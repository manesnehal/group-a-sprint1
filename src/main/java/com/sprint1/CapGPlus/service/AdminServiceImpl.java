package com.sprint1.CapGPlus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.entity.Admin;
import com.sprint1.CapGPlus.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminRepository adRepo;

	@Override
	public String adminLogin(Admin pass) {
		Admin a = adRepo.findAll().get(0);
		if(!a.getPassword().equals(pass.getPassword())) {
			return "Incorrect Password!";
		}
		return "Admin Login Successful";
	}
	
}
