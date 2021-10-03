package com.health.blog.entity;


import javax.persistence.*;
import java.util.List;


@Entity
public class BlogPost {
    @Id
    @SequenceGenerator(name = "blog_sequence", sequenceName = "blog_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "")
    private int blogPostId;
    private String blogPostTitle;
    private String blogpost;
    private String approve="false";

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "blogPostcomment", referencedColumnName = "blogPostId")
    private List<Comment> allComment;

    public BlogPost(int blogPostId, String blogPostTitle, String blogpost) {
        this.blogPostId = blogPostId;
        this.blogPostTitle = blogPostTitle;
        this.blogpost = blogpost;
    }

    public BlogPost() {

    }


    public int getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(int blogPostId) {
        this.blogPostId = blogPostId;
    }

    public String getBlogPostTitle() {
        return blogPostTitle;
    }

    public void setBlogPostTitle(String blogPostTitle) {
        this.blogPostTitle = blogPostTitle;
    }

    public String getBlogpost() {
        return blogpost;
    }

    public void setBlogpost(String blogpost) {
        this.blogpost = blogpost;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public List<Comment> getAllComment() {
        return allComment;
    }

    public void setAllComment(List<Comment> allComment) {
        this.allComment = allComment;
    }
}
