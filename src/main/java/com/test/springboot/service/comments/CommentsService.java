package com.test.springboot.service.comments;


import com.test.springboot.domain.comments.CommentsRepository;

import com.test.springboot.domain.posts.Posts;
import com.test.springboot.domain.posts.PostsRepository;
import com.test.springboot.domain.user.User;
import com.test.springboot.domain.user.UserRepository;
import com.test.springboot.web.Dto.CommentsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;

    @Transactional
    public Long commentSave(CommentsRequestDto requestDto , Long id, String userEmail) {

        User user = userRepository.findByEmail(userEmail).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다. Email ="+userEmail));;
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id ="+id));;

        requestDto.setPosts(posts);
        requestDto.setUser(user);

        Long temp = commentsRepository.save(requestDto.toEntity()).getId();
        System.out.println(temp);
        System.out.println(requestDto);
        return temp;
    }

}
