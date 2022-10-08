package com.sprint1.CapGPlus.service;

import java.util.List;
import java.util.Set;

import com.sprint1.CapGPlus.dto.inner.CommunityDTOInner;
import com.sprint1.CapGPlus.dto.outer.CommunityDTOOuter;
import com.sprint1.CapGPlus.dto.outer.UserDTO;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.exception.UserNotFoundException;

public interface CommunityService {

	// User community starts

	String joinCommunity(int userId, int communityId) throws CommunityNotFoundException, UserNotFoundException;

	String leaveCommunity(int userId, int communityId) throws CommunityNotFoundException, UserNotFoundException;

	Set<CommunityDTOInner> getCommunitiesbyUserId(int userId) throws UserNotFoundException;

	List<CommunityDTOInner> getAllCommunities();

	CommunityDTOOuter getCommunitybyCommunityId(int communityId) throws CommunityNotFoundException;

	List<UserDTO> getUsersinCommunityId(int communityId) throws CommunityNotFoundException;

	List<CommunityDTOInner> searchForCommunityByName(String searchQuery);
	// User community ends

}
