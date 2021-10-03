package com.health.blog.repository;

import com.health.blog.entity.AppUser;
import com.health.blog.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost,Integer>
{
    @Modifying
    @Query("delete from BlogPost bb where bb.blogpost=?1")
    void removeBlogPost(BlogPost blogPost);

    @Query("select b from BlogPost b WHERE b.approve='true' ")
    List<BlogPost>findAllApprovePost();

}
