package com.sprint1.CapGPlus.service.dto;

import com.sprint1.CapGPlus.dto.outer.UserDTO;
import com.sprint1.CapGPlus.dto.outer.UserDTOFollowerCount;
import com.sprint1.CapGPlus.entity.User;

public interface UserDTOService {
	UserDTO convertToDTO(User user);

	UserDTOFollowerCount convertToDTOFollowerCount(User user);
}
