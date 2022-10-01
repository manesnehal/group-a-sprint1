package com.sprint1.CapGPlus.service.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.dto.outer.UserDTO;
import com.sprint1.CapGPlus.dto.outer.UserDTOFollowerCount;
import com.sprint1.CapGPlus.entity.User;
import com.sprint1.CapGPlus.repository.UserRepository;

@Service
public class UserDTOServiceImpl implements UserDTOService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDTO convertToDTO(User user) {
		UserDTO u = new UserDTO();
		u.setId(user.getId());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setUserName(user.getUserName());
		return u;
	}

	@Override
	public UserDTOFollowerCount convertToDTOFollowerCount(User user) {
		UserDTOFollowerCount u = new UserDTOFollowerCount();
		u.setId(user.getId());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setUserName(user.getUserName());
		u.setNumberOfFollowers(userRepository.getNumberOfFollowers(user.getId()));
		return u;
	}
}
