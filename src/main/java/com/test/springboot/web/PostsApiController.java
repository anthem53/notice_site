package com.test.springboot.web;

import com.test.springboot.config.auth.LoginUser;
import com.test.springboot.config.auth.dto.SessionUser;
import com.test.springboot.domain.user.User;
import com.test.springboot.domain.user.UserRepository;
import com.test.springboot.service.posts.PostsService;
import com.test.springboot.web.Dto.PostsResponseDto;
import com.test.springboot.web.Dto.PostsSaveRequestDto;
import com.test.springboot.web.Dto.PostsUpdateRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.LazyToOne;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save (@RequestBody PostsSaveRequestDto requestDto , @LoginUser SessionUser user){

        return postsService.save(requestDto,user);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update (@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete (@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

}
