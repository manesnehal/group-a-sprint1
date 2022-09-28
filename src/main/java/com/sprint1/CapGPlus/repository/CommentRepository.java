package com.sprint1.CapGPlus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint1.CapGPlus.entity.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
