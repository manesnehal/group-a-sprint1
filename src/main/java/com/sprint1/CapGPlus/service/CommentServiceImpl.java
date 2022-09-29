package com.sprint1.CapGPlus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.dto.outer.CommentDTO;
import com.sprint1.CapGPlus.entity.Comment;
import com.sprint1.CapGPlus.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public List<CommentDTO> getAllCommentsByUser(int userId) {
		return commentRepository.getAllCommentsByUser(userId);
	}
}
