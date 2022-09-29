package com.sprint1.CapGPlus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint1.CapGPlus.entity.Community;

public interface CommunityRepository extends JpaRepository<Community, Integer> {
	@Query(value = "SELECT c FROM Community c WHERE c.name = :communityName", nativeQuery = true)
	public Community findByCommunityName(@Param("communityName") String communityName);
}
