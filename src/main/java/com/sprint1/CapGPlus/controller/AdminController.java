package com.sprint1.CapGPlus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.CapGPlus.entity.Admin;
import com.sprint1.CapGPlus.entity.Community;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.service.AdminService;

@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;

	@PostMapping("/admin/login")
	private ResponseEntity<Object> adminLogin(@RequestBody Admin pass) {
		String msg = adminService.adminLogin(pass);
		return new ResponseEntity<Object>(msg, HttpStatus.ACCEPTED);
	}

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

	// Admin community ends
}
