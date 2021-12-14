package com.tchokoapps.springboot.blogrestapi.service.impl;

import com.tchokoapps.springboot.blogrestapi.dto.CommentDto;
import com.tchokoapps.springboot.blogrestapi.dto.mapper.CommentDtoMapper;
import com.tchokoapps.springboot.blogrestapi.entity.Comment;
import com.tchokoapps.springboot.blogrestapi.entity.Post;
import com.tchokoapps.springboot.blogrestapi.exception.BlogApiException;
import com.tchokoapps.springboot.blogrestapi.exception.ResourceNotFoundException;
import com.tchokoapps.springboot.blogrestapi.repository.CommentRepository;
import com.tchokoapps.springboot.blogrestapi.repository.PostRepository;
import com.tchokoapps.springboot.blogrestapi.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Override
    public CommentDto createCommentDto(long postId, @NonNull CommentDto commentDto) {
        CommentDtoMapper commentDtoMapper = new CommentDtoMapper();
        Comment comment = commentDtoMapper.mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return commentDtoMapper.mappToDto(savedComment);
    }

    @Override
    public List<CommentDto> findCommentsByPostId(long postId) {
        CommentDtoMapper commentDtoMapper = new CommentDtoMapper();
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        List<Comment> comments = commentRepository.findByPost(post);
        return comments.stream().map(commentDtoMapper::mappToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto findByPostIdAndId(long postId, long id) {
        CommentDtoMapper commentDtoMapper = new CommentDtoMapper();
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
        Comment comment2 = commentRepository.findByPostIdAndId(postId, id).orElseThrow(() ->
                new BlogApiException(HttpStatus.BAD_REQUEST, String.format("Comment with id=%s doesn´t belong to post with id=%d", id, postId)));
        return commentDtoMapper.mappToDto(comment2);
    }
}
