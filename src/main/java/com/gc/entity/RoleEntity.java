/**
 * RoleEntity.java - Entity for GeekCell roles.
 * 2019 All rights reserved.
 *
 */
package com.gc.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity POJO for Role table.
 * @author Mardolfh Del Rosario
 *
 */
@Entity
@Table(name="ROLE")
public class RoleEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1504186580068827909L;

	@Id
	@Column(name = "ROLE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GCRL_SEQ")
	@SequenceGenerator(sequenceName = "geekCellRole_sequence", allocationSize = 1, name = "GCRL_SEQ")
	Integer roleId;

	@Column(name = "ROLE_NM")
	String roleName;

	@Column(name = "ROLE_DESC")
	String roleDescription;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleEntities")
	Set<UserEntity> userEntities = new HashSet<>();

	public RoleEntity() {

	}

	public RoleEntity(Integer roleId, String roleName, String roleDescription,
			Set<UserEntity> userEntities) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.userEntities = userEntities;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Set<UserEntity> getUserEntities() {
		return this.userEntities;
	}

	public void setUserEntities(Set<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}

	@Override
	public String toString() {
		return "RoleEntity [roleId=" + this.roleId + ", roleName=" + this.roleName + ", roleDescription=" +
				this.roleDescription + "]";
	}



}
