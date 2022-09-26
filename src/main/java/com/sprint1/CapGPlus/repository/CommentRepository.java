package com.sprint1.CapGPlus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint1.CapGPlus.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
