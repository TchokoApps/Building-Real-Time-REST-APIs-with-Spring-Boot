package com.tchokoapps.springboot.blogrestapi.service;

import com.tchokoapps.springboot.blogrestapi.dto.PostDto;

public interface PostService {
    PostDto createPostDto(PostDto postDto);
}
