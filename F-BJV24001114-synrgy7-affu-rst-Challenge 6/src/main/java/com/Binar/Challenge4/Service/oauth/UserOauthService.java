package com.Binar.Challenge4.Service.oauth;
import com.Binar.Challenge4.Entity.RegisterModel.RegisterModel;

import java.security.Principal;
import java.util.Map;

public interface UserOauthService {
    Map registerManual(RegisterModel objModel) ;
    Map registerByGoogle(RegisterModel objModel) ;
    Map getDetailProfile(Principal principal);

}