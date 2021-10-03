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
    private String status="Inactive";
    private String password;
    @Transient
    private String confirmPassword;
    private String role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userinfo", referencedColumnName = "username")
    private List<BlogPost> blogPost;


    public AppUser()
    {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AppUser(String username, String email,String status) {
        this.username = username;
        this.email = email;
        this.status=status;
    }
}

