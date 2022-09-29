package com.sprint1.CapGPlus.dto.outer;

import java.util.List;
import java.util.Set;

public class CommunityDTOOuter {
	private int id;
	private String name;
	private String description;
	private Set<UserDTO> user;
	private List<PostDTOOuter> post;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<UserDTO> getUser() {
		return user;
	}

	public void setUser(Set<UserDTO> user) {
		this.user = user;
	}

	public List<PostDTOOuter> getPost() {
		return post;
	}

	public void setPost(List<PostDTOOuter> post) {
		this.post = post;
	}
}
