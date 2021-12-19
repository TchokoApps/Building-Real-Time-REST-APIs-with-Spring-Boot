package com.tchokoapps.springboot.blogrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
