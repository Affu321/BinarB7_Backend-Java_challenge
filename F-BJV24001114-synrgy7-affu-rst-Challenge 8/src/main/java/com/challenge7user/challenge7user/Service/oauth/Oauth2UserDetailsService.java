package com.challenge7user.challenge7user.Service.oauth;

import com.challenge7user.challenge7user.Repository.oauth.UserOauthRepository;
import com.challenge7user.challenge7user.entity.oauth.UserOauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class Oauth2UserDetailsService implements UserDetailsService{

    @Autowired
    private UserOauthRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserOauth user = userRepository.findOneByUsername(s);
        if (null == user) {
            throw new UsernameNotFoundException(String.format("Username %s is not found", s));
        }

        return user;
    }

    @CacheEvict("oauth_username")
    public void clearCache(String s) {
    }
}
