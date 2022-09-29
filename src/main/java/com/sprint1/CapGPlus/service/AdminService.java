package com.sprint1.CapGPlus.service;

import java.util.List;

import com.sprint1.CapGPlus.dto.outer.CommunityDTOOuter;
import com.sprint1.CapGPlus.entity.Admin;
import com.sprint1.CapGPlus.entity.Community;
import com.sprint1.CapGPlus.exception.CommunityAlreadyExistsException;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.exception.InvalidCredentialsException;
import com.sprint1.CapGPlus.exception.PasswordMatchException;

public interface AdminService {
	// Admin Auth starts here
	String adminLogin(Admin pass) throws InvalidCredentialsException;

	String updatePassword(Admin a) throws InvalidCredentialsException, PasswordMatchException;
	// Admin Auth ends here

	// Admin community starts

	List<CommunityDTOOuter> getAllCommunities();

	CommunityDTOOuter getCommunityById(int communityId) throws CommunityNotFoundException;

	Community addCommunity(Community community) throws CommunityAlreadyExistsException;

	Community editCommunityDetails(int communityId, Community community)
			throws CommunityNotFoundException, CommunityAlreadyExistsException;

	String deleteCommunitybyCommunityId(int communityId) throws CommunityNotFoundException;

	// Admin community ends
}
