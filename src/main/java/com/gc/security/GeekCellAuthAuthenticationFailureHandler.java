/**
 * GeekCellAuthAuthenticationFailureHandler.java - GeekCellApplication security configuration for fail handling.
 * 2019 All rights reserved.
 *
 */
package com.gc.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Failure handler if user is not authenticated.
 * @author Mardolfh Del Rosario
 *
 */
@Component
public class GeekCellAuthAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	/**
	 * Execute onAuthenticationFailure on unauthenticated access.
	 *
	 * @param request HttpServletRequest reference.
	 * @param response HttpServletResponse reference.
	 * @param exception AuthenticationException type of auth error.
	 * @throws IOException error in writing response.
	 */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
    		AuthenticationException exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("errorMsg", exception.getMessage());

        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.print(node);
        writer.flush();
        writer.close();

    }
}
