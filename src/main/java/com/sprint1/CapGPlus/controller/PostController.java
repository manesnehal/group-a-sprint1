package com.sprint1.CapGPlus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.CapGPlus.entity.Post;
import com.sprint1.CapGPlus.service.PostService;

@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
//	@GetMapping("/users")
	//@RequestMapping(name="/post", method = RequestMethod.GET)
	@GetMapping("/post")
	public ResponseEntity<List<Post>> getAllPosts() {
		List<Post> list = postService.getAllPosts();
		return new ResponseEntity<List<Post>>(list, HttpStatus.FOUND);
	}

}
