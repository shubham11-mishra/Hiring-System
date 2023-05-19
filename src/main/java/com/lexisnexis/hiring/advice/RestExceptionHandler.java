package com.lexisnexis.hiring.advice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lexisnexis.hiring.exception.InvalidEmployeeID;

@RestController
@ControllerAdvice
public class RestExceptionHandler {
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InvalidEmployeeID.class)
    public Map<String, String> userNotLogin(InvalidEmployeeID invalidEmployeeId) {
        final Map<String, String> errorMap = new ConcurrentHashMap<>();
        errorMap.put("errorMessage", invalidEmployeeId.getMessage());
        return errorMap;
    }

}
