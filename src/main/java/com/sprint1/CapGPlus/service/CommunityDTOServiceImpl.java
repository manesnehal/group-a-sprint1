package com.sprint1.CapGPlus.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.dto.CommunityDTO;
import com.sprint1.CapGPlus.entity.Community;

@Service
public class CommunityDTOServiceImpl implements CommunityDTOService {

	@Autowired
	private UserDTOService userDTOService;

	@Override
	public CommunityDTO convertToDTO(Community community) {
		CommunityDTO c = new CommunityDTO();
		c.setId(community.getId());
		c.setName(community.getName());
		c.setDescription(community.getDescription());
		c.setUser(community.getUsers().stream().map(userDTOService::convertToDTO).collect(Collectors.toSet()));
		return c;
	}

}
