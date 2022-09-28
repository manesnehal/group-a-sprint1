package com.sprint1.CapGPlus.service;

import com.sprint1.CapGPlus.dto.PostDTO;
import com.sprint1.CapGPlus.entity.Post;

public interface PostDTOService {
	PostDTO convertToDTO(Post post);
}
