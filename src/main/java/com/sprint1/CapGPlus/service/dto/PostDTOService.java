package com.sprint1.CapGPlus.service.dto;

import com.sprint1.CapGPlus.dto.inner.PostDTOInner;
import com.sprint1.CapGPlus.dto.outer.PostDTOOuter;
import com.sprint1.CapGPlus.entity.Post;

public interface PostDTOService {
	
	  PostDTOOuter convertToOuterDTO(Post post);
	  
	  PostDTOInner convertToInnerDTO(Post post);
	 
}
