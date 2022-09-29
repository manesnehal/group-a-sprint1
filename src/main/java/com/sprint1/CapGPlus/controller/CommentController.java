package com.sprint1.CapGPlus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.CapGPlus.entity.Comment;
import com.sprint1.CapGPlus.service.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/user/{userId}/comments")
	private ResponseEntity<List<Comment>> getAllCommentsByUser(@PathVariable int userId){
		List<Comment> list = commentService.getAllCommentsByUser(userId);
		return new ResponseEntity<List<Comment>>(list,HttpStatus.FOUND);
	}
}
