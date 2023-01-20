package com.test.springboot.web.Dto;

import com.test.springboot.domain.comments.Comments;
import com.test.springboot.domain.posts.Posts;
import com.test.springboot.domain.user.User;
import lombok.Builder;

public class CommentsRequestDto {

    private String author;
    private String content;
    private User user ;
    private Posts posts;


    public CommentsRequestDto(String author,String content,User user,Posts posts){
        this.author = author;
        this.content = content;
        this.user = user;
        this.posts = posts;
    }
    public void print(){
        System.out.println(author);
        System.out.println(content);
        System.out.println(user);
        System.out.println(posts);
    }

    public void setUser(User user){
        this.user = user;
    }
    public void setPosts(Posts posts){
        this.posts = posts;
    }
    public Comments toEntity(){
        Comments comment = Comments.builder()
                .author(author).content(content).user(user).posts(posts).build();

        return comment;
    }

}

