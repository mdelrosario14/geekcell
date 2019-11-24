package com.gc.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MonthlyTrxRequest implements Serializable {

	private static final long serialVersionUID = 5073671214454120930L;
	private LocalDateTime localDateTime;
	private String currency;

	public LocalDateTime getLocalDateTime() {
		return this.localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	public String getCurrency() {
		return this.currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}


}
