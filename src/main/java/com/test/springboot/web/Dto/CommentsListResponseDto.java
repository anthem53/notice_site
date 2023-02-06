package com.test.springboot.web.Dto;

import com.test.springboot.domain.comments.Comments;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentsListResponseDto {

    private Long id;
    private String author;
    private String content;
    private LocalDateTime modifiedDate;

    private boolean isCommentAuthor;



    public CommentsListResponseDto(Comments comments){

        this.id = comments.getId();
        this.content = comments.getContent();
        this.author = comments.getAuthor();
        this.modifiedDate = comments.getModifiedDate();
        this.isCommentAuthor = false;
    }


}
