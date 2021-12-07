package com.tchokoapps.springboot.blogrestapi.service;

import com.tchokoapps.springboot.blogrestapi.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPostDto(PostDto postDto);
    List<PostDto> getAllPosts();
    PostDto getPostById(Long id);
    PostDto updatePostById(PostDto postDto, Long id);
    void deletePostById(Long id);
}
