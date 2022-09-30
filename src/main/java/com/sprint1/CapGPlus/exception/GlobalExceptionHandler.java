package com.sprint1.CapGPlus.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	// Admin Auth starts here
	@ExceptionHandler(value = InvalidCredentialsException.class)
	public ResponseEntity<String> invalidPassword(InvalidCredentialsException e) {
		return new ResponseEntity<>("Incorrect Credentials! Please Try Again!", HttpStatus.UNAUTHORIZED);
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

	// User feed starts here
	@ExceptionHandler(value = PostUnavailableException.class)
	public ResponseEntity<String> postUnavailable(PostUnavailableException e) {
		return new ResponseEntity<>("There are no posts in your communities", HttpStatus.NOT_FOUND);
	}

	// User feed ends here

	@ExceptionHandler(value = ActionRepititionException.class)
	public ResponseEntity<String> userNameAlreadyExists(ActionRepititionException e) {
		return new ResponseEntity<>("You have already liked the post. You cannot like again.", HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = CommentDoesNotExistException.class)
	public ResponseEntity<String> CommentDoesNotExist(CommentDoesNotExistException e) {
		return new ResponseEntity<>("Comment not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = NotAPartOfCommunityException.class)
	public ResponseEntity<String> NotAPartOfCommunity(NotAPartOfCommunityException e) {
		return new ResponseEntity<>("You are not a part of this community", HttpStatus.NOT_FOUND);
	}

	// Validation exception
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Map<String, List<String>> body = new HashMap<>();
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
		body.put("errors", errors);
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
