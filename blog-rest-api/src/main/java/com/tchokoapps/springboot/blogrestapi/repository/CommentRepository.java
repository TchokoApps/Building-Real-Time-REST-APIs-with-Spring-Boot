package com.tchokoapps.springboot.blogrestapi.repository;

import com.tchokoapps.springboot.blogrestapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
