package com.lexisnexis.hiring.advice;

import com.lexisnexis.hiring.exception.CandidateAlreadyExistException;
import com.lexisnexis.hiring.exception.CandidateDoesNotExistException;
import com.lexisnexis.hiring.exception.ErrorDescription;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value = CandidateAlreadyExistException.class)
	public ResponseEntity<ErrorDescription> candidateAlreadyExist(
			CandidateAlreadyExistException candidateAlreadyExistException) {
		ErrorDescription errorDescription = new ErrorDescription(404, "Candidate are already exist", new Date());
		return new ResponseEntity<ErrorDescription>(errorDescription, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = CandidateDoesNotExistException.class)
	public ResponseEntity<ErrorDescription> candidateDoesntExist(
			CandidateAlreadyExistException candidateAlreadyExistException) {
		ErrorDescription errorDescription = new ErrorDescription(404, "Candidate are not found", new Date());
		return new ResponseEntity<ErrorDescription>(errorDescription, HttpStatus.BAD_REQUEST);
	}

}
