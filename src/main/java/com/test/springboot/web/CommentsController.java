package com.test.springboot.web;


import com.test.springboot.config.auth.LoginUser;
import com.test.springboot.config.auth.dto.SessionUser;
import com.test.springboot.service.comments.CommentsService;
import com.test.springboot.service.posts.PostsService;
import com.test.springboot.web.Dto.CommentsRequestDto;
import com.test.springboot.web.Dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController

public class CommentsController {

    private final CommentsService commentsService;
    private final PostsService postsService;
    @PostMapping("/api/v1/posts/{id}/comment")
    public Long commentSave (@RequestBody CommentsRequestDto requestDto , @PathVariable Long id , @LoginUser SessionUser user){

        String userEmail = user.getEmail();
        Long new_id =commentsService.commentSave(requestDto,id,userEmail);
        System.out.println("**********************");
        System.out.println("new_id : " + new_id);
        System.out.println("**********************");
        return new_id;
    }

    /* UPDATE */
    @PutMapping({"/api/v1/posts/{post_id}/commentsUpdate/{comment_id}"})
    public Long update(@PathVariable Long post_id, @PathVariable Long comment_id ,  @RequestBody CommentsRequestDto dto) {
        System.out.println("**** comment_update_check **** ");
        dto.print();
        Long result = commentsService.update(post_id , comment_id,  dto);
        return result;
    }

    /* DELETE */
    @DeleteMapping("/api/v1/posts/{post_id}/commentsDelete/{comment_id}")
    public void delete(@PathVariable Long post_id, @PathVariable Long comment_id) {
        commentsService.delete(post_id,comment_id);
    }


}
