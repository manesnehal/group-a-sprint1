package com.sprint1.CapGPlus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.CapGPlus.entity.DataHolder;
import com.sprint1.CapGPlus.entity.User;
import com.sprint1.CapGPlus.exception.InvalidCredentialsException;
import com.sprint1.CapGPlus.exception.UserNameAlreadyExistsException;
import com.sprint1.CapGPlus.service.UserService;

import java.util.List;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public ResponseEntity<String> saveUser(@RequestBody User user) throws UserNameAlreadyExistsException {
		userService.saveUser(user);
		return new ResponseEntity<String>("User successfully created", HttpStatus.CREATED);
	}

	@PostMapping("/user/login")
	public ResponseEntity<String> userLogin(@RequestBody DataHolder dataHolder) throws InvalidCredentialsException {
		if (userService.userLogin(dataHolder))
			return new ResponseEntity<String>("Logged in succcessfully", HttpStatus.FOUND);
		return new ResponseEntity<String>("Log in failed", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = userService.getAllUsers();
		return new ResponseEntity<List<User>>(list, HttpStatus.FOUND);
	}
}