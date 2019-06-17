/**
 * GeekCellHttpAuthenticationEntryPoint.java - Implements the AuthenticationEntryPoint.
 * 2019 All rights reserved.
 *
 */
package com.gc.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Updates http response 401 when not authorized.
 * @author Mardolfh Del Rosario
 *
 */
@Component
public class GeekCellHttpAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}