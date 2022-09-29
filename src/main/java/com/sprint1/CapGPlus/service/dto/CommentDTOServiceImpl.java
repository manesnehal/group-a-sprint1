package com.sprint1.CapGPlus.service.dto;

import com.sprint1.CapGPlus.dto.outer.CommentDTO;
import com.sprint1.CapGPlus.entity.Comment;

public class CommentDTOServiceImpl implements CommentDTOService {

	@Override
	public CommentDTO convertToDTO(Comment comment) {
		CommentDTO c = new CommentDTO();
		c.setId(comment.getId());
		c.setContent(comment.getContent());
		return c;
	}

}
