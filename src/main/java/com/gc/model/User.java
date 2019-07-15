/**
 * User.java - Model DTO for GeekCell User UI.
 * 2019 All rights reserved.
 *
 */
package com.gc.model;

import java.util.List;

/**
 * Model POJO UI for User.
 * @author Mardolfh Del Rosario
 *
 */
public class User {
	private String userId;
	private String email;
	private String firstName;
	private String lastName;
	private List<String> roles;
	private String pwd;

	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<String> getRoles() {
		return this.roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getPwd() {
		return this.pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


}
