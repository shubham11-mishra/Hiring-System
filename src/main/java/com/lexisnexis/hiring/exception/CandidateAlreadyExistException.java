package com.lexisnexis.hiring.exception;

public class CandidateAlreadyExistException extends RuntimeException {
	public CandidateAlreadyExistException(String message){
        super(message);
    }
}
