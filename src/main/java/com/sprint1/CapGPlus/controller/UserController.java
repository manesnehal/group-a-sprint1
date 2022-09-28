package com.sprint1.CapGPlus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.CapGPlus.entity.Community;
import com.sprint1.CapGPlus.exception.CommunityAlreadyExistsException;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.service.CommunityService;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.sprint1.CapGPlus.entity.Post;
import com.sprint1.CapGPlus.exception.ActionNotAllowedException;
import com.sprint1.CapGPlus.exception.PostNotFoundException;
import com.sprint1.CapGPlus.exception.UserNotFoundException;
import com.sprint1.CapGPlus.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private CommunityService communityService;

	// User community starts

	@PostMapping("/user/{userId}/community/join/{communityId}")
	private ResponseEntity<Object> joinCommunity(@PathVariable int userId, @PathVariable int communityId)
			throws CommunityNotFoundException, UserNotFoundException {
		return new ResponseEntity<>(communityService.joinCommunity(userId, communityId), HttpStatus.OK);
	}

	@PostMapping("/user/{userId}/community/leave/{communityId}")
	private ResponseEntity<Object> leaveCommunity(@PathVariable int userId, @PathVariable int communityId)
			throws CommunityNotFoundException, UserNotFoundException {
		return new ResponseEntity<>(communityService.leaveCommunity(userId, communityId), HttpStatus.OK);
	}

	@GetMapping("/user/{userId}/community")
	private ResponseEntity<List<Community>> getCommunitiesbyUserId(@PathVariable int userId)
			throws UserNotFoundException {
		return new ResponseEntity<>(communityService.getCommunitiesbyUserId(userId), HttpStatus.OK);
	}

	// User community ends

	// User posts starts

	@GetMapping("/user/{userId}/post")
	private ResponseEntity<List<Post>> getUserPosts(@PathVariable int userId) throws UserNotFoundException {
		return new ResponseEntity<>(userService.getAllUserPosts(userId), HttpStatus.OK);
	}

	@PostMapping("/user/{userId}/{communityId}/post")
	private ResponseEntity<Post> createPostInCommunity(@PathVariable int userId, @PathVariable int communityId,
			@RequestBody Post post) throws UserNotFoundException, CommunityNotFoundException {
		return new ResponseEntity<>(userService.createPost(userId, post, communityId), HttpStatus.CREATED);
	}

	@DeleteMapping("/user/{userId}/post/{postId}")
	private ResponseEntity<String> deletePostByPostId(@PathVariable int userId, @PathVariable int postId)
			throws ActionNotAllowedException, UserNotFoundException, PostNotFoundException {
		userService.deletePost(userId, postId);
		return new ResponseEntity<>("Post deleted!", HttpStatus.NO_CONTENT);
	}
	// User posts ends

}