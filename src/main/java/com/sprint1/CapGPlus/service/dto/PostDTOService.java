package com.sprint1.CapGPlus.service.dto;

import com.sprint1.CapGPlus.dto.outer.PostDTOOuter;
import com.sprint1.CapGPlus.entity.Post;

public interface PostDTOService {
	PostDTOOuter convertToDTO(Post post);
}
