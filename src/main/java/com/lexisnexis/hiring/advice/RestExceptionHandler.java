package com.lexisnexis.hiring.advice;

import com.lexisnexis.hiring.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.file.AccessDeniedException;
import java.util.Date;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = NoJobFoundException.class)
    public ResponseEntity<ErrorDescription> handleNoJobFoundException(NoJobFoundException noJobFoundException) {
        ErrorDescription error = new ErrorDescription(400, noJobFoundException.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoEmployeeFoundException.class )
    public ResponseEntity<ErrorDescription> handleNoJobAlertFountException(NoEmployeeFoundException noEmployeeFoundException) {
        ErrorDescription error = new ErrorDescription(400, noEmployeeFoundException.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class )
    public ResponseEntity<ErrorDescription> handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException) {
        ErrorDescription error = new ErrorDescription(400, usernameNotFoundException.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = EmployeeAlreadyExistException.class)
    public ResponseEntity<ErrorDescription> handleEmployeeAlreadyExistException(EmployeeAlreadyExistException employeeAlreadyExistException) {
        ErrorDescription error = new ErrorDescription(409, employeeAlreadyExistException.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({AccessDeniedException.class, HttpClientErrorException.Unauthorized.class})
    public ResponseEntity<ErrorDescription> handleUnauthorizedException(AccessDeniedException accessDeniedException, HttpClientErrorException.Unauthorized unauthorized) {
        ErrorDescription error = new ErrorDescription(401, accessDeniedException.getMessage() + " " + unauthorized.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = CandidateAlreadyExistException.class)
    public ResponseEntity<ErrorDescription> candidateAlreadyExist(
            CandidateAlreadyExistException candidateAlreadyExistException) {
        ErrorDescription errorDescription = new ErrorDescription(404, candidateAlreadyExistException.getMessage(), new Date());
        return new ResponseEntity<>(errorDescription, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CandidateDoesNotExistException.class)
    public ResponseEntity<ErrorDescription> handleCandidateDoesNotExistException(CandidateDoesNotExistException candidateDoesNotExistException) {
        ErrorDescription error = new ErrorDescription(409, candidateDoesNotExistException.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = CommentIdNotFoundException.class)
    public ResponseEntity<ErrorDescription> commentDoesntNullPointer(
            CommentIdNotFoundException commentIdNotFoundException) {
        ErrorDescription errorDescription = new ErrorDescription(400, commentIdNotFoundException.getMessage(), new Date());
        return new ResponseEntity<>(errorDescription, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoCommentsFoundException.class)
    public ResponseEntity<ErrorDescription> handleNoCommentsFoundException(
            NoCommentsFoundException commentNotFoundException) {
        ErrorDescription errorDescription = new ErrorDescription(400, commentNotFoundException.getMessage(), new Date());
        return new ResponseEntity<>(errorDescription, HttpStatus.BAD_REQUEST);
    }
}
