package com.lexisnexis.hiring.exception;

public class EmployeeAlreadyExistException extends RuntimeException{
    public EmployeeAlreadyExistException(String Message) {
        super(Message);
    }
}