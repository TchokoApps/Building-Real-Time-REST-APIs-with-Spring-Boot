package com.tchokoapps.springboot.blogrestapi.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostResponse {
    private List<PostDto> postDtos;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private boolean last;
}
