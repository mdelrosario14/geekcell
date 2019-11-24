/**
 * LogEntity.java - Entity for Login user in the system.
 * 2019 All rights reserved.
 *
 */
package com.gc.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity POJO for LOG_TRAIL table.
 * @author Mardolfh Del Rosario
 *
 */
@Entity
@Table(name="LOG_TRAIL")
public class LogEntity implements Serializable {

	private static final long serialVersionUID = -1557452919137248849L;


	@Id
	@Column(name = "LOG_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GCLOG_SEQ")
	@SequenceGenerator(sequenceName = "geekCellLog_sequence", allocationSize = 1, name = "GCLOG_SEQ")
	Long logId;

	@Column(name = "IP_ADDR")
	String ipAddress;

	@Column(name = "CRE8_DT")
	LocalDateTime createDateTime;

	@Column(name = "USR_EMAIL")
	String userEmail;

	public LogEntity() {

	}

	public LogEntity(Long logId, String ipAddress, LocalDateTime createDateTime, String userEmail) {
		super();
		this.logId = logId;
		this.ipAddress = ipAddress;
		this.createDateTime = createDateTime;
		this.userEmail = userEmail;
	}

	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public LocalDateTime getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		return "LogEntity [logId=" + this.logId + ", ipAddress=" + this.ipAddress + ", createDateTime=" +
				this.createDateTime	+ ", userEmail=" + this.userEmail + "]";
	}


}
