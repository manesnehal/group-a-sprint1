package com.sprint1.CapGPlus.service;

import java.util.List;

import com.sprint1.CapGPlus.entity.DataHolder;
import com.sprint1.CapGPlus.entity.Post;
import com.sprint1.CapGPlus.entity.User;
import com.sprint1.CapGPlus.exception.ActionNotAllowedException;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.exception.InvalidCredentialsException;
import com.sprint1.CapGPlus.exception.PostNotFoundException;
import com.sprint1.CapGPlus.exception.UserNameAlreadyExistsException;
import com.sprint1.CapGPlus.exception.UserNotFoundException;

public interface UserService {

	public User saveUser(User user) throws UserNameAlreadyExistsException;

	public boolean userLogin(DataHolder dataHolder) throws InvalidCredentialsException;

	public User findByUserName(String UserName);

	public List<User> getAllUsers();

	public User getUserbyId(int userId) throws UserNotFoundException;

	// User post starts

	public List<Post> getAllUserPosts(int userId) throws UserNotFoundException;

	public Post createPost(int userId, Post post, int communityId)
			throws UserNotFoundException, CommunityNotFoundException, ActionNotAllowedException;

	public void deletePost(int userId, int postId)
			throws ActionNotAllowedException, UserNotFoundException, PostNotFoundException;

	public Post editPost(int userId, int postId, Post post)
			throws UserNotFoundException, PostNotFoundException, ActionNotAllowedException;

	// User post ends

	// User Feed starts here
	public List<Post> getAllPostsFromCommunities(int userId, String order);
	// User Feed ends here
}