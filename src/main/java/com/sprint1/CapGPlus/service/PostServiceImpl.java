package com.sprint1.CapGPlus.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.dto.outer.PostDTOOuter;
import com.sprint1.CapGPlus.entity.Community;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.exception.PostNotFoundException;
import com.sprint1.CapGPlus.repository.CommunityRepository;
import com.sprint1.CapGPlus.repository.PostRepository;
import com.sprint1.CapGPlus.service.dto.PostDTOService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostDTOService postDTOService;

	@Autowired
	private CommunityRepository communityRepository;

	@Override
	public List<PostDTOOuter> getAllPosts() {
		return postRepository.findAll().stream().map(postDTOService::convertToOuterDTO).collect(Collectors.toList());
	}

	@Override
	public PostDTOOuter getPostById(int postId) throws PostNotFoundException {
		if (!postRepository.existsById(postId))
			throw new PostNotFoundException();
		return postDTOService.convertToOuterDTO(postRepository.findById(postId).get());
	}

	@Override
	public List<PostDTOOuter> getPostByCommunity(int comId) throws CommunityNotFoundException {
		if (!communityRepository.existsById(comId))
			throw new CommunityNotFoundException();

		Community community = communityRepository.findById(comId).get();
		return community.getPosts().stream().map(postDTOService::convertToOuterDTO).collect(Collectors.toList());
	}

	@Override
	public List<PostDTOOuter> searchPostByTitle(String searchQuery) {
		return postRepository.searchPostByTitle(searchQuery).stream().map(postDTOService::convertToOuterDTO)
				.collect(Collectors.toList());
	}
}
