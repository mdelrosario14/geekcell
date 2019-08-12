/**
 * UserAccessService.java - Service interface that handles user authentication and authorization.
 * 2019 All rights reserved.
 *
 */
package com.gc.service;

import com.gc.exception.DtoException;
import com.gc.exception.ServiceException;
import com.gc.exception.UtilityException;
import com.gc.model.User;

/**
 * Service interface to validate/update login, roles etc.
 * @author Mardolfh Del Rosario
 */
public interface UserAccessService {
	User loginAccount(String email, String pwd) throws ServiceException, UtilityException, DtoException;
	User createUser(User user) throws ServiceException, UtilityException, DtoException;
	void insertLoginTrail(String ipAddress, String userName) throws ServiceException;
	User getUserDetailsByEmail(String email) throws ServiceException;

}
