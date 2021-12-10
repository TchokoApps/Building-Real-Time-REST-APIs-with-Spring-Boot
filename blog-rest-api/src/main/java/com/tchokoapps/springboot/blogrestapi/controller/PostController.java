package com.tchokoapps.springboot.blogrestapi.controller;

import com.tchokoapps.springboot.blogrestapi.dto.PostDto;
import com.tchokoapps.springboot.blogrestapi.dto.PostResponse;
import com.tchokoapps.springboot.blogrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {

    public static final String PAGE_NO = "pageNo";
    public static final String PAGE_SIZE = "pageSize";
    public static final String SORT_BY = "sortBy";
    public static final String SORT_DIR = "sortDir";
    public static final String PAGE_NO_DEFAULT_VALUE = "0";
    public static final String PAGE_SIZE_DEFAULT_VALUE = "2";
    public static final String SORT_BY_DEFAULT_VALUE = "id";
    public static final String SORT_DIR_DEFAULT_VALUE = "asc";
    public static final String POST_ID_PARAM = "id";

    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody @NonNull PostDto postDto) {
        return new ResponseEntity<>(postService.createPostDto(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/pages")
    public PostResponse getAllPostsByPage(
            @RequestParam(value = PAGE_NO, defaultValue = PAGE_NO_DEFAULT_VALUE, required = false) int pageNo,
            @RequestParam(value = PAGE_SIZE, defaultValue = PAGE_SIZE_DEFAULT_VALUE, required = false) int pageSize,
            @RequestParam(value = SORT_BY, defaultValue = SORT_BY_DEFAULT_VALUE, required = false) String sortBy,
            @RequestParam(value = SORT_DIR, defaultValue = SORT_DIR_DEFAULT_VALUE, required = false) String sortDir) {
        return postService.getAllPostsByPage(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = POST_ID_PARAM) long id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable(name = POST_ID_PARAM) long id) {
        PostDto updatedPostDto = postService.updatePostById(postDto, id);
        return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = POST_ID_PARAM) long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
