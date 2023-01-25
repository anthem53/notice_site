package com.test.springboot.service.comments;


import com.test.springboot.domain.comments.Comments;
import com.test.springboot.domain.comments.CommentsRepository;

import com.test.springboot.domain.posts.Posts;
import com.test.springboot.domain.posts.PostsRepository;
import com.test.springboot.domain.user.User;
import com.test.springboot.domain.user.UserRepository;
import com.test.springboot.web.Dto.CommentsListResponseDto;
import com.test.springboot.web.Dto.CommentsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

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

        Comments comment = requestDto.toEntity();
        posts.getComments().add(comment);


        Long temp = commentsRepository.save(comment).getId();
        return temp;
    }

    @Transactional
    public List<CommentsListResponseDto> getCommentsList(Long post_id){
        Posts current_posts = postsRepository.findById(post_id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id ="+post_id));;
        // postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        return current_posts.getComments().stream().map(CommentsListResponseDto::new).collect(Collectors.toList());

    }

    @Transactional
    public Long update(Long post_id, Long comment_id, CommentsRequestDto dto){
        Posts current_posts = postsRepository.findById(post_id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id ="+post_id));;
        Comments current_comment = commentsRepository.findById(comment_id).orElseThrow(()-> new IllegalArgumentException("해당 덧글이 없습니다. id ="+comment_id));;

        current_comment.update(dto.getContent());

        return comment_id;
    }

    @Transactional
    public void delete(Long post_id , Long comment_id){
        Comments comment = commentsRepository.findById(comment_id).orElseThrow(() -> new IllegalArgumentException("해당 코멘트가 존재하지 않습니다 id="+comment_id));
        Posts entity = postsRepository.findById(post_id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id ="+post_id));

        entity.getComments().remove(comment);
        entity.getComments().remove(comment);

        commentsRepository.delete(comment);

    }

}
