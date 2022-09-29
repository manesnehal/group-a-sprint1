package com.sprint1.CapGPlus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint1.CapGPlus.dto.outer.CommentDTO;
import com.sprint1.CapGPlus.entity.Comment;
import com.sprint1.CapGPlus.entity.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query(value = "select * from comment where user_id = :ID", nativeQuery = true)
	public List<Comment> getAllCommentsByUser(@Param("ID") int userId);
	
	@Query(value = "select * from comment where post_id = :ID", nativeQuery = true)
	public List<Comment> getAllCommentsOnAPost(@Param("ID") int postId);
}
