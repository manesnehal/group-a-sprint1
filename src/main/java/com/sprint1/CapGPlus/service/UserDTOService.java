package com.sprint1.CapGPlus.service;

import com.sprint1.CapGPlus.dto.UserDTO;
import com.sprint1.CapGPlus.entity.User;

public interface UserDTOService {
	UserDTO convertToDTO(User user);
}
