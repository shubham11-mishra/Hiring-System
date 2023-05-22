package com.lexisnexis.hiring.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception{

	public UserNotFoundException(String message) {
		super(message);
	}
}