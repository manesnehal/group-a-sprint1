package com.sprint1.CapGPlus.dto.outer;

import java.util.List;
import java.util.Set;

import com.sprint1.CapGPlus.dto.inner.PostDTOInner;

public class CommunityDTOOuter {
	private int id;
	private String name;
	private String description;
	private Set<UserDTO> user;
	private List<PostDTOInner> post;

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

	public List<PostDTOInner> getPost() {
		return post;
	}

	public void setPost(List<PostDTOInner> post) {
		this.post = post;
	}
}
