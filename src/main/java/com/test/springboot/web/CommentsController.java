package com.test.springboot.web;


import com.test.springboot.config.auth.LoginUser;
import com.test.springboot.config.auth.dto.SessionUser;
import com.test.springboot.service.comments.CommentsService;
import com.test.springboot.service.posts.PostsService;
import com.test.springboot.web.Dto.CommentsRequestDto;
import com.test.springboot.web.Dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController

public class CommentsController {

    private final CommentsService commentsService;
    private final PostsService postsService;
    @PostMapping("/api/v1/posts/{id}/comment")
    public Long commentSave (@RequestBody CommentsRequestDto requestDto , @PathVariable Long id , @LoginUser SessionUser user){

        String userEmail = user.getEmail();

        System.out.println("****** user email *****");
        System.out.println(userEmail);
        System.out.println("*****************");


        long temp2 = commentsService.commentSave(requestDto,id,userEmail);
        return temp2;
    }


}
