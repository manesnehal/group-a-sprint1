package com.sprint1.CapGPlus.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.dto.inner.CommunityDTOInner;
import com.sprint1.CapGPlus.dto.outer.CommunityDTOOuter;
import com.sprint1.CapGPlus.dto.outer.UserDTO;
import com.sprint1.CapGPlus.entity.Community;
import com.sprint1.CapGPlus.entity.User;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.exception.UserNotFoundException;
import com.sprint1.CapGPlus.repository.CommunityRepository;
import com.sprint1.CapGPlus.repository.UserRepository;
import com.sprint1.CapGPlus.service.dto.CommunityDTOService;
import com.sprint1.CapGPlus.service.dto.UserDTOService;

@Service
public class CommunityServiceImpl implements CommunityService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommunityRepository communityRepository;

	@Autowired
	private CommunityDTOService communityDTOService;

	@Autowired
	private UserDTOService userDTOService;

	// User Community starts

	public String joinCommunity(int userId, int communityId) throws CommunityNotFoundException, UserNotFoundException {
		if (!communityRepository.existsById(communityId))
			throw new CommunityNotFoundException();
		if (!userRepository.existsById(userId))
			throw new UserNotFoundException();

		User user = userRepository.findById(userId).get();
		Community community = communityRepository.findById(communityId).get();

		// Check if user is already a member community
		if (user.getCommunities().contains(community))
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
		if (!user.getCommunities().contains(community))
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

	public Set<CommunityDTOInner> getCommunitiesbyUserId(int userId) throws UserNotFoundException {
		if (!userRepository.existsById(userId))
			throw new UserNotFoundException();
		return userRepository.findById(userId).get().getCommunities().stream()
				.map(communityDTOService::convertToInnerDTO).collect(Collectors.toSet());
	}

	public List<CommunityDTOInner> getAllCommunities() {
		return communityRepository.findAll().stream().map(communityDTOService::convertToInnerDTO)
				.collect(Collectors.toList());
	}

	public CommunityDTOOuter getCommunitybyCommunityId(int communityId) throws CommunityNotFoundException {
		if (!communityRepository.existsById(communityId))
			throw new CommunityNotFoundException();
		return communityDTOService.convertToOuterDTO(communityRepository.findById(communityId).get());
	}

	public List<UserDTO> getUsersinCommunityId(int communityId) throws CommunityNotFoundException {
		if (!communityRepository.existsById(communityId))
			throw new CommunityNotFoundException();
		return communityRepository.findById(communityId).get().getUsers().stream().map(userDTOService::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<CommunityDTOInner> searchForCommunityByName(String searchQuery) {
		return communityRepository.searchForCommunityByName(searchQuery).stream()
				.map(communityDTOService::convertToInnerDTO).collect(Collectors.toList());
	}
	// User Community ends
}
