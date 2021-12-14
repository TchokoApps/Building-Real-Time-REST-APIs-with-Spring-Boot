package com.tchokoapps.springboot.blogrestapi.controller;

import com.tchokoapps.springboot.blogrestapi.dto.CommentDto;
import com.tchokoapps.springboot.blogrestapi.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tchokoapps.springboot.blogrestapi.dto.Constant.COMMENT_ID_PARAM;
import static com.tchokoapps.springboot.blogrestapi.dto.Constant.POST_ID_PARAM;

@AllArgsConstructor
@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = POST_ID_PARAM) long postId,
                                                    @RequestBody @NonNull CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createCommentDto(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable(value = POST_ID_PARAM) long postId) {
        return new ResponseEntity<>(commentService.findCommentsByPostId(postId), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentByPostIdAndId(@PathVariable(value = POST_ID_PARAM) long postId,
                                                              @PathVariable(value = COMMENT_ID_PARAM) long id) {
        return new ResponseEntity<>(commentService.findByPostIdAndId(postId, id), HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentByPostIdAndId(@PathVariable(value = POST_ID_PARAM) long postId,
                                                                 @PathVariable(value = COMMENT_ID_PARAM) long id,
                                                                 @RequestBody @NonNull CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateByPostIdAndId(postId, id, commentDto), HttpStatus.CREATED);
    }
}
