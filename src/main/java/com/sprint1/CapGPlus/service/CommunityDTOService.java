package com.sprint1.CapGPlus.service;

import com.sprint1.CapGPlus.dto.CommunityDTO;
import com.sprint1.CapGPlus.entity.Community;

public interface CommunityDTOService {
	CommunityDTO convertToDTO(Community community);
}
