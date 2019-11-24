package com.gc.model;

import java.time.LocalDate;

public class TransactionResponse {
	private int trxMonthId;
	private LocalDate recordDateTime;
	private double debit;
	private double credit;
	private String remarks;
	private String income;
	private String expense;
	private String channel;

	public int getTrxMonthId() {
		return this.trxMonthId;
	}
	public void setTrxMonthId(int trxMonthId) {
		this.trxMonthId = trxMonthId;
	}
	public LocalDate getRecordDateTime() {
		return this.recordDateTime;
	}
	public void setRecordDateTime(LocalDate recordDateTime) {
		this.recordDateTime = recordDateTime;
	}
	public String getRemarks() {
		return this.remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIncome() {
		return this.income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getExpense() {
		return this.expense;
	}
	public void setExpense(String expense) {
		this.expense = expense;
	}
	public String getChannel() {
		return this.channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public double getDebit() {
		return this.debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	public double getCredit() {
		return this.credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}

}
