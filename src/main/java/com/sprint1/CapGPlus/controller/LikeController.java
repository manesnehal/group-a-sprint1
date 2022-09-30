package com.sprint1.CapGPlus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.CapGPlus.dto.outer.PostDTOOuter;
import com.sprint1.CapGPlus.exception.UserNotFoundException;
import com.sprint1.CapGPlus.service.UserService;

@RestController
public class LikeController {
	@Autowired
	private UserService userService;

	// 1. Get all User's liked posts

	@GetMapping("/user/{userId}/likes")
	private ResponseEntity<List<PostDTOOuter>> getAllPostsLikedByUser(@PathVariable int userId)
			throws UserNotFoundException {
		return new ResponseEntity<List<PostDTOOuter>>(userService.getAllPostsLikedByUser(userId), HttpStatus.OK);
	}
}
