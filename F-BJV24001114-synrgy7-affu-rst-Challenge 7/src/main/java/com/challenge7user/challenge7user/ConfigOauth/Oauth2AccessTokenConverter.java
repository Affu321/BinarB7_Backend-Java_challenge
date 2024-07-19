package com.challenge7user.challenge7user.ConfigOauth;


import com.challenge7user.challenge7user.Repository.UserRepository;
import com.challenge7user.challenge7user.Service.oauth.Oauth2UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
@SuppressWarnings("unchecked")
public class Oauth2AccessTokenConverter extends DefaultAccessTokenConverter {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Oauth2UserDetailsService userDetailsService;

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        final OAuth2Authentication auth = super.extractAuthentication(map);
        final UserDetails user = userDetailsService.loadUserByUsername((String) auth.getPrincipal());
        return new OAuth2Authentication(auth.getOAuth2Request(), auth.getUserAuthentication()) {
            @Override
            public Collection<GrantedAuthority> getAuthorities() {
                if (user != null) {
                    return (Collection<GrantedAuthority>)
                            user.getAuthorities();
                }

                return auth.getAuthorities();
            }
        };
    }
}


