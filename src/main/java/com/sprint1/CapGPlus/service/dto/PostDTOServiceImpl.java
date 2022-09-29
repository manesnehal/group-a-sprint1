package com.sprint1.CapGPlus.service.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.dto.inner.PostDTOInner;
import com.sprint1.CapGPlus.dto.outer.CommentDTO;
import com.sprint1.CapGPlus.dto.outer.PostDTOOuter;
import com.sprint1.CapGPlus.dto.outer.UserDTO;
import com.sprint1.CapGPlus.entity.Post;

@Service
public class PostDTOServiceImpl implements PostDTOService {

	@Autowired
	private UserDTOService userDTOService;

	@Autowired
	private CommunityDTOService communityDTOService;

	@Autowired
	private CommentDTOService commentDTOService;

	@Override
	public PostDTOOuter convertToOuterDTO(Post post) {
		PostDTOOuter p = new PostDTOOuter();
		p.setId(post.getId());
		p.setTitle(post.getTitle());
		p.setContent(post.getContent());
		p.setPostedAt(post.getPostedAt());
		p.setUser(userDTOService.convertToDTO(post.getUser()));
		p.setCommunity(communityDTOService.convertToInnerDTO(post.getCommunity()));
		// p.setLikedBy();
//		p.setLikedBy();

		if (post.getLikedBy() == null) {
			p.setLikedBy(Collections.<UserDTO>emptySet());
		} else {
			p.setLikedBy(post.getLikedBy().stream().map(userDTOService::convertToDTO).collect(Collectors.toSet()));
		}
		if (post.getComments() == null) {
			p.setComments(new ArrayList<CommentDTO>());
		} else {
			p.setComments(
					post.getComments().stream().map(commentDTOService::convertToDTO).collect(Collectors.toList()));
		}
		return p;
	}

	@Override
	public PostDTOInner convertToInnerDTO(Post post) {
		PostDTOInner p = new PostDTOInner();
		p.setId(post.getId());
		p.setTitle(post.getTitle());
		p.setContent(post.getContent());
		p.setPostedAt(post.getPostedAt());
		return p;
	}

}
