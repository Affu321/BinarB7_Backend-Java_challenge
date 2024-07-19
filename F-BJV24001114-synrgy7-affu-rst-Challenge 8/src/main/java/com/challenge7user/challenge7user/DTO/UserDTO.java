package com.challenge7user.challenge7user.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String username;
    private String emailAddress;
    private String password;
}
