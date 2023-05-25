package com.lexisnexis.hiring.exception;

public class NoJobFoundException extends RuntimeException {
	public NoJobFoundException(String message) {
        super(message);
   }
}
