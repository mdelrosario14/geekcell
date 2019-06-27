/**
 * RoleAccessRepository.java - Repository dao interface for the object database.
 * 2019 All rights reserved.
 *
 */
package com.gc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gc.entity.RoleEntity;

/**
 * This repository deals with RoleEntity transactions.
 * @author Mardolfh Del Rosario
 *
 */
@Repository
public interface RoleAccessRepository extends JpaRepository<RoleEntity, Integer> {
	List<RoleEntity> findByRoleName(String roleName);
}
