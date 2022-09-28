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

	// Permission exceptions
	@ExceptionHandler(value = ActionNotAllowedException.class)
	public ResponseEntity<String> actionNotAllowed(ActionNotAllowedException e) {
		return new ResponseEntity<>("This action is not allowed", HttpStatus.UNAUTHORIZED);
	}

	// Post exceptions
	@ExceptionHandler(value = PostNotFoundException.class)
	public ResponseEntity<String> postNotFound(PostNotFoundException e) {
		return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
	}

	// User exceptions
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<String> userNotFound(UserNotFoundException e) {
		return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	}

	// Community exceptions
	@ExceptionHandler(value = CommunityNotFoundException.class)
	public ResponseEntity<String> communityNotFound(CommunityNotFoundException e) {
		return new ResponseEntity<>("Community not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = CommunityAlreadyExistsException.class)
	public ResponseEntity<String> communityAlreadyExists(CommunityAlreadyExistsException e) {
		return new ResponseEntity<>("Community already exists", HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = UserNameAlreadyExistsException.class)
	public ResponseEntity<String> userNameAlreadyExists(UserNameAlreadyExistsException e) {
		return new ResponseEntity<>("Username already exists!! Try with different username", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = ActionRepititionException.class)
	public ResponseEntity<String> userNameAlreadyExists(ActionRepititionException e) {
		return new ResponseEntity<>("You have already liked the post. You cannot like again.", HttpStatus.CONFLICT);
	}
}
