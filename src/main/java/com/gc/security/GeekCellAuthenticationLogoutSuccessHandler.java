/**
 * GeekCellAuthenticationLogoutSuccessHandler.java - GeekCellApplication security configuration for LOGOUT handling.
 * 2019 All rights reserved.
 *
 */
package com.gc.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Security activity for logout mechanism.
 * @author Mardolfh Del Rosario
 *
 */
@Component
public class GeekCellAuthenticationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	private static final Logger LOG = LoggerFactory.getLogger(GeekCellAuthenticationLogoutSuccessHandler.class);

	/**
	 * Whenever a /logout has invoked, execute this logout success method.
	 * @param request HttpServletRequest reference.
	 * @param response HttpServletResponse reference.
	 * @param authentication Authentication reference of the user logged out.
	 */
	@Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        LOG.debug("GeekCell user has logged out.");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
