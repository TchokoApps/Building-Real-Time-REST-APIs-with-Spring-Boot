package com.tchokoapps.springboot.blogrestapi.service.impl;

import com.tchokoapps.springboot.blogrestapi.dto.PostDto;
import com.tchokoapps.springboot.blogrestapi.dto.mapper.PostDtoMapper;
import com.tchokoapps.springboot.blogrestapi.entity.Post;
import com.tchokoapps.springboot.blogrestapi.exception.ResourceNotFoundException;
import com.tchokoapps.springboot.blogrestapi.repository.PostRepository;
import com.tchokoapps.springboot.blogrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<PostDto> getAllPosts() {

        PostDtoMapper postDtoMapper = new PostDtoMapper();

        List<Post> posts = postRepository.findAll();

        return posts.stream().map(postDtoMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        PostDtoMapper postDtoMapper = new PostDtoMapper();
        return postRepository.findById(id).map(postDtoMapper::mapToDto).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }

    @Override
    public PostDto updatePostById(PostDto postDto, Long id) {

        PostDtoMapper postDtoMapper = new PostDtoMapper();

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        Post savedPost = postRepository.save(post);

        return postDtoMapper.mapToDto(savedPost);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

}
