package com.health.blog.repository;

import com.health.blog.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser,String>
{
    int countAppUsersByUsername(String username);
    @Query("select user from AppUser user where user.role='ROLE_ADMIN'")
    List<AppUser>findByRole();

    @Query("select new AppUser(b.username,b.email,b.status) from AppUser b WHERE b.role='ROLE_USER' and (?1 is null or b.username like %?1% or b.email like %?1%)  ")
    List<AppUser> findAllUsers(String s);

    @Query("select new AppUser(b.username,b.email,b.status) from AppUser b WHERE b.role='ROLE_USER' ")
    List<AppUser> findAllUser();

    @Query("select new AppUser(b.username,b.email,b.status) from AppUser b WHERE b.role='ROLE_ADMIN' and (?1 is null or b.username like %?1% or b.email like %?1%)  ")
    List<AppUser> findAllAdmin(String s);

}
