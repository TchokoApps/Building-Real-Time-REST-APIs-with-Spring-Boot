package com.tchokoapps.springboot.blogrestapi.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String body;
}
