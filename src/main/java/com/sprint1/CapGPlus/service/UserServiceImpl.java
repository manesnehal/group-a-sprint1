package com.sprint1.CapGPlus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.entity.User;
import com.sprint1.CapGPlus.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	
	@Override
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	@Override
	public Optional<User> getUserById(int id) {
		return userRepo.findById(id);
	}

	@Override
	public boolean deleteUserById(int id) {
		// Check if User exists
		if (!userRepo.existsById(id))
			return false;

		userRepo.deleteById(id);
		return true;
	}

	@Override
	public User updateUserById(int id, User user) {
		Optional<User> UserOptional = userRepo.findById(id);
		if (UserOptional.isEmpty())
			return null;
		user.setId(id);
		return userRepo.save(user);
	}

	@Override
	public List<User> findByFirstName(String firstName) {
		return userRepo.findByFirstName(firstName);
	}
}