package com.tchokoapps.springboot.blogrestapi.service;

import com.tchokoapps.springboot.blogrestapi.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createCommentDto(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);
}
