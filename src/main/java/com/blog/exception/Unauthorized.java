package com.blog.exception;

/**
 * status -> 401
 */
public class Unauthorized extends BlogException{

    private static final String MESSAGE = "인증이 필요합니다.";

    public Unauthorized() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }

}