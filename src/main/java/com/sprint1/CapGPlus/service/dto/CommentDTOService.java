package com.sprint1.CapGPlus.service.dto;

import com.sprint1.CapGPlus.dto.outer.CommentDTO;
import com.sprint1.CapGPlus.entity.Comment;

public interface CommentDTOService {
	CommentDTO convertToDTO(Comment comment);
}
