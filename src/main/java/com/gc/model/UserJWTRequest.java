/**
 * UserJWTRequest.java - Request model from mobile.
 * 2019 All rights reserved.
 *
 */
package com.gc.model;

import java.io.Serializable;

/**
 * This model served as the JWT request for authentication.
 * @author Mardolfh Del Rosario
 */
public class UserJWTRequest implements Serializable {

	private static final long serialVersionUID = 7680546440848469237L;

	private String username;
	private String password;

	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
