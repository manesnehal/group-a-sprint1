package com.sprint1.CapGPlus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.CapGPlus.entity.Post;
import com.sprint1.CapGPlus.service.UserService;

@RestController
public class LikeController {
	@Autowired
	private UserService userService;

	@GetMapping("/user/{userId}/likes")
	private ResponseEntity<List<Post>> getAllPostsLikedByUser(@PathVariable int userId) {
		return new ResponseEntity<>(userService.getAllPostsLikedByUser(userId), HttpStatus.OK);
	}
}
