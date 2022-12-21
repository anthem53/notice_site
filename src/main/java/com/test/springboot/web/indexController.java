package com.test.springboot.web;

import com.test.springboot.service.posts.PostsService;
import com.test.springboot.web.Dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class indexController {

    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model){
        System.out.println("**********************");
        System.out.println("Call index");
        System.out.println("**********************");
        model.addAttribute("posts",postsService.findAllDesc());

        return "index";
    }

    @GetMapping("/posts/save")
    public String postSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);

        model.addAttribute("post",dto);
        return "posts-update";

    }


}
