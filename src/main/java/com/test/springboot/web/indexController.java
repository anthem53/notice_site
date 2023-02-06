package com.test.springboot.web;

import com.test.springboot.config.auth.LoginUser;
import com.test.springboot.config.auth.dto.SessionUser;
import com.test.springboot.domain.comments.Comments;
import com.test.springboot.domain.posts.PostsRepository;
import com.test.springboot.domain.user.User;
import com.test.springboot.domain.user.UserRepository;
import com.test.springboot.service.comments.CommentsService;
import com.test.springboot.service.posts.PostsService;
import com.test.springboot.web.Dto.CommentsListResponseDto;
import com.test.springboot.web.Dto.CommentsResponseDto;
import com.test.springboot.web.Dto.PostsListResponseDto;
import com.test.springboot.web.Dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class indexController {

    private final PostsService postsService;
    private final CommentsService commentsService;
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;

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
            User temp_user = userRepository.findByEmail(user.getEmail()).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다. email ="+ user.getEmail()));;

            String a = user.getName();
            a = a.replace(" ", "_");
            System.out.println(a);
            model.addAttribute("AuthorName",a);
            model.addAttribute("user_id",temp_user.getId());
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

        List<CommentsListResponseDto> comments = commentsService.getCommentsList(id, user);
        model.addAttribute("comments",comments);
        /* 댓글 관련 */
        if (comments != null && !comments.isEmpty()) {
            model.addAttribute("comments", comments);
        }

        if (user != null){
            model.addAttribute("userName",user.getName());

            User userDto = userRepository.findByEmail(user.getEmail()).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다. email ="+user.getEmail()));

            if (dto.getAuthor_id() == userDto.getId()){
                model.addAttribute("isAuthor",true);
                System.out.println("a == author");
            }
        }
        else {
            model.addAttribute("userName",false);
        }

        return "posts-inquiry";
    }

    @GetMapping("/user")
    public String postUser( Model model, @LoginUser SessionUser user){

        if (user != null) {
            model.addAttribute("userName",user.getName());
            model.addAttribute("posts",postsService.findUserPosts(user));

        }


        return "posts-user";
    }
    @GetMapping("user/posts/inquiry/{id}")
    public String userPostInquiry(@PathVariable Long id, Model model, @LoginUser SessionUser user){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        model.addAttribute("isUserPost",true);

        List<CommentsListResponseDto> comments = commentsService.getCommentsList(id, user);
        model.addAttribute("comments",comments);
        /* 댓글 관련 */
        if (comments != null && !comments.isEmpty()) {
            model.addAttribute("comments", comments);
        }

        if (user != null){
            model.addAttribute("userName",user.getName());

            User userDto = userRepository.findByEmail(user.getEmail()).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다. email ="+user.getEmail()));

            if (dto.getAuthor_id() == userDto.getId()){
                model.addAttribute("isAuthor",true);
                System.out.println("a == author");
            }
        }
        else {
            model.addAttribute("userName",false);
        }

        return "posts-inquiry";
    }

    @GetMapping("/user/posts/update/{id}")
    public String userPostUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("isUserPost",true);
        model.addAttribute("post",dto);
        return "posts-update";

    }

    @GetMapping("/search")
    public String postSearch(@RequestParam(value="searchContent") String searchContent , Model model){
        System.out.println("****************");
        System.out.println("post search call "+ searchContent);
        System.out.println("****************");

        List<PostsListResponseDto> posts = postsService.findPostsWithSearchWord(searchContent);
        model.addAttribute("posts",posts);
        model.addAttribute("searchContent",searchContent);


        return "posts-search";
    }

    @GetMapping("/search/posts/inquiry/{id}")
    public String searchPostInquiry(@RequestParam(value="searchContent") String searchContent , @PathVariable Long id, Model model, @LoginUser SessionUser user){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        model.addAttribute("isSearchPost",true);
        model.addAttribute("searchContent",searchContent);

        List<CommentsListResponseDto> comments = commentsService.getCommentsList(id, user);
        model.addAttribute("comments",comments);
        /* 댓글 관련 */
        if (comments != null && !comments.isEmpty()) {
            model.addAttribute("comments", comments);
        }

        if (user != null){
            model.addAttribute("userName",user.getName());

            User userDto = userRepository.findByEmail(user.getEmail()).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다. email ="+user.getEmail()));

            if (dto.getAuthor_id() == userDto.getId()){
                model.addAttribute("isAuthor",true);
                System.out.println("a == author");
            }
        }
        else {;}

        return "posts-inquiry";
    }

    @GetMapping("/search/posts/update/{id}")
    public String searchPostUpdate(@RequestParam(value="searchContent") String searchContent , @PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("isSearchPost",true);
        model.addAttribute("post",dto);
        model.addAttribute("searchContent",searchContent);
        return "posts-update";

    }


}
