package com.health.blog.entity;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @SequenceGenerator(name = "comment_sequence", sequenceName = "comment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "")
    private int commentId;
    private String comment;

    public  Comment()
    {

    }
    public Comment(String comment) {
        this.comment = comment;
    }


    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
