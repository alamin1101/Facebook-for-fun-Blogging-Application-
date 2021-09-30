package com.health.blog.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.List;

@Setter
@Getter
@ToString
@Entity
public class AppUser {

    @Id
    private String username;
    private String email;
    private boolean status=false;
    private String password;
    @Transient
    private String confirmPassword;
    private String role;


}

