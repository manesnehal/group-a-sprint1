package com.sprint1.CapGPlus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint1.CapGPlus.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query(value = "SELECT e FROM User e WHERE e.firstName = :name", nativeQuery = true)
	public List<User> findByFirstName(@Param("name") String firstName);

	@Query(value = "SELECT e FROM User e WHERE e.userName = :name", nativeQuery = true)
	public User findByUserName(@Param("name") String userName);

}