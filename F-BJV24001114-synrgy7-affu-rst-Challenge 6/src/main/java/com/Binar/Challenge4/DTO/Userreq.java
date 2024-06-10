package com.Binar.Challenge4.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class Userreq {
    private UUID id;
    private String username;
    private String emailAddress;
    private String password;
}
