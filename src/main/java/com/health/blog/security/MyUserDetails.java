package com.health.blog.security;


import com.health.blog.entity.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MyUserDetails implements UserDetails {

    private AppUser appUser;

    public MyUserDetails(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(appUser.getRole()));
    }

    @Override
    public String getPassword() {
        if(appUser.getStatus().equals("Active"))
            return appUser.getPassword();
        else
        {
            return "-1111-1-1-1-1-1-1-111"+appUser.getPassword()+";ty5643828111----1-1=====--2=]]p[]]\\][p[er[[][]rqwp[e[]]]r[]t[y]";
        }
    }

    @Override
    public String getUsername() {
        return appUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
