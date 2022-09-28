package com.sprint1.CapGPlus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.dto.PostDTO;
import com.sprint1.CapGPlus.entity.Post;

@Service
public class PostDTOServiceImpl implements PostDTOService {

	@Autowired
	private UserDTOService userDTOService;

	@Autowired
	private CommunityDTOService communityDTOService;

	@Override
	public PostDTO convertToDTO(Post post) {
		PostDTO p = new PostDTO();
		p.setId(p.getId());
		p.setTitle(post.getTitle());
		p.setContent(post.getContent());
		p.setPostedAt(post.getPostedAt());
		p.setUser(userDTOService.convertToDTO(post.getUser()));
		return p;
	}

}
