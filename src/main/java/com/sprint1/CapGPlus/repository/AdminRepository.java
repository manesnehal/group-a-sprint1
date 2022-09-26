package com.sprint1.CapGPlus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint1.CapGPlus.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
