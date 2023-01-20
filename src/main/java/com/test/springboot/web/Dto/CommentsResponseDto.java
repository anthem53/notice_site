package com.test.springboot.web.Dto;

import com.test.springboot.domain.comments.Comments;
import com.test.springboot.domain.posts.Posts;
import com.test.springboot.domain.user.User;
import lombok.Builder;

public class CommentsResponseDto {

    private Long id;
    private String author;
    private String content;
    private String userName;
    private Long postsID;

    public CommentsResponseDto(Comments comments){
        this.id = comments.getId();
        this.content = comments.getContent();
        this.author = comments.getAuthor();

    }

}
