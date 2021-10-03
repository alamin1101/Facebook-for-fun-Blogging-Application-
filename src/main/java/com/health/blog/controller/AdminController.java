package com.health.blog.controller;


import com.health.blog.entity.AppUser;
import com.health.blog.entity.BlogPost;
import com.health.blog.repository.AppUserRepository;
import com.health.blog.repository.BlogPostRepository;
import netscape.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class AdminController {


    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping("/active")
    public String activeId(Model model,@RequestParam String username)
    {
        AppUser appUser = appUserRepository.findById(username).get();
        appUser.setStatus("Active");
        appUserRepository.save(appUser);
        model.addAttribute("userslist", appUserRepository.findAllUser());
        return "users-list";
    }
    @RequestMapping("/inactive")
    public String inactiveId(Model model,@RequestParam String username)
    {
        AppUser appUser = appUserRepository.findById(username).get();
        appUser.setStatus("Inactive");
        appUserRepository.save(appUser);
        model.addAttribute("userslist", appUserRepository.findAllUser());
        return "users-list";
    }
    @RequestMapping("/delete")
    public String deleteId(Model model,@RequestParam String username)
    {

        appUserRepository.deleteById(username);
        model.addAttribute("userslist", appUserRepository.findAllUser());
        return "users-list";
    }


    @GetMapping("/blog-posts")
    public String blogPosts(Model model)
    {
        model.addAttribute("blogposts",blogPostRepository.findAll());
        return "blog-posts";
    }



    @RequestMapping("/approve")
    public String approvePost(Model model,@RequestParam int blogPostId)
    {
        BlogPost blogPost= blogPostRepository.getById(blogPostId);
        blogPost.setApprove("true");
        blogPostRepository.save(blogPost);

        model.addAttribute("blogposts", blogPostRepository.findAll());
        return "blog-posts";
    }
    @RequestMapping("/deactive")
    public String deActivePost(Model model,@RequestParam int blogPostId)
    {
        BlogPost blogPost= blogPostRepository.getById(blogPostId);
        blogPost.setApprove("false");
        blogPostRepository.save(blogPost);

        model.addAttribute("blogposts", blogPostRepository.findAll());
        return "blog-posts";
    }
    @RequestMapping("/delete-post")
    public String deletePost(Model model,@RequestParam int blogPostId)
    {

        blogPostRepository.deleteById(blogPostId);
        return "blog-posts";
    }


    @GetMapping({"/create-admin"})
    public String addAdmin(Model model)
    {
        model.addAttribute("appUser",new AppUser());
        return "add-admin";
    }

    @PostMapping("/create-admin")
    public String addAdmin(@Valid AppUser appUser,Model model)
    {

        if(appUserRepository.countAppUsersByUsername(appUser.getUsername())==1)
            return "add-admin";
        if (!appUser.getPassword().equals(appUser.getConfirmPassword())) {
            return "add-admin";
        }
        String pass = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(pass);

            appUser.setRole("ROLE_ADMIN");
            appUser.setStatus("Active");

        appUserRepository.save(appUser);
        model.addAttribute("message","sign up success");
        return "home";
    }


    @RequestMapping("userslist")
    public String userslist(Model model,@RequestParam(required = false) String s) {
        AppUser appUser=new AppUser();
        model.addAttribute("userslist", appUserRepository.findAllUsers(s));
        return "users-list";
    }

    @RequestMapping("/adminlist")
    public String adminlist(Model model,@RequestParam(required = false) String s) {
        AppUser appUser=new AppUser();
        model.addAttribute("userslist", appUserRepository.findAllAdmin(s));
        return "admin-list";
    }

}
