package com.sprint1.CapGPlus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;
}
