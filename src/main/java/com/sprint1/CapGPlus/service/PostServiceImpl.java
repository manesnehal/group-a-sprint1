package com.sprint1.CapGPlus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostService postService;
}
