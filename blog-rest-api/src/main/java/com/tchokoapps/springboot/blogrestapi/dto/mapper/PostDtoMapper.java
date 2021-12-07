package com.tchokoapps.springboot.blogrestapi.dto.mapper;

import com.tchokoapps.springboot.blogrestapi.dto.PostDto;
import com.tchokoapps.springboot.blogrestapi.entity.Post;
import org.springframework.lang.NonNull;

public class PostDtoMapper {

    public PostDto mapToDto(@NonNull Post post) {

        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());

        return postDto;
    }

    public Post mapToEntity(@NonNull PostDto postDto) {

        Post post = new Post();

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;
    }
}
