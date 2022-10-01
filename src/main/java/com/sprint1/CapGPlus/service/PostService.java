package com.sprint1.CapGPlus.service;

import java.util.List;

import com.sprint1.CapGPlus.dto.outer.PostDTOOuter;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.exception.PostNotFoundException;

public interface PostService {
	public List<PostDTOOuter> getAllPosts();

	public PostDTOOuter getPostById(int postId) throws PostNotFoundException;

	public List<PostDTOOuter> getPostByCommunity(int comId) throws CommunityNotFoundException;

	public List<PostDTOOuter> searchPostByTitle(String searchQuery);
}
