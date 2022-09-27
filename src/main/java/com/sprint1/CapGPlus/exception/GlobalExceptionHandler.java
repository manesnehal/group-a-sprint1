package com.sprint1.CapGPlus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	// Admin Auth starts here
	@ExceptionHandler(value = InvalidCredentialsException.class)
	public ResponseEntity<String> invalidPassword(InvalidCredentialsException e) {
		return new ResponseEntity<>("Incorrect Credentials! Please Try Again!", HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = PasswordMatchException.class)
	public ResponseEntity<String> passwordMatch(PasswordMatchException e) {
		return new ResponseEntity<>("New Password cannot be same as Current Password", HttpStatus.CONFLICT);
	}
	// Admin Auth ends here

	@ExceptionHandler(value = CommunityNotFoundException.class)
	public ResponseEntity<String> communityNotFound(CommunityNotFoundException e) {
		return new ResponseEntity<>("Community not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = CommunityAlreadyExistsException.class)
	public ResponseEntity<String> communityNotFound(CommunityAlreadyExistsException e) {
		return new ResponseEntity<>("Community already exists", HttpStatus.CONFLICT);
	}
}
