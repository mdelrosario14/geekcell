/**
 * UserEntity.java - Entity for GeekCell users.
 * 2019 All rights reserved.
 *
 */
package com.gc.entity;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity POJO for User table.
 * @author Mardolfh Del Rosario
 *
 */
@Entity
@Table(name="USER")
public class UserEntity implements java.io.Serializable {

	private static final long serialVersionUID = 457038428488502787L;

	@Id
	@Column(name = "USR_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GCUSR_SEQ")
	@SequenceGenerator(sequenceName = "geekCellUser_sequence", allocationSize = 1, name = "GCUSR_SEQ")
	Long userId;

	@Column(name = "USR_FRST_NM")
	String firstName;

	@Column(name = "USR_LST_NM")
	String lastName;

	@Column(name = "USR_EMAIL")
	String email;

	@Column(name = "USR_PWD")
	String pwd;

	@Column(name = "CRE8_DT")
	LocalDateTime createDateTime;

	@Column(name = "LST_UPDT")
	LocalDateTime lastUpdate;

	@Column(name = "ACTV_SW")
	String activeSwitch;

	@Column(name = "CHNGE_PWD_DT")
	LocalDateTime changePwdDate;


	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USR_ROLE",
        joinColumns = @JoinColumn(name = "USR_ID", referencedColumnName = "USR_ID"),
        inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID"))
	Set<RoleEntity> roleEntities = new HashSet<>();

	public UserEntity() {

	}

	public UserEntity(Long userId, String firstName, String lastName, String email, String pwd,
			LocalDateTime createDateTime, LocalDateTime lastUpdate, String activeSwitch, LocalDateTime changePwdDate,
			Set<RoleEntity> roleEntities) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pwd = pwd;
		this.createDateTime = createDateTime;
		this.lastUpdate = lastUpdate;
		this.activeSwitch = activeSwitch;
		this.changePwdDate = changePwdDate;
		this.roleEntities = roleEntities;
	}


	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public LocalDateTime getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getActiveSwitch() {
		return this.activeSwitch;
	}

	public void setActiveSwitch(String activeSwitch) {
		this.activeSwitch = activeSwitch;
	}

	public LocalDateTime getChangePwdDate() {
		return this.changePwdDate;
	}

	public void setChangePwdDate(LocalDateTime changePwdDate) {
		this.changePwdDate = changePwdDate;
	}


	public Set<RoleEntity> getRoleEntities() {
		return this.roleEntities;
	}

	public void setRoleEntities(Set<RoleEntity> roleEntities) {
		this.roleEntities = roleEntities;
	}

	@Override
	public String toString() {
		return "UserEntity [userId=" + this.userId + ", firstName=" + this.firstName + ", lastName=" +
				this.lastName + ", email=" + this.email + ", pwd=" + this.pwd + ", createDateTime=" +
				this.createDateTime + ", lastUpdate=" + this.lastUpdate + ", activeSwitch=" +
				this.activeSwitch + ", changePwdDate=" + this.changePwdDate + ", roleEntities="	+ this.roleEntities;
	}


}
