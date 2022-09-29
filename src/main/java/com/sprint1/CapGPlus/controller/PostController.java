package com.sprint1.CapGPlus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.CapGPlus.dto.outer.PostDTOOuter;
import com.sprint1.CapGPlus.exception.PostNotFoundException;
import com.sprint1.CapGPlus.service.PostService;

@RestController
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping("/post")
	public ResponseEntity<Object> getAllPosts() {
		List<PostDTOOuter> list = postService.getAllPosts();
		return new ResponseEntity<Object>(list, HttpStatus.FOUND);
	}

	
	@GetMapping("/post/{postId}")
	private ResponseEntity<Object> getPostById(@PathVariable int postId)
			throws PostNotFoundException {
		return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
	}
	/*
	 * @GetMapping("/posts") private ResponseEntity<List<Post>> getAllPosts(){
	 * return new
	 * ResponseEntity<List<Post>>(postService.getAllPosts(),HttpStatus.FOUND); }
	 */

}
