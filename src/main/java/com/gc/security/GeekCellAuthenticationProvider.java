/**
 * GeekCellAuthenticationProvider.java - Provide the GeekCell's authentication, checks login and pwd in DB.
 * 2019 All rights reserved.
 *
 */
package com.gc.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Checks the user authentication and handles the corresponding role.
 * @author Mardolfh Del Rosario
 *
 */
@Component
public class GeekCellAuthenticationProvider implements AuthenticationProvider  {

	/**
	 * This method do the actual authentication and returns granted role of the user.
	 * @param authentication request auth from user's UI.
	 * @return Authentication reference of the validated auth.
	 * @exception AuthenticationException any errors related to authentication.
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String user = authentication.getName();
        String password = authentication.getCredentials().toString();


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
