package com.challenge7user.challenge7user.Service.oauth.oauthimpl;
import com.challenge7user.challenge7user.ConfigOauth.Config;
import com.challenge7user.challenge7user.Repository.oauth.RoleRepository;
import com.challenge7user.challenge7user.Repository.oauth.UserOauthRepository;
import com.challenge7user.challenge7user.Service.oauth.Oauth2UserDetailsService;
import com.challenge7user.challenge7user.Service.oauth.UserOauthService;
import com.challenge7user.challenge7user.Utils.Response;
import com.challenge7user.challenge7user.entity.RegisterModel.RegisterModel;
import com.challenge7user.challenge7user.entity.oauth.Role;
import com.challenge7user.challenge7user.entity.oauth.UserOauth;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserOauthServiceImpl implements UserOauthService {

    Config config = new Config();
    private static final Logger logger = LoggerFactory.getLogger(UserOauthServiceImpl.class);
    @Autowired
    RoleRepository repoRole;

    @Autowired
    UserOauthRepository repoUser;

    @Autowired
    public UserOauthRepository userRepository;

    @Autowired
    private Oauth2UserDetailsService userDetailsService;


    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public Response response;

    @Override
    public Map registerManual(RegisterModel objModel) {
        Map map = new HashMap();
        try {
            String[] roleNames = {"ROLE_USER", "ROLE_READ", "ROLE_WRITE"}; // admin
            UserOauth user = new UserOauth();
            user.setUsername(objModel.getUsername().toLowerCase());
            user.setFullname(objModel.getFullname());

            //step 1 :
//            user.setEnabled(false); // matikan user

            String password = encoder.encode(objModel.getPassword().replaceAll("\\s+", ""));
            List<Role> r = repoRole.findByNameIn(roleNames);

            user.setRoles(r);
            user.setPassword(password);
            UserOauth obj = repoUser.save(user);

            return response.templateEror(obj);

        } catch (Exception e) {
            logger.error("Eror registerManual=", e);
            return response.templateEror("eror:"+e);
        }

    }

    @Override
    public Map registerByGoogle(RegisterModel objModel) {
        Map map = new HashMap();
        try {
            String[] roleNames = {"ROLE_USER", "ROLE_READ", "ROLE_WRITE"}; // ROLE DEFAULE
            UserOauth user = new UserOauth();
            user.setUsername(objModel.getUsername().toLowerCase());
            user.setFullname(objModel.getFullname());
            //step 1 :
            user.setEnabled(false); // matikan user
            String password = encoder.encode(objModel.getPassword().replaceAll("\\s+", ""));
            List<Role> r = repoRole.findByNameIn(roleNames);
            user.setRoles(r);
            user.setPassword(password);
            UserOauth obj = repoUser.save(user);
            return response.sucsess(obj);

        } catch (Exception e) {
            logger.error("Eror registerManual=", e);
            return response.templateEror("eror:"+e);
        }
    }

    @Override
    public Map getDetailProfile(Principal principal) {
        UserOauth idUser = getUserIdToken(principal, userDetailsService);
        try {
//            User obj = userRepository.save(idUser);
            return response.sucsess(idUser);
        } catch (Exception e){
            return response.eror(e,"500");
        }
    }

    private UserOauth getUserIdToken(Principal principal, Oauth2UserDetailsService userDetailsService) {
        UserDetails user = null;
        String username = principal.getName();
        if (!StringUtils.isEmpty(username)) {
            user = userDetailsService.loadUserByUsername(username);
        }

        if (null == user) {
            throw new UsernameNotFoundException("User not found");
        }
        UserOauth idUser = userRepository.findOneByUsername(user.getUsername());
        if (null == idUser) {
            throw new UsernameNotFoundException("User name not found");
        }
        return idUser;
    }


}
