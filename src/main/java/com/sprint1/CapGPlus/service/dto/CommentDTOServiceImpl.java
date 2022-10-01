package com.sprint1.CapGPlus.service.dto;

import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.dto.outer.CommentDTO;
import com.sprint1.CapGPlus.entity.Comment;

@Service
public class CommentDTOServiceImpl implements CommentDTOService {

	@Override
	public CommentDTO convertToDTO(Comment comment) {
		CommentDTO c = new CommentDTO();
		c.setId(comment.getId());
		c.setContent(comment.getContent());
		c.setUsername(comment.getUser().getUserName());
		return c;
	}

}
