package com.sprint1.CapGPlus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.entity.DataHolder;
import com.sprint1.CapGPlus.entity.User;
import com.sprint1.CapGPlus.exception.InvalidCredentialsException;
import com.sprint1.CapGPlus.exception.UserNameAlreadyExistsException;
import com.sprint1.CapGPlus.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	PasswordEncoder passwordEncoder;

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Override
	public User saveUser(User user) throws UserNameAlreadyExistsException {
		if (userRepo.findByUserName(user.getUserName()) != null)
			throw new UserNameAlreadyExistsException();
		String encodedPassword = this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		return userRepo.save(user);
	}

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepo = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public boolean userLogin(DataHolder dataHolder) throws InvalidCredentialsException {
		if (userRepo.findByUserName(dataHolder.getUserName()) == null || !bCryptPasswordEncoder
				.matches(dataHolder.getPassword(), userRepo.findByUserName(dataHolder.getUserName()).getPassword()))
			throw new InvalidCredentialsException();
		return bCryptPasswordEncoder.matches(dataHolder.getPassword(),
				userRepo.findByUserName(dataHolder.getUserName()).getPassword());
	}

	@Override
	public User findByUserName(String userName) {
		return userRepo.findByUserName(userName);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

}