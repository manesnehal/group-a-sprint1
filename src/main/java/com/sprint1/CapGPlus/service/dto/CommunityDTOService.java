package com.sprint1.CapGPlus.service.dto;

import com.sprint1.CapGPlus.dto.inner.CommunityDTOInner;
import com.sprint1.CapGPlus.dto.inner.CommunityDTOInnerCount;
import com.sprint1.CapGPlus.dto.outer.CommunityDTOOuter;
import com.sprint1.CapGPlus.entity.Community;

public interface CommunityDTOService {
	CommunityDTOOuter convertToOuterDTO(Community community);

	CommunityDTOInner convertToInnerDTO(Community community);

	CommunityDTOInnerCount convertToInnerDTOCount(Community community);
}
