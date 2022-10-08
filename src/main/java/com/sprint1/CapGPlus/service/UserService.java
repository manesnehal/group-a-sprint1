package com.sprint1.CapGPlus.service;

import java.util.List;

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

public interface UserService {

	// User Auth starts
	public User saveUser(User user) throws UserNameAlreadyExistsException;

	public boolean userLogin(DataHolder dataHolder) throws InvalidCredentialsException;

	public UserDTO findByUserName(String UserName);

	public List<UserDTO> getAllUsers();

	public List<UserDTO> searchForUserByUsername(String searchQuery);

	// User Auth ends

	public UserDTO getUserbyId(int userId) throws UserNotFoundException;

	// User Post starts
	public List<PostDTOOuter> getAllUserPosts(int userId) throws UserNotFoundException;

	public PostDTOOuter createPost(int userId, Post post, int communityId)
			throws UserNotFoundException, CommunityNotFoundException, ActionNotAllowedException;

	public void deletePost(int userId, int postId)
			throws ActionNotAllowedException, UserNotFoundException, PostNotFoundException;

	public PostDTOOuter editPost(int userId, int postId, Post post)
			throws UserNotFoundException, PostNotFoundException, ActionNotAllowedException;

	public Post likeAPost(int userId, int postId)
			throws UserNotFoundException, PostNotFoundException, ActionRepititionException;

	public Post unlikeAPost(int userId, int postId)
			throws UserNotFoundException, PostNotFoundException, ActionNotAllowedException;

	// User post ends

	// User Feed starts here
	public List<PostDTOOuter> getAllPostsFromCommunities(int userId)
			throws UserNotFoundException, PostUnavailableException;
	// User Feed ends here

	public Comment commentOnPost(int postId, int UserId, Comment comment)
			throws PostNotFoundException, UserNotFoundException, ActionNotAllowedException;

	public void deleteComment(int postId, int userId, int commentId) throws UserNotFoundException,
			PostNotFoundException, ActionNotAllowedException, CommentDoesNotExistException;

	public List<PostDTOOuter> getAllPostsLikedByUser(int userId) throws UserNotFoundException;

	// User following starts here
	public String followUser(int userId, int followingId) throws ActionNotAllowedException, UserNotFoundException;

	public String unfollowUser(int userId, int followingId) throws ActionNotAllowedException, UserNotFoundException;

	public List<UserDTO> getFollowers(int userId) throws UserNotFoundException;

	public List<UserDTO> getFollowing(int userId) throws UserNotFoundException;

	public List<PostDTOOuter> getFeedOfFollowingUsers(int userId) throws UserNotFoundException;
	// User following ends here
}