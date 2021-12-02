package com.tchokoapps.springboot.blogrestapi.repository;

import com.tchokoapps.springboot.blogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
