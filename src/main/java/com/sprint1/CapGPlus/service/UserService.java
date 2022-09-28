package com.sprint1.CapGPlus.service;

import java.util.List;

import com.sprint1.CapGPlus.entity.Post;
import com.sprint1.CapGPlus.exception.ActionNotAllowedException;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.exception.PostNotFoundException;
import com.sprint1.CapGPlus.exception.UserNotFoundException;

public interface UserService {
	// User post starts

	public List<Post> getAllUserPosts(int userId) throws UserNotFoundException;

	public Post createPost(int userId, Post post, int communityId)
			throws UserNotFoundException, CommunityNotFoundException;

	public void deletePost(int userId, int postId)
			throws ActionNotAllowedException, UserNotFoundException, PostNotFoundException;

	// User post ends
}
