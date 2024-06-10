package com.Binar.Challenge4.Entity.oauth.request;

import lombok.Data;
@Data
public class ResetPasswordModel {
    public String email;
    public String otp;
    public String newPassword;
}

