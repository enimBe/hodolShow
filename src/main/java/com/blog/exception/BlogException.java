package com.blog.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

// 비즈니스에 해당하는 최상위 Exception으로 관리
@Getter
public abstract class BlogException extends RuntimeException {

    public final Map<String, String> validation = new HashMap<>();

    public BlogException(String message) {
        super(message);
    }

    public BlogException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
