package com.tchokoapps.springboot.blogrestapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BlogApiException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;
}
