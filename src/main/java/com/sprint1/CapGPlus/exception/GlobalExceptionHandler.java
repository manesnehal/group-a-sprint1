package com.sprint1.CapGPlus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = CommunityNotFoundException.class)
	public ResponseEntity<String> communityNotFound(CommunityNotFoundException e) {
		return new ResponseEntity<>("Community not found", HttpStatus.NOT_FOUND);
	}
}
