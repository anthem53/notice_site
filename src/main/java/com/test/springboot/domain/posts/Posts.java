package com.test.springboot.domain.posts;

import com.test.springboot.domain.BaseTimeEntity;
import com.test.springboot.domain.comments.Comments;
import com.test.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private String author;

    private Long author_id;

    @OneToMany(mappedBy = "posts", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comments> comments;

    @ManyToOne
    @JoinColumn(name="User_id")
    private User user;



    @Builder
    public Posts(String title , String content, String author, Long author_id,User user){
        this.title = title;
        this.content = content;
        this.author = author;
        this.author_id = author_id;
        this.user = user;

    }

    public void set_Author_id(Long Author_id){
        this.author_id = Author_id;
    }



    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
