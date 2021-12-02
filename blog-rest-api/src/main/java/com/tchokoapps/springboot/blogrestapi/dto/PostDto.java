package com.tchokoapps.springboot.blogrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {

    private Long id;

    private String title;

    private String description;

    private String content;
}
