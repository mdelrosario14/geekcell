/**
 * GeekCellJwtRequestFilter.java - All mobile request filters are being checked here.
 * 2019 All rights reserved.
 *
 */
package com.gc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gc.util.AccessUtil;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * This filter class handle the logic on validating request if JWT is valid (session).
 */
@Component
public class GeekCellJwtRequestFilter extends OncePerRequestFilter  {

	private static final Logger LOG = LoggerFactory.getLogger(GeekCellJwtRequestFilter.class);

	@Autowired
	private AccessUtil accessUtil;

	@Autowired
	private UserDetailsService userDetailsService;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		// JWT Token is in the form "Bearer token".
		if (null != requestTokenHeader && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = this.accessUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				LOG.error("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				LOG.error("JWT Token has expired");
			}
		} else {
			LOG.warn("JWT Token does not begin with Bearer String");
		}

		if (null != username && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			// if token is valid configure Spring Security to manually set
			// authentication
			if (this.accessUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request));

				// set Spring Security Configuration.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);

	}

}
