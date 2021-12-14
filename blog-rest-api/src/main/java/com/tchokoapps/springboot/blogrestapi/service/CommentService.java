package com.tchokoapps.springboot.blogrestapi.service;

import com.tchokoapps.springboot.blogrestapi.dto.CommentDto;

public interface CommentService {
    CommentDto createCommentDto(long postId, CommentDto commentDto);
}
