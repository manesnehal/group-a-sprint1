package com.sprint1.CapGPlus.dto.outer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.sprint1.CapGPlus.dto.inner.CommunityDTOInner;

public class PostDTOOuter {
	private int id;
	private String title;
	private String content;
	private LocalDateTime postedAt;
	private UserDTO user;
	private CommunityDTOInner community;
	private Set<UserDTO> likedBy;
	private List<CommentDTO> comments;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(LocalDateTime postedAt) {
		this.postedAt = postedAt;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public CommunityDTOInner getCommunity() {
		return community;
	}

	public void setCommunity(CommunityDTOInner community) {
		this.community = community;
	}

	public Set<UserDTO> getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(Set<UserDTO> likedBy) {
		this.likedBy = likedBy;
	}

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}
}
