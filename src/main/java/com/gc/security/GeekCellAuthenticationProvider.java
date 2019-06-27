/**
 * GeekCellAuthenticationProvider.java - Provide the GeekCell's authentication, checks login and pwd in DB.
 * 2019 All rights reserved.
 *
 */
package com.gc.security;

import java.util.ArrayList;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.gc.exception.DtoException;
import com.gc.exception.ServiceException;
import com.gc.exception.UtilityException;
import com.gc.model.User;
import com.gc.service.UserAccessService;
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

    private static final Logger LOG = LoggerFactory.getLogger(GeekCellAuthenticationProvider.class);

	/**
	 * This method do the actual authentication and returns granted role of the user.
	 * @param authentication request auth from user's UI.
	 * @return Authentication reference of the validated auth.
	 * @exception AuthenticationException any errors related to authentication.
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userEmail = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        try {
			User user = this.userAccessService.loginAccount(userEmail, pwd);
			if (user != null) {
				List<GrantedAuthority> grantedAuthorities = null;
				List<String> roles = user.getRoles();

				if (roles != null && !roles.isEmpty()) {
					grantedAuthorities = new ArrayList<>();
					for(String role : roles) {
	                    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
	                    LOG.debug("User is " + grantedAuthorities.get(grantedAuthorities.size()-1).getAuthority());
					}
                    return new UsernamePasswordAuthenticationToken(user, pwd, grantedAuthorities);
				} else {
					throw new ServiceException(this.messagePropertyReader.toLocale(
							MessageConstants.GC_LOGIN_NOT_AUTHORIZED));
				}
			}

		} catch (ServiceException | UtilityException | DtoException e) {
			throw new AuthenticationCredentialsNotFoundException(e.getMessage(), e);
		}


		return null;
	}

	/**
	 * Tells Spring to support the username/password authentication.
	 * @param authentication any Class that uses authentication mechanism.
	 * @return boolean supports the implementation or not.
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
