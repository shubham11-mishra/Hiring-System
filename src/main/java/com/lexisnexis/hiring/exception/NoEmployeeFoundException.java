package com.lexisnexis.hiring.exception;

public class NoEmployeeFoundException extends RuntimeException{
    public NoEmployeeFoundException(String Message) {
        super(Message);
    }
}
