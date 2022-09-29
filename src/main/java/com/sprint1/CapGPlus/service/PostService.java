package com.sprint1.CapGPlus.service;

import java.util.List;

import com.sprint1.CapGPlus.dto.outer.PostDTOOuter;
import com.sprint1.CapGPlus.exception.PostNotFoundException;

public interface PostService {
	public List<PostDTOOuter> getAllPosts();

	PostDTOOuter getPostById(int postId) throws PostNotFoundException;
}
