package com.sprint1.CapGPlus.service;

import java.util.List;
import java.util.Optional;

import com.sprint1.CapGPlus.entity.User;

public interface UserService {
	public User saveUser(User user); 
	public List<User> getUsers();
	public Optional<User> getUserById(int id);
	public boolean deleteUserById(int id);
	public User updateUserById(int id, User user);
	public List<User> findByFirstName(String firstName);
}