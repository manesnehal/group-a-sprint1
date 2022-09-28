package com.sprint1.CapGPlus.service;

import java.util.Set;

import com.sprint1.CapGPlus.dto.CommunityDTO;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.exception.UserNotFoundException;

public interface CommunityService {

	// User community starts

	String joinCommunity(int userId, int communityId) throws CommunityNotFoundException, UserNotFoundException;

	String leaveCommunity(int userId, int communityId) throws CommunityNotFoundException, UserNotFoundException;

	Set<CommunityDTO> getCommunitiesbyUserId(int userId) throws UserNotFoundException;

	// User community ends

}
