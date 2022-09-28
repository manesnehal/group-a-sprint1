package com.sprint1.CapGPlus.service;

import java.util.List;

import com.sprint1.CapGPlus.entity.Community;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;

public interface CommunityService {

	// User community starts

//	List<Community> getAllCommunities();

//	Community getCommunityById(int communityId);

	String joinCommunity(int userId, int communityId) throws CommunityNotFoundException;

	String leaveCommunity(int userId, int communityId) throws CommunityNotFoundException;

	List<Community> getCommunitiesbyUserId(int userId);

	// User community ends

}
