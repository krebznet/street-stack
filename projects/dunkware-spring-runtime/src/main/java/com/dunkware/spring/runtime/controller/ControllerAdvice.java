package com.dunkware.spring.runtime.controller;


import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
	
	
	@ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ControllerError> gobalUserExceptionHandler(UserException ex) { 
		ControllerError message = new ControllerError(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getMessage());

        return new ResponseEntity<ControllerError>(message, HttpStatus.BAD_REQUEST);
	}
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ControllerError> globalExceptionHandler(Exception ex) {
        ControllerError message = new ControllerError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getMessage());

        return new ResponseEntity<ControllerError>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
