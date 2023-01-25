package com.test.springboot.web.Dto;

import com.test.springboot.domain.posts.Posts;
import com.test.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    private Long userId;

    private User user;


    @Builder
    public PostsSaveRequestDto(String title, String content, String author,Long author_id , User user){
        this.title = title;
        this.content = content;
        this.author = author;
        this.userId = author_id;
        this.user = user;


    }

    public Posts toEntity(){
        return Posts.builder().title(title).content(content).author(author).author_id(userId).user(user).build();

    }
}
