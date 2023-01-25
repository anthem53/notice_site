package com.test.springboot.web.Dto;

import com.test.springboot.domain.comments.Comments;
import com.test.springboot.domain.posts.Posts;
import com.test.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;


@Getter
public class CommentsRequestDto {

    private Long id;
    private String content;

    private String author;
    private User user ;
    private Posts posts;


    public CommentsRequestDto(Long id, String author,String content){
        this.id = id;
        this.content = content;
        this.author = author;
    }
    public void print(){
        System.out.println(id);
        System.out.println(author);
        System.out.println(content);
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

