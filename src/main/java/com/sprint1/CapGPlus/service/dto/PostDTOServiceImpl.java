package com.sprint1.CapGPlus.service.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.dto.inner.PostDTOInner;
import com.sprint1.CapGPlus.dto.outer.PostDTOOuter;
import com.sprint1.CapGPlus.entity.Post;

@Service
public class PostDTOServiceImpl implements PostDTOService {

	@Autowired
	private UserDTOService userDTOService;

	@Autowired
	private CommunityDTOService communityDTOService;

	@Override
	public PostDTOOuter convertToOuterDTO(Post post) {
		PostDTOOuter p = new PostDTOOuter();
		p.setId(p.getId());
		p.setTitle(post.getTitle());
		p.setContent(post.getContent());
		p.setPostedAt(post.getPostedAt());
		p.setUser(userDTOService.convertToDTO(post.getUser()));
		return p;
	}

	@Override
	public PostDTOInner convertToInnerDTO(Post post) {
		PostDTOInner p = new PostDTOInner();
		p.setId(p.getId());
		p.setTitle(post.getTitle());
		p.setContent(post.getContent());
		p.setPostedAt(post.getPostedAt());
		return p;
	}

}