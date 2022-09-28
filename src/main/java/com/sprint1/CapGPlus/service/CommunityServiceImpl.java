package com.sprint1.CapGPlus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint1.CapGPlus.entity.Community;
import com.sprint1.CapGPlus.entity.User;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.exception.UserNotFoundException;
import com.sprint1.CapGPlus.repository.CommunityRepository;
import com.sprint1.CapGPlus.repository.UserRepository;

@Service
public class CommunityServiceImpl implements CommunityService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommunityRepository communityRepository;

	// User Community starts

	public String joinCommunity(int userId, int communityId) throws CommunityNotFoundException, UserNotFoundException {
		if (!communityRepository.existsById(communityId))
			throw new CommunityNotFoundException();
		if (!userRepository.existsById(userId))
			throw new UserNotFoundException();
		
		User user = userRepository.findById(userId).get();
		Community community = communityRepository.findById(communityId).get();

		// Check if user is already a member community
		if(user.getCommunities().contains(community))
			return "You are alraedy a member of " + community.getName();
		
		// Add a community to user
		Set<Community> userCommunities = user.getCommunities();
		userCommunities.add(community);
		Set<User> communityUsers = community.getUsers();
		communityUsers.add(user);
		
		// Set a community to user
		user.setCommunities(userCommunities);
		community.setUsers(communityUsers);

		// Save User, Community
		userRepository.save(user);
		communityRepository.save(community);

		return "You are now a member of " + community.getName();
	}

	public String leaveCommunity(int userId, int communityId) throws CommunityNotFoundException, UserNotFoundException {
		if (!communityRepository.existsById(communityId))
			throw new CommunityNotFoundException();
		if (!userRepository.existsById(userId))
			throw new UserNotFoundException();

		User user = userRepository.findById(userId).get();
		Community community = communityRepository.findById(communityId).get();

		
		// Check if user is already a member community
		if(!user.getCommunities().contains(community))
			return "You are not a part of " + community.getName();

		// Remove a community to user
		Set<Community> userCommunities = user.getCommunities();
		userCommunities.remove(community);
		Set<User> communityUsers = community.getUsers();
		communityUsers.remove(user);
		
		// Set a community to user
		user.setCommunities(userCommunities);
		community.setUsers(communityUsers);

		// Save User, Community
		userRepository.save(user);
		communityRepository.save(community);

		return "You can no longer post to " + community.getName();
	}

	public List<Community> getCommunitiesbyUserId(int userId) throws UserNotFoundException {
		if (!userRepository.existsById(userId))
			throw new UserNotFoundException();
		return new ArrayList<>(userRepository.findById(userId).get().getCommunities());
	}

	// User Community ends
}
