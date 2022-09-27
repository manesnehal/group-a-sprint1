package com.sprint1.CapGPlus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.CapGPlus.entity.Admin;
import com.sprint1.CapGPlus.service.AdminService;

@RestController
public class AdminController {
	// AdminAuth starts here
	@Autowired
	private AdminService adServ;

	@PostMapping("/admin/login")
	private ResponseEntity<Object> adminLogin(@RequestBody Admin pass) {
		String msg = adServ.adminLogin(pass);
		return new ResponseEntity<Object>(msg, HttpStatus.ACCEPTED);
	}
	// AdminAuth ends here
}
