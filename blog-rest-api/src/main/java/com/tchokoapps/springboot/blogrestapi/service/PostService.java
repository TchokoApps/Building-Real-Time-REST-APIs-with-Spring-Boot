package com.tchokoapps.springboot.blogrestapi.service;

import com.tchokoapps.springboot.blogrestapi.dto.PostDto;
import com.tchokoapps.springboot.blogrestapi.dto.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPostDto(PostDto postDto);

    List<PostDto> getAllPosts();

    PostResponse getAllPostsByPage(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePostById(PostDto postDto, Long id);

    void deletePostById(Long id);
}
