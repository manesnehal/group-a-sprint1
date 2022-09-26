package com.sprint1.CapGPlus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.repository.CommunityRepository;

@Service
public class CommunityServiceImpl implements CommunityService {
	@Autowired
	private CommunityRepository communityRepository;
}
