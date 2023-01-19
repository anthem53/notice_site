package com.test.springboot.domain.comments;

import com.test.springboot.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface CommentsRepository extends JpaRepository<Comments,Long> {


}
