package com.sprint1.CapGPlus.service;

import java.util.List;

import com.sprint1.CapGPlus.dto.outer.CommentDTO;
import com.sprint1.CapGPlus.entity.Comment;
import com.sprint1.CapGPlus.exception.CommentDoesNotExistException;
import com.sprint1.CapGPlus.exception.PostNotFoundException;
import com.sprint1.CapGPlus.exception.UserNotFoundException;

public interface CommentService {

	public List<CommentDTO> getAllCommentsByUser(int userId) throws UserNotFoundException, CommentDoesNotExistException;
	
	public List<CommentDTO> getAllCommentsOnAPost(int postId) throws CommentDoesNotExistException, PostNotFoundException;
}
