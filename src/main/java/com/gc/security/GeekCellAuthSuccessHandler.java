/**
 * GeekCellAuthSuccessHandler.java - GeekCellApplication security configuration for success handling.
 * 2019 All rights reserved.
 *
 */
package com.gc.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gc.model.User;

/**
 * Success handler if user is authenticated.
 * @author Mardolfh Del Rosario
 *
 */
@Component
public class GeekCellAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private static final Logger LOG = LoggerFactory.getLogger(GeekCellAuthSuccessHandler.class);

	/**
	 * Whenever there is an authentication success, apply logic below, including csrf token.
	 * @param request HttpServletRequest reference.
	 * @param response HttpServletResponse reference.
	 * @param authentication Authentication reference of the user login.
	 * @exception IOException getWriter() exception.
	 */
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode roles = mapper.createArrayNode();
        List<String> rolesList = null;

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            roles.add(authority.getAuthority());
        }

        User user = (User)authentication.getPrincipal();
        ObjectNode node = mapper.createObjectNode();

        rolesList = authentication.getAuthorities().stream().map(
    			role -> role.getAuthority()).collect(Collectors.toList());

        //User can have multiple roles.  Need to cast it in an individual specific user.
    	if (null != rolesList && !rolesList.isEmpty()) {
    		for (String role : rolesList) {
    			if (role != null && !role.isBlank()) {
    				JSONObject obj = new JSONObject(user);
    				node.putPOJO("valid-user", obj);
    			}
    		}
    	}


        CsrfToken token = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
        LOG.debug("tokenlog="  + token.getToken());
        node.put("_csrf", token.getToken());

        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.print(node);
        writer.flush();
        writer.close();

    }
}
