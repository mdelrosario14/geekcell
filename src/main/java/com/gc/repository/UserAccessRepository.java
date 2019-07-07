/**
 * UserAccessRepository.java - Repository dao interface for the object database.
 * 2019 All rights reserved.
 *
 */
package com.gc.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gc.entity.UserEntity;

/**
 * This repository deals with UserEntity transactions.
 * @author Mardolfh Del Rosario
 *
 */
@Repository
public interface UserAccessRepository extends JpaRepository<UserEntity, Long> {
	List<UserEntity> findByEmail(String email);
    List<UserEntity> findByCreateDateTime(LocalDateTime createDateTime);


    // custom query example and return a stream
    //@Query("select c from GeekCellUser c where c.email like %:email")
    //Stream<UserEntity> findByEmailReturnStream(@Param("email") String email);
}
