package com.tchokoapps.springboot.blogrestapi.service.impl;

import com.tchokoapps.springboot.blogrestapi.dto.CommentDto;
import com.tchokoapps.springboot.blogrestapi.entity.Comment;
import com.tchokoapps.springboot.blogrestapi.entity.Post;
import com.tchokoapps.springboot.blogrestapi.exception.BlogApiException;
import com.tchokoapps.springboot.blogrestapi.exception.ResourceNotFoundException;
import com.tchokoapps.springboot.blogrestapi.repository.CommentRepository;
import com.tchokoapps.springboot.blogrestapi.repository.PostRepository;
import com.tchokoapps.springboot.blogrestapi.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    @Override
    public CommentDto createCommentDto(long postId, @NonNull CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", Long.toString(postId)));
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public List<CommentDto> findComments(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", Long.toString(postId)));
        List<Comment> comments = commentRepository.findByPost(post);
        return comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
    }

    @Override
    public CommentDto findComment(long postId, long id) {
        Comment comment2 = find(postId, id);
        return modelMapper.map(comment2, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(long postId, long id, CommentDto commentDto) {
        Comment comment2 = find(postId, id);
        comment2.setEmail(commentDto.getEmail());
        comment2.setName(commentDto.getName());
        comment2.setBody(commentDto.getBody());
        Comment savedComment = commentRepository.save(comment2);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(long postId, long id) {
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", Long.toString(postId)));
        commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", Long.toString(id)));
        Comment comment = commentRepository.findByPostIdAndId(postId, id).orElseThrow(() ->
                new BlogApiException(HttpStatus.BAD_REQUEST, String.format("Comment with id=%s doesn´t belong to post with id=%s", id, postId)));
        commentRepository.delete(comment);
    }

    private Comment find(long postId, long id) {
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", Long.toString(postId)));
        commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", Long.toString(id)));
        return commentRepository.findByPostIdAndId(postId, id).orElseThrow(() ->
                new BlogApiException(HttpStatus.BAD_REQUEST, String.format("Comment with id=%s doesn´t belong to post with id=%s", id, postId)));
    }
}
