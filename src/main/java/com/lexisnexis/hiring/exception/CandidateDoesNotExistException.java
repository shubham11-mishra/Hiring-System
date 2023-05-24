package com.lexisnexis.hiring.exception;

public class CandidateDoesNotExistException extends RuntimeException {
    public CandidateDoesNotExistException(String Message) {
        super(Message);
    }
}
