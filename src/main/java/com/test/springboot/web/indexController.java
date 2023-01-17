package com.test.springboot.web;

import com.test.springboot.config.auth.LoginUser;
import com.test.springboot.config.auth.dto.SessionUser;
import com.test.springboot.service.posts.PostsService;
import com.test.springboot.web.Dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class indexController {

    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model , @LoginUser SessionUser user){

        model.addAttribute("posts",postsService.findAllDesc());


        if (user != null){
            model.addAttribute("userName",user.getName());
            System.out.println(user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postSave(Model model  , @LoginUser SessionUser user){

        if (user != null){
            String a = user.getName();
            a = a.replace(" ", "_");
            System.out.println(a);
            model.addAttribute("AuthorName",a);
        }


        return "posts-save";
    }



    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);

        model.addAttribute("post",dto);
        return "posts-update";

    }

    @GetMapping("/posts/inquiry/{id}")
    public String postInquiry(@PathVariable Long id, Model model, @LoginUser SessionUser user){
        PostsResponseDto dto = postsService.findById(id);

        model.addAttribute("post",dto);

        if (user != null){
            String a = user.getName();
            String postAuthor = dto.getAuthor();
            a = a.replace(" ", "_");
            System.out.println("user ID : "+a);
            System.out.println("Post author : "+postAuthor);

            if (a.equals(postAuthor) == true){
                model.addAttribute("isAuthor",true);
                System.out.println("a == author");
            }
            else{
                model.addAttribute("isAuthor",false);
            }

        }

        return "posts-inquiry";
    }


}
