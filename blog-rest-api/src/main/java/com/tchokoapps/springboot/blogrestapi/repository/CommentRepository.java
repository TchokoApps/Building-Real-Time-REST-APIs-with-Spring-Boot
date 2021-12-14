package com.tchokoapps.springboot.blogrestapi.repository;

import com.tchokoapps.springboot.blogrestapi.entity.Comment;
import com.tchokoapps.springboot.blogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
