/**
 * AccessUtil.java - Utility used for any user accesses.
 * 2019 All rights reserved.
 *
 */
package com.gc.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.gc.security.GeekCellAuthenticationProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Contains all helper methods for getting user authentication.
 * @author Mardolfh Del Rosario
 */
@Component
public class AccessUtil {

	private static final Logger LOG = LoggerFactory.getLogger(GeekCellAuthenticationProvider.class);
	private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.key}")
	private String gcKey;

	/**
	 * Get all GrantedAuthority references from String roles.
	 * @param roles list of roles to check.
	 * @return List<GrantedAuthority> of the user.
	 */
	public List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> grantedAuthorities = null;

		if (roles != null && !roles.isEmpty()) {
			grantedAuthorities = new ArrayList<>();
			for(String role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
                LOG.warn("User has " + grantedAuthorities.get(grantedAuthorities.size()-1).getAuthority());
			}
		}

		return grantedAuthorities;
	}


	/**
	 * Retrieve username from jwt token.
	 *
	 * @param token username to get to.
	 * @return username
	 */
	public String getUsernameFromToken(String token) {
		return this.getClaimFromToken(token, Claims::getSubject);
	}

	/**
	 * Retrieving information from token must provide the geek cell key.
	 *
	 * @param token username to get to.
	 * @return T reference.
	 */
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = Jwts.parser().setSigningKey(this.gcKey).parseClaimsJws(token).getBody();
		return claimsResolver.apply(claims);
	}

	/**
	 * Generate token for logged user.
	 *
	 * @param userDetails UserDetails reference.
	 * @return token.
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		String token = Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, this.gcKey).compact();

		return token;
	}


	/**
	 * Validate token from request.
	 *
	 * @param token token to check to.
	 * @param userDetails UserDetails reference.
	 * @return valid user.
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = this.getUsernameFromToken(token);
		final Date expiration = this.getClaimFromToken(token, Claims::getExpiration);
		boolean isTokenExpired =  expiration.before(new Date());

		return (username.equals(userDetails.getUsername()) && !isTokenExpired);
	}
}
