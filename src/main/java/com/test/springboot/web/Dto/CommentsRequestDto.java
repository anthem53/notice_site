package com.test.springboot.web.Dto;

import com.test.springboot.domain.comments.Comments;
import com.test.springboot.domain.posts.Posts;
import com.test.springboot.domain.user.User;

public class CommentsRequestDto {

    private Long id;
    private String author;
    private String content;
    private User user ;
    private Posts posts;

    public Comments toEntity(){
        Comments comment = Comments.builder()
                .author(author).content(content).user(user).posts(posts).build();

        return comment;
    }

}
