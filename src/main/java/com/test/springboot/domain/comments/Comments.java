package com.test.springboot.domain.comments;

import com.test.springboot.domain.BaseTimeEntity;
import com.test.springboot.domain.posts.Posts;
import com.test.springboot.domain.user.Role;
import com.test.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Comments extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(columnDefinition = "TEXT",nullable = false)
    private String author;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String content; // 덧글 내용



    @ManyToOne
    @JoinColumn(name="Posts_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name="User_id")
    private User user;

    @Builder
    public Comments (String author, String content,Posts posts,User user){
        this.author = author;
        this.content = content;
        this.posts = posts;
        this.user = user;
    }

    public void update (String content){
        this.content = content;
    }


}
