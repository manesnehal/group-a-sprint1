package com.sprint1.CapGPlus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint1.CapGPlus.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	@Query(value = "SELECT * FROM post WHERE community_id IN (SELECT DISTINCT community_id FROM user_community WHERE user_id=:userId) ORDER BY posted_at DESC", nativeQuery = true)
	public List<Post> getAllPostsByCommunity(@Param("userId") int userId);

	@Query(value = "SELECT * FROM post WHERE id IN (SELECT post_id FROM user_likes WHERE user_id=:userId)", nativeQuery = true)
	public List<Post> getAllPostsLikedByUser(@Param("userId") int userId);

	@Query(value = "SELECT * FROM post WHERE user_id IN (SELECT following_id FROM user_following WHERE user_id = :userId) ORDER BY posted_at DESC", nativeQuery = true)
	public List<Post> getFeedOfFollowingUsers(@Param("userId") int userId);

	@Query(value = "SELECT * FROM post WHERE title ILIKE %:searchQuery%", nativeQuery = true)
	public List<Post> searchPostByTitle(@Param("searchQuery") String searchQuery);
}
