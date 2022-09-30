package com.sprint1.CapGPlus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.CapGPlus.dto.inner.CommunityDTOInner;
import com.sprint1.CapGPlus.dto.outer.CommunityDTOOuter;
import com.sprint1.CapGPlus.dto.outer.UserDTO;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.service.CommunityService;

@RestController
public class CommunityController {

	@Autowired
	private CommunityService communityService;

	// Get all communities
	@GetMapping("/community")
	private ResponseEntity<List<CommunityDTOInner>> getAllCommunities() {
		return new ResponseEntity<>(communityService.getAllCommunities(), HttpStatus.OK);
	}

	// Get Community by id
	@GetMapping("/community/{communityId}")
	private ResponseEntity<CommunityDTOOuter> getCommunitybyCommunityId(@PathVariable int communityId)
			throws CommunityNotFoundException {
		return new ResponseEntity<>(communityService.getCommunitybyCommunityId(communityId), HttpStatus.OK);
	}

	// Get users in community
	@GetMapping("/community/{communityId}/users")
	private ResponseEntity<List<UserDTO>> getUsersinCommunityId(@PathVariable int communityId)
			throws CommunityNotFoundException {
		return new ResponseEntity<>(communityService.getUsersinCommunityId(communityId), HttpStatus.OK);
	}

}
