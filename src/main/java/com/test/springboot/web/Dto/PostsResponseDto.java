package com.test.springboot.web.Dto;

import com.test.springboot.domain.posts.Posts;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private Long author_id;
    private List<CommentsResponseDto> comments;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.author_id = entity.getAuthor_id();
        this.comments = entity.getComments().stream().map(CommentsResponseDto::new).collect(Collectors.toList());

    }


}
