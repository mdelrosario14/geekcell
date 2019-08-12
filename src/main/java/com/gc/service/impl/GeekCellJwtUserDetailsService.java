/**
 * GeekCellJwtUserDetailsService.java - Entry point used for login mobile.
 * 2019 All rights reserved.
 *
 */
package com.gc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gc.exception.ServiceException;
import com.gc.model.User;
import com.gc.service.UserAccessService;
import com.gc.util.AccessUtil;
import com.gc.util.MessageConstants;
import com.gc.util.MessagePropertyReader;

/**
 * Implements UserDetailsService to be used in getting Principal for JWT.
 * @author Mardolfh Del Rosario
 */
@Service
public class GeekCellJwtUserDetailsService implements UserDetailsService {


	@Autowired
	private UserAccessService userAccessService;

    @Autowired
    private AccessUtil accessUtil;

	@Autowired
	private MessagePropertyReader messagePropertyReader;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = this.userAccessService.getUserDetailsByEmail(username);
			if (null != user) {
				return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPwd(),
						this.accessUtil.getGrantedAuthorities(user.getRoles()));
			} else {
				throw new ServiceException(this.messagePropertyReader.toLocale(
						MessageConstants.GC_LOGIN_USER_NOT_FOUND));
			}
		} catch (ServiceException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

}
