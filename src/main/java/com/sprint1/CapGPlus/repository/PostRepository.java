package com.sprint1.CapGPlus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint1.CapGPlus.entity.Community;
import com.sprint1.CapGPlus.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	@Query(value = "SELECT * FROM post WHERE community_id=:community ORDER BY :order", nativeQuery = true)
	public List<Post> getAllPostsByCommunity(@Param("community") Community community, @Param("order") String order);
}
