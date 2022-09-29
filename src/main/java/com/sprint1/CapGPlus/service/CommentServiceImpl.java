package com.sprint1.CapGPlus.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.dto.outer.CommentDTO;
import com.sprint1.CapGPlus.repository.CommentRepository;
import com.sprint1.CapGPlus.service.dto.CommentDTOService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CommentDTOService commentDTOService;

	@Override
	public List<CommentDTO> getAllCommentsByUser(int userId) {
		return commentRepository.getAllCommentsByUser(userId).stream().map(commentDTOService::convertToDTO).collect(Collectors.toList());
	}
}
