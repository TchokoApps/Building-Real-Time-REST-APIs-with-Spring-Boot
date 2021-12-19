package com.tchokoapps.springboot.blogrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private String fieldName2;
    private String fieldValue;
    private String fieldValue2;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue, String fieldName2, String fieldValue2) {
        super(String.format("%s not found with %s: %s, %s: %s", resourceName, fieldName, fieldValue, fieldName2, fieldValue2));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.fieldName2 = fieldName2;
        this.fieldValue2 = fieldValue2;
    }

}
