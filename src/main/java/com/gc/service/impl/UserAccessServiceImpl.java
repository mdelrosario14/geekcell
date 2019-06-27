/**
 * UserAccessServiceImpl.java - Implementation of UserAccessService.
 * 2019 All rights reserved.
 *
 */
package com.gc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.dto.UserDto;
import com.gc.entity.UserEntity;
import com.gc.exception.DtoException;
import com.gc.exception.ServiceException;
import com.gc.exception.UtilityException;
import com.gc.model.User;
import com.gc.repository.UserAccessRepository;
import com.gc.service.UserAccessService;
import com.gc.util.MessageConstants;
import com.gc.util.MessagePropertyReader;

/**
 * This service contains all implementation about user access.
 * @author Mardolfh Del Rosario
 *
 */
@Service
public class UserAccessServiceImpl implements UserAccessService {

    @Autowired
    private UserAccessRepository userAccessRepository;
    @Autowired
    private MessagePropertyReader messagePropertyReader;
    @Autowired
    private UserDto userDto;


	@Override
	public User loginAccount(String email, String pwd) throws ServiceException, UtilityException, DtoException {
		User userLogin = null;
		if (null != email && !email.isBlank() && null != pwd && !pwd.isEmpty()) {
			UserEntity userEntity = this.userAccessRepository.findByLogin(email, pwd).get(0);
			if (null == userEntity) {
				throw new ServiceException(this.messagePropertyReader.toLocale(
						MessageConstants.GC_LOGIN_USER_NOT_FOUND));
			} else {
				userLogin = this.userDto.transferDataToModel(userEntity);
			}
		}

		return userLogin;
	}
}
