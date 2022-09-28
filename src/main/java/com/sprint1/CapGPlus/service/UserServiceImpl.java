package com.sprint1.CapGPlus.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.entity.Community;
import com.sprint1.CapGPlus.entity.Post;
import com.sprint1.CapGPlus.entity.User;
import com.sprint1.CapGPlus.exception.ActionNotAllowedException;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.exception.PostNotFoundException;
import com.sprint1.CapGPlus.exception.UserNotFoundException;
import com.sprint1.CapGPlus.repository.CommunityRepository;
import com.sprint1.CapGPlus.repository.PostRepository;
import com.sprint1.CapGPlus.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommunityRepository communityRepository;

	@Autowired
	private PostRepository postRepository;

	// User post starts

	@Override
	public List<Post> getAllUserPosts(int userId) throws UserNotFoundException {
		if (!userRepository.existsById(userId))
			throw new UserNotFoundException();
		return userRepository.findById(userId).get().getPosts();
	}

	@Override
	public Post createPost(int userId, Post post, int communityId)
			throws UserNotFoundException, CommunityNotFoundException {
		// Check if user exists
		// TODO JWT AUTH if (!userRepository.existsById(post.getUser().getId()))
		if (!userRepository.existsById(userId))
			throw new UserNotFoundException();

		// Get user
		// TODO User user = userRepository.findById(post.getUser().getId()).get();
		User user = userRepository.findById(userId).get();

		// Check if community exists
		if (!communityRepository.existsById(communityId))
			throw new CommunityNotFoundException();

		// TODO Check if user is part of that community

		// Get community
		Community community = communityRepository.findById(communityId).get();

		// Set community and user in the post
		post.setCommunity(community);
		post.setUser(user);

		// Add post to that user and community
		user.getPosts().add(post);
		community.getPosts().add(post);

		// Set postedAt in post
		LocalDateTime postedAt = LocalDateTime.now();
		post.setPostedAt(postedAt);

		// Save post, user and community
		userRepository.save(user);
		communityRepository.save(community);
		return postRepository.save(post);
	}

	@Override
	public void deletePost(int userId, int postId)
			throws ActionNotAllowedException, UserNotFoundException, PostNotFoundException {
		// Check if user exists
		// TODO JWT AUTH if (!userRepository.existsById(post.getUser().getId()))
		if (!userRepository.existsById(userId))
			throw new UserNotFoundException();

		// Get user
		// TODO User user = userRepository.findById(post.getUser().getId()).get();
		User user = userRepository.findById(userId).get();

		// Check if post exists
		if (!postRepository.existsById(postId))
			throw new PostNotFoundException();

		// Get post
		Post post = postRepository.findById(postId).get();

		// Check if user has permission to delete this post
		if (post.getUser().getId() != user.getId())
			throw new ActionNotAllowedException();

		// Get community where post was posted
		Community community = post.getCommunity();

		// Remove post from user, and community
		user.getPosts().remove(post);
		community.getPosts().remove(post);

		// Delete post
		postRepository.deleteById(postId);
	}

	// User post ends
	// User Feed starts here
	@Override
	public List<Post> getAllPostsFromCommunities(int userId, String order) {
		List<Post> p = null;
		User u = userRepository.findById(userId).get();
		Set<Community> c = u.getCommunities();
		for (Community community : c) {
			p.addAll(postRepository.getAllPostsByCommunity(community, order));
		}
		return p;
	}
	// User Feed ends here
}