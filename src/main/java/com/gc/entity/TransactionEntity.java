package com.gc.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gc.util.LocalDateTimeConverter;

@Entity
@Table(name="TRANSACTION")
public class TransactionEntity implements Serializable {

	private static final long serialVersionUID = 8329284592429831788L;

	@Id
	@Column(name = "TRX_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GCTRX_SEQ")
	@SequenceGenerator(sequenceName = "geekCellTrx_sequence", allocationSize = 1, name = "GCTRX_SEQ")
	Long trxId;

	@Column(name = "RECORD_DT")
	LocalDateTime recordDateTime;

	@Column(name = "TRX_DT")
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime trxDateTime;

	@Column(name = "DEBIT")
	Double debit;

	@Column(name = "CREDIT")
	Double credit;

	@Column(name = "REMARKS")
	String remarks;

	@ManyToOne
    @JoinColumn(name = "TRX_INC_ID", nullable=false)
    TransactionIncomeEntity income;

	@ManyToOne
    @JoinColumn(name = "TRX_EXP_ID", nullable=false)
    TransactionExpenseEntity expense;

	@ManyToOne
    @JoinColumn(name = "CHNL_ID", nullable=false)
    ChannelEntity channel;

	public TransactionEntity() {

	}


	public TransactionEntity(Long trxId, LocalDateTime recordDateTime, LocalDateTime trxDateTime, Double debit,
			Double credit, String remarks, TransactionIncomeEntity income, TransactionExpenseEntity expense,
			ChannelEntity channel) {
		super();
		this.trxId = trxId;
		this.recordDateTime = recordDateTime;
		this.trxDateTime = trxDateTime;
		this.debit = debit;
		this.credit = credit;
		this.remarks = remarks;
		this.income = income;
		this.expense = expense;
		this.channel = channel;
	}

	public Long getTrxId() {
		return this.trxId;
	}

	public void setTrxId(Long trxId) {
		this.trxId = trxId;
	}

	public LocalDateTime getRecordDateTime() {
		return this.recordDateTime;
	}

	public void setRecordDateTime(LocalDateTime recordDateTime) {
		this.recordDateTime = recordDateTime;
	}

	public LocalDateTime getTrxDateTime() {
		return this.trxDateTime;
	}

	public void setTrxDateTime(LocalDateTime trxDateTime) {
		this.trxDateTime = trxDateTime;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public TransactionIncomeEntity getIncome() {
		return this.income;
	}

	public void setIncome(TransactionIncomeEntity income) {
		this.income = income;
	}

	public TransactionExpenseEntity getExpense() {
		return this.expense;
	}

	public void setExpense(TransactionExpenseEntity expense) {
		this.expense = expense;
	}

	public ChannelEntity getChannel() {
		return this.channel;
	}

	public void setChannel(ChannelEntity channel) {
		this.channel = channel;
	}

	public Double getDebit() {
		return this.debit;
	}

	public void setDebit(Double debit) {
		this.debit = debit;
	}

	public Double getCredit() {
		return this.credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}
}
