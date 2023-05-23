package com.lexisnexis.hiring.exception;

@SuppressWarnings("serial")
public class CommentIdNotFoundException extends RuntimeException{

	public CommentIdNotFoundException(String message) {
		super(message);
	}
}