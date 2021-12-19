package com.tchokoapps.springboot.blogrestapi.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostDto {

    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Post title schould have at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post description schould have at least 10 characters")
    private String description;

    @NotEmpty
    private String content;

    private Set<CommentDto> commentDtos;
}
