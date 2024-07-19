package com.challenge7user.challenge7user.entity.oauth.request;


import lombok.Data;
@Data
public class ResetPasswordModel {
    public String email;
    public String otp;
    public String newPassword;
}