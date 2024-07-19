package com.challenge7user.challenge7user.entity.RegisterModel;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class RegisterModel {
    @NotEmpty(message = "username is required.")
    private String username;
    @NotEmpty(message = "password is required.")
    private String password;
    @NotEmpty(message = "fullname is required.")
    private String fullname;
}
