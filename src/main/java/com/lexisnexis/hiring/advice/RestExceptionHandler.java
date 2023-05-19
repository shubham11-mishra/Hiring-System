package com.lexisnexis.hiring.advice;

import com.lexisnexis.hiring.exception.EmployeeAlreadyExistException;
import com.lexisnexis.hiring.exception.ErrorDescription;
import com.lexisnexis.hiring.exception.NoEmployeeFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.file.AccessDeniedException;
import java.util.Date;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = NoEmployeeFoundException.class)
    public ResponseEntity<ErrorDescription> handleNoJobAlertFountException() {
        ErrorDescription error = new ErrorDescription(400, "No Employee Found", new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = EmployeeAlreadyExistException.class)
    public ResponseEntity<ErrorDescription> handleEmployeeAlreadyExistException() {
        ErrorDescription error = new ErrorDescription(409, "Employee Already Found", new Date());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({AccessDeniedException.class, HttpClientErrorException.Unauthorized.class})
    public ResponseEntity<ErrorDescription> handleUnauthorizedException() {
        ErrorDescription error = new ErrorDescription(401, "UnAuthorized Employee Access", new Date());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

}
