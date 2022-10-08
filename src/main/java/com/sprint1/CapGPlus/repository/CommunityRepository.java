package com.sprint1.CapGPlus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint1.CapGPlus.entity.Community;

public interface CommunityRepository extends JpaRepository<Community, Integer> {
	@Query(value = "SELECT c FROM Community c WHERE c.name = :communityName")
	public Community findByCommunityName(@Param("communityName") String communityName);

	// Search for a community using a search query
	@Query(value = "SELECT * FROM community WHERE name ILIKE %:searchQuery%", nativeQuery = true)
	public List<Community> searchForCommunityByName(@Param("searchQuery") String searchQuery);
}
