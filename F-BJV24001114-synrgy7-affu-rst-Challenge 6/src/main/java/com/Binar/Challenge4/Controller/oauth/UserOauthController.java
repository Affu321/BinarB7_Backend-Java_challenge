package com.Binar.Challenge4.Controller.oauth;



import com.Binar.Challenge4.Repository.oauth.UserOauthRepository;
import com.Binar.Challenge4.Service.oauth.Oauth2UserDetailsService;
import com.Binar.Challenge4.Service.oauth.UserOauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserOauthController {


    @Autowired
    UserOauthRepository userRepository;

    @Autowired
    private Oauth2UserDetailsService userDetailsService;

    @Autowired
    UserOauthService userService;

    @GetMapping("/detail-profile")
    public ResponseEntity<Map> detailProfile(
            Principal principal
    ) {
        Map map = userService.getDetailProfile(principal);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

}
