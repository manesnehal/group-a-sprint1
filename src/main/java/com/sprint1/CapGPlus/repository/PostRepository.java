package com.sprint1.CapGPlus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint1.CapGPlus.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
