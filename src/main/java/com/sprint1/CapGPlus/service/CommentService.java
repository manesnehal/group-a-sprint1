package com.sprint1.CapGPlus.service;

import java.util.List;

import com.sprint1.CapGPlus.dto.outer.CommentDTO;
import com.sprint1.CapGPlus.entity.Comment;

public interface CommentService {

	public List<CommentDTO> getAllCommentsByUser(int userId);
}
