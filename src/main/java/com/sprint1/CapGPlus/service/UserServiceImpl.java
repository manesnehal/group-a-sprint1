package com.sprint1.CapGPlus.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.dto.PostDTO;
import com.sprint1.CapGPlus.dto.UserDTO;
import com.sprint1.CapGPlus.entity.Comment;
import com.sprint1.CapGPlus.entity.Community;
import com.sprint1.CapGPlus.entity.DataHolder;
import com.sprint1.CapGPlus.entity.Post;
import com.sprint1.CapGPlus.entity.User;
import com.sprint1.CapGPlus.exception.ActionNotAllowedException;
import com.sprint1.CapGPlus.exception.ActionRepititionException;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.exception.InvalidCredentialsException;
import com.sprint1.CapGPlus.exception.PostNotFoundException;
import com.sprint1.CapGPlus.exception.PostUnavailableException;
import com.sprint1.CapGPlus.exception.UserNameAlreadyExistsException;
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

	@Autowired
	private UserDTOService userDTOService;

	@Autowired
	private PostDTOService postDTOService;

	PasswordEncoder passwordEncoder;

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Override
	public User saveUser(User user) throws UserNameAlreadyExistsException {
		if (userRepository.findByUserName(user.getUserName()) != null)
			throw new UserNameAlreadyExistsException();
		String encodedPassword = this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		System.out.println(user.getFirstName());
		return userRepository.save(user);
	}

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public boolean userLogin(DataHolder dataHolder) throws InvalidCredentialsException {
		if (userRepository.findByUserName(dataHolder.getUserName()) == null
				|| !bCryptPasswordEncoder.matches(dataHolder.getPassword(),
						userRepository.findByUserName(dataHolder.getUserName()).getPassword()))
			throw new InvalidCredentialsException();
		return bCryptPasswordEncoder.matches(dataHolder.getPassword(),
				userRepository.findByUserName(dataHolder.getUserName()).getPassword());
	}

	@Override
	public UserDTO findByUserName(String userName) {
		return userDTOService.convertToDTO(userRepository.findByUserName(userName));
	}

	@Override
	public List<UserDTO> getAllUsers() {
		return userRepository.findAll().stream().map(userDTOService::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public UserDTO getUserbyId(int userId) throws UserNotFoundException {
		if (!userRepository.existsById(userId))
			throw new UserNotFoundException();
		return userDTOService.convertToDTO(userRepository.findById(userId).get());
	}

	// User post starts

	@Override
	public List<PostDTO> getAllUserPosts(int userId) throws UserNotFoundException {
		if (!userRepository.existsById(userId))
			throw new UserNotFoundException();
		return userRepository.findById(userId).get().getPosts().stream().map(postDTOService::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Post createPost(int userId, Post post, int communityId)

			throws UserNotFoundException, CommunityNotFoundException, ActionNotAllowedException {
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

		// Get community
		Community community = communityRepository.findById(communityId).get();

		// Check if user is part of that community
		if (!community.getUsers().contains(user))
			throw new ActionNotAllowedException();

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

	@Override
	public Post editPost(int userId, int postId, Post post)
			throws UserNotFoundException, PostNotFoundException, ActionNotAllowedException {
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
		Post oldPost = postRepository.findById(postId).get();

		// Check if user has permission to edit this post
		if (oldPost.getUser().getId() != user.getId())
			throw new ActionNotAllowedException();

		// Only title and content can be changed
		// Set updated title and content in the post already present in the repository
		oldPost.setTitle(post.getTitle());
		oldPost.setContent(post.getContent());

		return postRepository.save(oldPost);
	}

	// User post ends
	// User Feed starts here
	@Override
	public List<PostDTO> getAllPostsFromCommunities(int userId) throws UserNotFoundException, PostUnavailableException {
		try {
			User u = userRepository.findById(userId).get();
			if (u.getCommunities().isEmpty()) {
				return null;
			}
		} catch (Exception e) {
			throw new UserNotFoundException();
		}
		List<PostDTO> p = postRepository.getAllPostsByCommunity(userId).stream().map(postDTOService::convertToDTO)
				.collect(Collectors.toList());
		if (p.isEmpty()) {
			throw new PostUnavailableException();
		}

		return p;
	}
	// User Feed ends here

	@Override
	public Post likeAPost(int userId, int postId)
			throws UserNotFoundException, PostNotFoundException, ActionRepititionException {
		if (!userRepository.existsById(userId))
			throw new UserNotFoundException();
		if (!postRepository.existsById(postId))
			throw new PostNotFoundException();
		Set<User> set = postRepository.findById(postId).get().getLikedBy();
		User u = userRepository.findById(userId).get();
		if (set.contains(u))
			throw new ActionRepititionException();
		set.add(u);
		Post p = postRepository.findById(postId).get();
		p.setLikedBy(set);
		return postRepository.save(p);
	}

	@Override
	public Post unlikeAPost(int userId, int postId)
			throws UserNotFoundException, PostNotFoundException, ActionNotAllowedException {
		if (!userRepository.existsById(userId))
			throw new UserNotFoundException();
		if (!postRepository.existsById(postId))
			throw new PostNotFoundException();
		Set<User> set = postRepository.findById(postId).get().getLikedBy();
		if (!set.contains(userRepository.findById(userId).get()))
			throw new ActionNotAllowedException();
		Post p = postRepository.findById(postId).get();
		set.remove(userRepository.findById(userId).get());
		p.setLikedBy(set);
		return postRepository.save(p);
	}

	@Override
	public Post commentOnPost(int userId, int postId, Comment comment) {
//		post
		return null;
	}

	@Override
	public List<Post> getAllPostsLikedByUser(int userId) {
		return postRepository.getAllPostsLikedByUser(userId);
	}
}