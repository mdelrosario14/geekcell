/**
 * GeekCellAuthAuthenticationFailureHandler.java - GeekCellApplication security configuration for fail handling.
 * 2019 All rights reserved.
 *
 */
package com.gc.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * Failure handler if user is not authenticated.
 * @author Mardolfh Del Rosario
 *
 */
@Component
public class GeekCellAuthAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	/**
	 * Execute onAuthenticationFailure on unauthenticated access.
	 * @param request HttpServletRequest reference.
	 * @param response HttpServletResponse reference.
	 * @param exception AuthenticationException type of auth error.
	 */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
    		AuthenticationException exception) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
