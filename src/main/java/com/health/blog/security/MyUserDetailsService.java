package com.health.blog.security;

import com.health.blog.entity.AppUser;
import com.health.blog.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository userLogRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = userLogRepository.findById(email).get();
        if (appUser == null)
            throw new UsernameNotFoundException("User not found");
        return new MyUserDetails(appUser);
    }
}
