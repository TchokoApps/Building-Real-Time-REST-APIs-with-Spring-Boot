package com.tchokoapps.springboot.blogrestapi.service;

import com.tchokoapps.springboot.blogrestapi.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createCommentDto(long postId, CommentDto commentDto);

    List<CommentDto> findComments(long postId);

    CommentDto findComment(long postId, long id);

    CommentDto updateComment(long postId, long id, CommentDto commentDto);

    void deleteComment(long postId, long id);
}
