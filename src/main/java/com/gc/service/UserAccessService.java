/**
 * UserAccessService.java - Service interface that handles user authentication and authorization.
 * 2019 All rights reserved.
 *
 */
package com.gc.service;

import com.gc.exception.MapperException;
import com.gc.exception.ServiceException;
import com.gc.exception.UtilityException;
import com.gc.model.User;

/**
 * Service interface to validate/update login, roles etc.
 * @author Mardolfh Del Rosario
 */
public interface UserAccessService {
	User loginAccount(String email, String pwd) throws ServiceException, UtilityException, MapperException;
	User createUser(User user) throws ServiceException, UtilityException, MapperException;
	void insertLoginTrail(String ipAddress, String userName) throws ServiceException;
	User getUserDetailsByEmail(String email) throws ServiceException;

}
