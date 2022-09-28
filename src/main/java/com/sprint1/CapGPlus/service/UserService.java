package com.sprint1.CapGPlus.service;

import java.util.List;

import com.sprint1.CapGPlus.entity.DataHolder;
import com.sprint1.CapGPlus.entity.User;
import com.sprint1.CapGPlus.exception.InvalidCredentialsException;
import com.sprint1.CapGPlus.exception.UserNameAlreadyExistsException;

public interface UserService {

	public User saveUser(User user) throws UserNameAlreadyExistsException;

	public boolean userLogin(DataHolder dataHolder) throws InvalidCredentialsException;

	public User findByUserName(String UserName);

	public List<User> getAllUsers();

}