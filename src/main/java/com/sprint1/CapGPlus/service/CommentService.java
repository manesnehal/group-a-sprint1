package com.sprint1.CapGPlus.service;

import java.util.List;

import com.sprint1.CapGPlus.entity.Comment;

public interface CommentService {

	public List<Comment> getAllCommentsByUser(int userId);
}
