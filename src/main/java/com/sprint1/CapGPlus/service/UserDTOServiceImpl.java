package com.sprint1.CapGPlus.service;

import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.dto.UserDTO;
import com.sprint1.CapGPlus.entity.User;

@Service
public class UserDTOServiceImpl implements UserDTOService {

	@Override
	public UserDTO convertToDTO(User user) {
		UserDTO u = new UserDTO();
		u.setId(user.getId());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setUserName(user.getUserName());
		return u;
	}
}
