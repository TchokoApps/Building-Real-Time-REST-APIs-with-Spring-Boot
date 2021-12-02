package com.tchokoapps.springboot.blogrestapi.service.impl;

import com.tchokoapps.springboot.blogrestapi.dto.PostDto;
import com.tchokoapps.springboot.blogrestapi.dto.mapper.PostDtoMapper;
import com.tchokoapps.springboot.blogrestapi.entity.Post;
import com.tchokoapps.springboot.blogrestapi.repository.PostRepository;
import com.tchokoapps.springboot.blogrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Override
    public PostDto createPostDto(@NonNull PostDto postDto) {

        PostDtoMapper postDtoMapper = new PostDtoMapper();

        Post post = postDtoMapper.mapToEntity(postDto);

        Post postSaved = postRepository.save(post);

        return postDtoMapper.mapToDto(postSaved);
    }

}
