/**
 * LoginAccessRepository.java - Repository dao interface for the user who login.
 * 2019 All rights reserved.
 *
 */
package com.gc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gc.entity.LogEntity;

/**
 * This repository deals with LogEntity transactions.
 * @author Mardolfh Del Rosario
 *
 */
@Repository
public interface LoginAccessRepository extends JpaRepository<LogEntity, Long> {
	List<LogEntity> findByIpAddress(String ipAddress);
	List<LogEntity> findByUserEmail(String userEmail);

}
