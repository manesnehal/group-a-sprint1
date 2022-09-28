package com.sprint1.CapGPlus.exception;

public class UserNameAlreadyExistsException extends Exception {

	public UserNameAlreadyExistsException() {
		super();
	}

	public UserNameAlreadyExistsException(String msg) {
		super(msg);
	}
}
