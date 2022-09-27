package com.sprint1.CapGPlus.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String password;
	@ManyToMany
	@JoinTable(name = "user_community", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "community_id"))
	Set<Community> communities;
	@OneToMany(mappedBy = "user")
	List<Post> posts;

	public Set<Community> getCommunities() {
		return communities;
	}

	public void setCommunities(Set<Community> communities) {
		this.communities = communities;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
