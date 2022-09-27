package com.sprint1.CapGPlus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.CapGPlus.entity.Admin;
import com.sprint1.CapGPlus.entity.Community;
import com.sprint1.CapGPlus.exception.CommunityAlreadyExistsException;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.exception.InvalidCredentialsException;
import com.sprint1.CapGPlus.exception.PasswordMatchException;
import com.sprint1.CapGPlus.service.AdminService;

@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;

	// Admin Auth starts here
	@PostMapping("/admin/login")
	private ResponseEntity<Object> adminLogin(@RequestBody Admin pass) throws InvalidCredentialsException {
		String msg = adminService.adminLogin(pass);
		return new ResponseEntity<Object>(msg, HttpStatus.ACCEPTED);
	}

	@PutMapping("/admin/update")
	private ResponseEntity<Object> passwordUpdate(@RequestBody Admin a)
			throws InvalidCredentialsException, PasswordMatchException {
		String msg = adminService.updatePassword(a);
		return new ResponseEntity<Object>(msg, HttpStatus.OK);
	}
	// Admin Auth ends here

	// Admin community starts

	@GetMapping("/admin/community")
	private ResponseEntity<List<Community>> getAllCommunities() {
		return new ResponseEntity<>(adminService.getAllCommunities(), HttpStatus.OK);
	}

	@GetMapping("/admin/community/{communityId}")
	private ResponseEntity<Community> getCommunityById(@PathVariable int communityId)
			throws CommunityNotFoundException {
		return new ResponseEntity<>(adminService.getCommunityById(communityId), HttpStatus.OK);
	}

	@PostMapping("/admin/community")
	private ResponseEntity<Community> addNewCommunity(@RequestBody Community community)
			throws CommunityAlreadyExistsException {
		return new ResponseEntity<>(adminService.addCommunity(community), HttpStatus.CREATED);
	}

	@PutMapping("/admin/community/{communityId}")
	private ResponseEntity<Community> editCommunityDetails(@PathVariable int communityId,
			@RequestBody Community community) throws CommunityNotFoundException {
		return new ResponseEntity<>(adminService.editCommunityDetails(communityId, community), HttpStatus.OK);
	}

	// Admin community ends
}
