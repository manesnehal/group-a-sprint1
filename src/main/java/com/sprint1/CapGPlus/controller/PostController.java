package com.sprint1.CapGPlus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.CapGPlus.dto.PostDTO;
import com.sprint1.CapGPlus.service.PostService;

@RestController
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping("/post")
	public ResponseEntity<Object> getAllPosts() {
		List<PostDTO> list = postService.getAllPosts();
		return new ResponseEntity<Object>(list, HttpStatus.FOUND);
	}

}
