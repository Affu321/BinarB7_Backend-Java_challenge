package com.challenge7user.challenge7user.Service.oauth;
import com.challenge7user.challenge7user.entity.RegisterModel.RegisterModel;

import java.security.Principal;
import java.util.Map;

public interface UserOauthService {
    Map registerManual(RegisterModel objModel) ;
    Map registerByGoogle(RegisterModel objModel) ;
    Map getDetailProfile(Principal principal);

}