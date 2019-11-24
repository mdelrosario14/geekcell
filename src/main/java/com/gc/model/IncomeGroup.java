package com.gc.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class IncomeGroup implements Serializable {

	private static final long serialVersionUID = 6675095777959884607L;
	private double totalCnt;
	private long trxIncId;
	private LocalDateTime trxDateTime;
	private String trxIncDesc;
	private Currency currCd;

	public IncomeGroup(LocalDateTime trxDateTime, long trxIncId, String trxIncDesc, Currency currCd, double totalCnt) {
		super();
		this.totalCnt = totalCnt;
		this.trxIncId = trxIncId;
		this.trxDateTime = trxDateTime;
		this.trxIncDesc = trxIncDesc;
		this.currCd = currCd;
	}
	public double getTotalCnt() {
		return this.totalCnt;
	}
	public void setTotalCnt(double totalCnt) {
		this.totalCnt = totalCnt;
	}
	public long getTrxIncId() {
		return this.trxIncId;
	}
	public void setTrxIncId(long trxIncId) {
		this.trxIncId = trxIncId;
	}
	public LocalDateTime getTrxDateTime() {
		return this.trxDateTime;
	}
	public void setTrxDateTime(LocalDateTime trxDateTime) {
		this.trxDateTime = trxDateTime;
	}
	public String getTrxIncDesc() {
		return this.trxIncDesc;
	}
	public void setTrxIncDesc(String trxIncDesc) {
		this.trxIncDesc = trxIncDesc;
	}
	public Currency getCurrCd() {
		return this.currCd;
	}
	public void setCurrCd(Currency currCd) {
		this.currCd = currCd;
	}

}
