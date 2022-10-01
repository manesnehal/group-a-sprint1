package com.sprint1.CapGPlus.service.dto;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.dto.inner.CommunityDTOInner;
import com.sprint1.CapGPlus.dto.inner.CommunityDTOInnerCount;
import com.sprint1.CapGPlus.dto.outer.CommunityDTOOuter;
import com.sprint1.CapGPlus.entity.Community;

@Service
public class CommunityDTOServiceImpl implements CommunityDTOService {

	@Autowired
	private UserDTOService userDTOService;

	@Autowired
	private PostDTOService postDTOService;

	@Override
	public CommunityDTOOuter convertToOuterDTO(Community community) {
		CommunityDTOOuter c = new CommunityDTOOuter();
		c.setId(community.getId());
		c.setName(community.getName());
		c.setDescription(community.getDescription());
		c.setUser(community.getUsers().stream().map(userDTOService::convertToDTO).collect(Collectors.toSet()));
		c.setPost(community.getPosts().stream().map(postDTOService::convertToInnerDTO).collect(Collectors.toList()));
		return c;
	}

	@Override
	public CommunityDTOInner convertToInnerDTO(Community community) {
		CommunityDTOInner c = new CommunityDTOInner();
		c.setId(community.getId());
		c.setName(community.getName());
		c.setDescription(community.getDescription());
		return c;
	}

	@Override
	public CommunityDTOInnerCount convertToInnerDTOCount(Community community) {
		CommunityDTOInnerCount c = new CommunityDTOInnerCount();
		c.setId(community.getId());
		c.setName(community.getName());
		c.setDescription(community.getDescription());
		c.setNumberOfUsers(community.getUsers().size());
		return c;
	}

}
