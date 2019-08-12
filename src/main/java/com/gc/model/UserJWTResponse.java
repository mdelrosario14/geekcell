/**
 * UserJWTResponse.java - Request model from mobile.
 * 2019 All rights reserved.
 *
 */
package com.gc.model;

import java.io.Serializable;

/**
 * This model served as the JWT response if authenticated.
 * @author Mardolfh Del Rosario
 */
public class UserJWTResponse implements Serializable {

	private static final long serialVersionUID = 755558163790514419L;

	private final String jwttoken;

	/**
	 * Inserts token in this model.
	 * @param jwttoken String token.
	 */
	public UserJWTResponse(String jwttoken) {
		super();
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

}
