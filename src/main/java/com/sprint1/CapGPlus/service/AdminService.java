package com.sprint1.CapGPlus.service;

import java.util.List;

import com.sprint1.CapGPlus.entity.Admin;
import com.sprint1.CapGPlus.entity.Community;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;

public interface AdminService {
	// Admin Auth starts here
	String adminLogin(Admin pass);
	// Admin Auth ends here

	// Admin community starts

	List<Community> getAllCommunities();

	Community getCommunityById(int communityId) throws CommunityNotFoundException;

	// Admin community ends
}
