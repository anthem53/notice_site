package com.test.springboot.service.posts;

import com.test.springboot.config.auth.dto.SessionUser;
import com.test.springboot.domain.posts.Posts;
import com.test.springboot.domain.posts.PostsRepository;
import com.test.springboot.domain.user.User;
import com.test.springboot.domain.user.UserRepository;
import com.test.springboot.web.Dto.PostsListResponseDto;
import com.test.springboot.web.Dto.PostsResponseDto;
import com.test.springboot.web.Dto.PostsSaveRequestDto;
import com.test.springboot.web.Dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;


    @Transactional
    public Long save(PostsSaveRequestDto requestDto , SessionUser user) {

        User userEntity = userRepository.findByEmail(user.getEmail()).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다. email ="+user.getEmail()));


        if (requestDto.getTitle().equals("")){
            requestDto.setTitle("No title");
        }
        if (requestDto.getAuthor().equals("")){
            requestDto.setAuthor("No Author");
        }
        if (requestDto.getContent().equals("")){
            requestDto.setContent("No Content");
        }

        requestDto.setUserId(userEntity.getId());
        requestDto.setUser(userEntity);


        return postsRepository.save(requestDto.toEntity()).getId();
    }



    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id ="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id ="+id));

        return new PostsResponseDto(entity);

    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());

    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);

    }

    @Transactional
    public List<PostsListResponseDto> findUserPosts(SessionUser user){
        User userEntity = userRepository.findByEmail(user.getEmail()).orElseThrow(() -> new IllegalArgumentException("해당 유저는 없습니다 email:"+ user.getEmail()));
        List<Posts> userPosts = userEntity.getPosts();
        Collections.reverse(userPosts);

        return userPosts.stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public List<PostsListResponseDto> findPostsWithSearchWord(String content){

        List<Posts> entityList = postsRepository.findByTitleContaining(content) ;
        List<PostsListResponseDto> resultList = new ArrayList<>();

        if (entityList.isEmpty() == true) return resultList;
        else {
            for (Posts post : entityList){
                resultList.add(new PostsListResponseDto(post));
            }
                Collections.reverse(resultList);
            return resultList;
        }

    }

}
