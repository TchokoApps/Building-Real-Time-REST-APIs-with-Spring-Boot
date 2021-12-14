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
    private CommentDtoMapper commentDtoMapper;

    @Override
    public CommentDto createCommentDto(long postId, @NonNull CommentDto commentDto) {
        Comment comment = commentDtoMapper.mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return commentDtoMapper.mappToDto(savedComment);
    }

    @Override
    public List<CommentDto> findCommentsByPostId(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        List<Comment> comments = commentRepository.findByPost(post);
        return comments.stream().map(commentDtoMapper::mappToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto findByPostIdAndId(long postId, long id) {
        Comment comment2 = findComment(postId, id);
        return commentDtoMapper.mappToDto(comment2);
    }

    @Override
    public CommentDto updateByPostIdAndId(long postId, long id, CommentDto commentDto) {
        Comment comment2 = findComment(postId, id);
        comment2.setEmail(commentDto.getEmail());
        comment2.setName(commentDto.getName());
        comment2.setBody(commentDto.getBody());
        Comment savedComment = commentRepository.save(comment2);
        return commentDtoMapper.mappToDto(savedComment);
    }

    private Comment findComment(long postId, long id) {
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
        return commentRepository.findByPostIdAndId(postId, id).orElseThrow(() ->
                new BlogApiException(HttpStatus.BAD_REQUEST, String.format("Comment with id=%s doesn´t belong to post with id=%d", id, postId)));
    }
}
