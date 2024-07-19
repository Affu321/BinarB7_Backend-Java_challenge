package com.challenge7user.challenge7user.entity;

import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User extends AbstrackUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(length = 255)
    private String username;

    @Column(name = "email_address", length = 255)
    private String emailAddress;

    @Column(name = "password", length = 255)
    private String password;
}

