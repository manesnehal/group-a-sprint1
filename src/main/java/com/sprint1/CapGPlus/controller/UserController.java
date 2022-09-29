package com.sprint1.CapGPlus.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.CapGPlus.dto.outer.PostDTOOuter;
import com.sprint1.CapGPlus.dto.outer.UserDTO;
import com.sprint1.CapGPlus.entity.Comment;
import com.sprint1.CapGPlus.entity.DataHolder;
import com.sprint1.CapGPlus.entity.Post;
import com.sprint1.CapGPlus.entity.User;
import com.sprint1.CapGPlus.exception.ActionNotAllowedException;
import com.sprint1.CapGPlus.exception.ActionRepititionException;
import com.sprint1.CapGPlus.exception.CommentDoesNotExistException;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.exception.InvalidCredentialsException;
import com.sprint1.CapGPlus.exception.PostNotFoundException;
import com.sprint1.CapGPlus.exception.PostUnavailableException;
import com.sprint1.CapGPlus.exception.UserNameAlreadyExistsException;
import com.sprint1.CapGPlus.exception.UserNotFoundException;
import com.sprint1.CapGPlus.service.CommunityService;
import com.sprint1.CapGPlus.service.PostService;
import com.sprint1.CapGPlus.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private CommunityService communityService;
	@Autowired
	private PostService postService;

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
	private ResponseEntity<Object> getCommunitiesbyUserId(@PathVariable int userId) throws UserNotFoundException {
		return new ResponseEntity<>(communityService.getCommunitiesbyUserId(userId), HttpStatus.OK);
	}

	// User community ends

	// User-Auth starts
	@PostMapping("/user")
	public ResponseEntity<String> saveUser(@Valid @RequestBody User user) throws UserNameAlreadyExistsException {
		userService.saveUser(user);
		return new ResponseEntity<String>("User successfully created", HttpStatus.CREATED);
	}

	@PostMapping("/user/login")
	public ResponseEntity<String> userLogin(@Valid @RequestBody DataHolder dataHolder)
			throws InvalidCredentialsException {
		if (userService.userLogin(dataHolder))
			return new ResponseEntity<String>("Logged in succcessfully", HttpStatus.FOUND);
		return new ResponseEntity<String>("Log in failed", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> list = userService.getAllUsers();
		return new ResponseEntity<List<UserDTO>>(list, HttpStatus.FOUND);
	}
	// User-Auth ends

	@GetMapping("/user/{userId}")
	private ResponseEntity<Object> getUserById(@PathVariable int userId) throws UserNotFoundException {
		return new ResponseEntity<>(userService.getUserbyId(userId), HttpStatus.OK);
	}

	// User posts starts

	@GetMapping("/user/{userId}/post")
	private ResponseEntity<List<PostDTOOuter>> getUserPosts(@PathVariable int userId) throws UserNotFoundException {
		return new ResponseEntity<>(userService.getAllUserPosts(userId), HttpStatus.OK);
	}

	@PostMapping("/user/{userId}/{communityId}/post")
	private ResponseEntity<Post> createPostInCommunity(@PathVariable int userId, @PathVariable int communityId,
			@RequestBody Post post)
			throws UserNotFoundException, CommunityNotFoundException, ActionNotAllowedException {
		return new ResponseEntity<>(userService.createPost(userId, post, communityId), HttpStatus.CREATED);
	}

	@PutMapping("/user/{userId}/post/{postId}")
	private ResponseEntity<Post> editPostByPostId(@PathVariable int userId, @PathVariable int postId,
			@RequestBody Post post) throws UserNotFoundException, PostNotFoundException, ActionNotAllowedException {
		return new ResponseEntity<>(userService.editPost(userId, postId, post), HttpStatus.OK);
	}

	@DeleteMapping("/user/{userId}/post/{postId}")
	private ResponseEntity<String> deletePostByPostId(@PathVariable int userId, @PathVariable int postId)
			throws ActionNotAllowedException, UserNotFoundException, PostNotFoundException {
		userService.deletePost(userId, postId);

		return new ResponseEntity<>("Post deleted!", HttpStatus.OK);

	}
	// User posts ends

	// User Feed starts here
	@GetMapping("/user/{userId}/feed")
	private ResponseEntity<Object> getFeed(@PathVariable int userId)
			throws UserNotFoundException, PostUnavailableException {
		List<PostDTOOuter> p = userService.getAllPostsFromCommunities(userId);
		if (p == null) {
			return new ResponseEntity<Object>("You haven't joined any community", HttpStatus.FOUND);
		}
		return new ResponseEntity<Object>(p, HttpStatus.FOUND);
	}
	// User Feed ends here

	// User Like starts
	@PostMapping("/user/{userId}/post/{postId}/like")
	private ResponseEntity<String> likeAPost(@PathVariable int userId, @PathVariable int postId)
			throws UserNotFoundException, PostNotFoundException, ActionRepititionException {
		userService.likeAPost(userId, postId);
		return new ResponseEntity<String>("You have liked the post", HttpStatus.ACCEPTED);
	}

	@PostMapping("/user/{userId}/post/{postId}/unlike")
	private ResponseEntity<String> unlikeAPost(@PathVariable int userId, @PathVariable int postId)
			throws UserNotFoundException, PostNotFoundException, ActionNotAllowedException {
		userService.unlikeAPost(userId, postId);
		return new ResponseEntity<String>("You have unliked the post", HttpStatus.ACCEPTED);
	}

	// User like ends

	@PostMapping("/user/{userId}/post/{postId}/comment")
	private ResponseEntity<String> commentOnPost(@PathVariable int postId, @PathVariable int userId,
			@RequestBody Comment comment) throws PostNotFoundException, UserNotFoundException {
		userService.commentOnPost(postId, userId, comment);
		return new ResponseEntity<String>("Comment added to the post", HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/user/{userId}/post/{postId}/comment/{commentId}")
	private ResponseEntity<String> deleteComment(@PathVariable int postId, @PathVariable int userId,
			@PathVariable int commentId) throws UserNotFoundException, PostNotFoundException, ActionNotAllowedException,
			CommentDoesNotExistException {
		userService.deleteComment(postId, userId, commentId);
		return new ResponseEntity<String>("Comment Deleted", HttpStatus.OK);
	}

}