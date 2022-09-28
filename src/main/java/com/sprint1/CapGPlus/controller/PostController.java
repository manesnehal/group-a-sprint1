package com.sprint1.CapGPlus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.CapGPlus.entity.Post;
import com.sprint1.CapGPlus.service.PostService;

@RestController
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping("/post")
	public ResponseEntity<List<Post>> getAllPosts() {
		List<Post> list = postService.getAllPosts();
		return new ResponseEntity<List<Post>>(list, HttpStatus.FOUND);
	}

}
