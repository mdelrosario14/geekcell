/**
 * JWTEntryController.java - Controller used for mobile login.
 * 2019 All rights reserved.
 *
 */
package com.gc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gc.model.UserJWTRequest;
import com.gc.model.UserJWTResponse;
import com.gc.service.impl.GeekCellJwtUserDetailsService;
import com.gc.util.AccessUtil;

/**
 * This controller handles all user JWT Login.
 * @author Mardolfh Del Rosario
 *
 */
@RestController
@CrossOrigin
@RequestMapping("mobile")
public class JWTEntryController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AccessUtil jwtTokenUtil;

	@Autowired
	private GeekCellJwtUserDetailsService userDetailsService;

	/**
	 * Creates an authentication token if valid.
	 *
	 * @param authenticationRequest	UserJWTRequest model request.
	 * @return UserJWTResponse reference.
	 * @throws Exception login error.
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserJWTRequest authenticationRequest)
			throws Exception {
		this.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = this.jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new UserJWTResponse(token));
	}

	/**
	 * Explicitly validates username and password from UserJWTRequest.
	 *
	 * @param username	user email.
	 * @param password	password before encryption.
	 * @throws Exception Exception login error.
	 */
	private void authenticate(String username, String password) throws Exception {
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
