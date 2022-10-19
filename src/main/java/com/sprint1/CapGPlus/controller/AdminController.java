package com.sprint1.CapGPlus.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("/")
  private ResponseEntity<String> home() {
    return new ResponseEntity<>("Munity", HttpStatus.OK);
  }

	// Admin Auth starts here
	// 1. Login
	// 2. Update Password

	@PostMapping("/admin/login")
	private ResponseEntity<Object> adminLogin(@Valid @RequestBody Admin pass) throws InvalidCredentialsException {
		String msg = adminService.adminLogin(pass);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping("/admin/update")
	private ResponseEntity<Object> passwordUpdate(@Valid @RequestBody Admin a)
			throws InvalidCredentialsException, PasswordMatchException {
		String msg = adminService.updatePassword(a);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	// Admin Auth ends here

	// Admin community starts
	// 1. Add Community
	// 2. Edit Community
	// 3. Delete Community

	@PostMapping("/admin/community")
	private ResponseEntity<Community> addNewCommunity(@Valid @RequestBody Community community)
			throws CommunityAlreadyExistsException {
		return new ResponseEntity<>(adminService.addCommunity(community), HttpStatus.CREATED);
	}

	@PutMapping("/admin/community/{communityId}")
	private ResponseEntity<Community> editCommunityDetails(@PathVariable int communityId,
			@Valid @RequestBody Community community)
			throws CommunityNotFoundException, CommunityAlreadyExistsException {
		return new ResponseEntity<>(adminService.editCommunityDetails(communityId, community), HttpStatus.OK);
	}

	@DeleteMapping("/community/{communityId}")
	private ResponseEntity<Object> deleteCommunitybyCommunityId(@PathVariable int communityId)
			throws CommunityNotFoundException {
		return new ResponseEntity<>(adminService.deleteCommunitybyCommunityId(communityId), HttpStatus.OK);
	}

	// Admin community ends
}
