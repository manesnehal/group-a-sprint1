package com.sprint1.CapGPlus.service;

import java.util.List;

import com.sprint1.CapGPlus.entity.Admin;
import com.sprint1.CapGPlus.entity.Community;
import com.sprint1.CapGPlus.exception.CommunityAlreadyExistsException;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;

public interface AdminService {
	String adminLogin(Admin pass);

	// Admin community starts

	List<Community> getAllCommunities();

	Community getCommunityById(int communityId) throws CommunityNotFoundException;

	Community addCommunity(Community community) throws CommunityAlreadyExistsException;

	Community editCommunityDetails(int communityId, Community community) throws CommunityNotFoundException;

	// Admin community ends
}
