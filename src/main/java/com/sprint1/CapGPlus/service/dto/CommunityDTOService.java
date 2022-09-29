package com.sprint1.CapGPlus.service.dto;

import com.sprint1.CapGPlus.dto.outer.CommunityDTOOuter;
import com.sprint1.CapGPlus.entity.Community;

public interface CommunityDTOService {
	CommunityDTOOuter convertToDTO(Community community);
}
