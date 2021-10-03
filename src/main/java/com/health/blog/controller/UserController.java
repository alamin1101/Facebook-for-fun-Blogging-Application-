package com.health.blog.controller;


import com.health.blog.entity.AppUser;
import com.health.blog.entity.BlogPost;
import com.health.blog.entity.Comment;
import com.health.blog.repository.AppUserRepository;
import com.health.blog.repository.BlogPostRepository;
import com.health.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping({"/create-blogpost"})
    public String createBlogPost(Model model)
    {
        model.addAttribute("blogPost",new BlogPost());
        return "create-blog-post";
    }

    @PostMapping("/create-blogpost")
    public String createBlogPost(@Valid BlogPost blogPost, Model model, Principal principal)
    {
        blogPostRepository.save(blogPost);
        AppUser appUser = appUserRepository.getById(principal.getName());

        appUser.getBlogPost().add(blogPost);

        appUserRepository.save(appUser);

        return "my-post";
    }

    @GetMapping("/my-post")
    public String myBlogPost(Model model,Principal principal)
    {
        AppUser appUser=appUserRepository.getById(principal.getName());
        model.addAttribute("myposts",appUser.getBlogPost());
        return "my-post";
    }

    @GetMapping("/pending-post")
    public String pendingBlogPost(Model model,Principal principal)
    {
        AppUser appUser=appUserRepository.getById(principal.getName());
        model.addAttribute("myposts",appUser.getBlogPost());
        return "pending-post";
    }

    @RequestMapping("/delete-mypost")
    public String deleteMyPost(Model model,@RequestParam int blogPostId)
    {

        blogPostRepository.deleteById(blogPostId);
        return "blog-homepage";
    }

    @GetMapping("/blog-homepage")
    public String blogHomePage(Model model,Principal principal)
    {
        List<BlogPost> blogPosts = blogPostRepository.findAllApprovePost();
        model.addAttribute("postlist",blogPosts);
        return "blog-homepage";
    }

    @GetMapping("/comment")
    public String comment(Model model,Principal principal,@RequestParam String comment,@RequestParam int blogPostId)
    {
        Comment comm=new Comment(comment);
        commentRepository.save(comm);
        BlogPost blogPost = blogPostRepository.getById(blogPostId);

        blogPost.getAllComment().add(comm);

        blogPostRepository.save(blogPost);
        model.addAttribute("postlist",blogPostRepository.findAllApprovePost());

        return "redirect:/blog-homepage";
    }

}

