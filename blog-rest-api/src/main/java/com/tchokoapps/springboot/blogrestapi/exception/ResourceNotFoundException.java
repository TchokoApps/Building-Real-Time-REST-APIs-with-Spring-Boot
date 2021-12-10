package com.tchokoapps.springboot.blogrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private Long fieldValue;
    private String fieldName2;
    private Long fieldValue2;

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s not found with %s: %d", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue, String fieldName2, Long fieldValue2) {
        super(String.format("%s not found with %s: %d, %s: %d", resourceName, fieldName, fieldValue, fieldName2, fieldValue2));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.fieldName2 = fieldName2;
        this.fieldValue2 = fieldValue2;
    }


}
