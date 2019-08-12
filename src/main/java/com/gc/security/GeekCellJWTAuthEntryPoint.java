/**
 * GeekCellJWTAuthEntryPoint.java - Entry point used for login mobile.
 * 2019 All rights reserved.
 *
 */
package com.gc.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * This class handles authentication entry point if invalid, execute commence.
 * @author Mardolfh Del Rosario
 */
@Component
public class GeekCellJWTAuthEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -2336362137110760051L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());

	}

}
