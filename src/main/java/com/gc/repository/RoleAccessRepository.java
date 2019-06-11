package com.gc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gc.entity.RoleEntity;

public interface RoleAccessRepository extends JpaRepository<RoleEntity, Integer> {
	List<RoleEntity> findByRoleName(String roleName);
}
