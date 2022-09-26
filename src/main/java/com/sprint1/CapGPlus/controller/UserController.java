package com.sprint1.CapGPlus.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sprint1.CapGPlus.entity.User;
import com.sprint1.CapGPlus.service.UserService;

public class UserController {

	@Autowired
	private UserService userServ;
	
	@GetMapping("/user/{firstName}")
	private ResponseEntity<List<User>> getUsersByFirstName(@PathVariable String firstName) {
		return new ResponseEntity<>(userServ.findByFirstName(firstName), HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	private ResponseEntity<Object> getEmployeeById(@PathVariable int id) {
		Optional<User> userData = userServ.getUserById(id);
		if (userData.isPresent())
			return new ResponseEntity<>(userData, HttpStatus.OK);
		else
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/users")
	private ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<>(userServ.getUsers(), HttpStatus.OK);
	}

	@PostMapping("/user")
	private ResponseEntity<String> saveUser(@RequestBody User emp) {
		userServ.saveUser(emp);
		return new ResponseEntity<>("User created", HttpStatus.CREATED);
	}

	@DeleteMapping("/users/{id}")
	private ResponseEntity<String> deleteuserById(@PathVariable int id) {
		if (userServ.deleteUserById(id))
			return new ResponseEntity<>("User deleted!", HttpStatus.NO_CONTENT);
		return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/users/{id}")
	private ResponseEntity<Object> updateUserById(@RequestBody User usr, @PathVariable int id) {
		User updatedUser = userServ.updateUserById(id, usr);
		if (updatedUser == null)
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
}