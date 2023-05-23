package com.lexisnexis.hiring.exception;
public class CommentIdNotFoundException extends RuntimeException {
    public CommentIdNotFoundException(String message) {
        super(message);
    }
}