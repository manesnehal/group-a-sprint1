package com.sprint1.CapGPlus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sprint1.CapGPlus.entity.Community;
import com.sprint1.CapGPlus.exception.CommunityAlreadyExistsException;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.repository.CommunityRepository;
import com.sprint1.CapGPlus.repository.UserRepository;

@Service
public class CommunityServiceImpl implements CommunityService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommunityRepository communityRepository;

	// User Community starts

	public String joinCommunity(int userId, int communityId) throws CommunityNotFoundException {
		if (!communityRepository.existsById(communityId))
			throw new CommunityNotFoundException();
		
		userRepository.findById(userId).get().getCommunities().add(communityRepository.findById(communityId).get());
		communityRepository.findById(communityId).get().getUsers().add(userRepository.findById(userId).get());

		return "You are now a member of " + communityRepository.findById(communityId).get().getName();
	}

	public String leaveCommunity(int userId, int communityId) throws CommunityNotFoundException {
		if (!communityRepository.existsById(communityId))
			throw new CommunityNotFoundException();
		// form user side
		communityRepository.findById(communityId).get().getUsers().remove(userRepository.findById(userId).get());
		return "You can no longer post to " + communityRepository.findById(communityId).get().getName();
	}

	public List<Community> getCommunitiesbyUserId(int userId) {
		return new ArrayList<>(userRepository.findById(userId).get().getCommunities());
	}

	// User Community ends
}
