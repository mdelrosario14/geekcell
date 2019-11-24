/**
 * GeekCellAuthenticationProvider.java - Provide the GeekCell's authentication, checks login and pwd in DB.
 * 2019 All rights reserved.
 *
 */
package com.gc.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.gc.exception.MapperException;
import com.gc.exception.ServiceException;
import com.gc.exception.UtilityException;
import com.gc.model.User;
import com.gc.service.UserAccessService;
import com.gc.util.AccessUtil;
import com.gc.util.MessageConstants;
import com.gc.util.MessagePropertyReader;

/**
 * Checks the user authentication and handles the corresponding role.
 * @author Mardolfh Del Rosario
 *
 */
@Component
public class GeekCellAuthenticationProvider implements AuthenticationProvider  {

	@Autowired
	private UserAccessService userAccessService;

    @Autowired
    private MessagePropertyReader messagePropertyReader;

    @Autowired
    private AccessUtil accessUtil;

    private static final Logger LOG = LoggerFactory.getLogger(GeekCellAuthenticationProvider.class);

	/**
	 * This method do the actual authentication and returns granted role of the user.
	 *
	 * @param authentication request auth from user's UI.
	 * @return Authentication reference of the validated auth.
	 * @exception AuthenticationException any errors related to authentication.
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userEmail = authentication.getName().toLowerCase().trim();
        String pwd = authentication.getCredentials().toString();

        try {
			User user = this.userAccessService.loginAccount(userEmail, pwd);
			if (user != null) {
				List<GrantedAuthority> grantedAuthorities = this.accessUtil.getGrantedAuthorities(user.getRoles());
				if (null != grantedAuthorities) {
					return new UsernamePasswordAuthenticationToken(user, pwd, grantedAuthorities);
				} else {
					throw new ServiceException(this.messagePropertyReader.toLocale(
							MessageConstants.GC_LOGIN_NOT_AUTHORIZED));
				}
			} else {
				throw new ServiceException(this.messagePropertyReader.toLocale(
						MessageConstants.GC_LOGIN_USER_INVALID));
			}

		} catch (ServiceException | UtilityException | MapperException e) {
			LOG.warn("User " + userEmail + ", " + e.getMessage());
			throw new AuthenticationCredentialsNotFoundException(e.getMessage(), e);
		}

	}

	/**
	 * Tells Spring to support the username/password authentication.
	 *
	 * @param authentication any Class that uses authentication mechanism.
	 * @return boolean supports the implementation or not.
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
