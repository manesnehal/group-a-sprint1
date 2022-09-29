package com.sprint1.CapGPlus.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.dto.outer.PostDTOOuter;
import com.sprint1.CapGPlus.repository.PostRepository;
import com.sprint1.CapGPlus.service.dto.PostDTOService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostDTOService postDTOService;

	@Override
	public List<PostDTOOuter> getAllPosts() {
		return postRepository.findAll().stream().map(postDTOService::convertToDTO).collect(Collectors.toList());
	}

}
