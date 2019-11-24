package com.gc.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gc.model.Currency;

@Entity
@Table(name="TRX_EXPENSE")
public class TransactionExpenseEntity implements Serializable {

	private static final long serialVersionUID = -3971052560024043703L;

	@Id
	@Column(name = "TRX_EXP_ID")
	Long trxExpId;

	@Column(name =  "TRX_EXP_DESC")
	String trxExpDesc;


	@Enumerated(EnumType.STRING)
	@Column(name = "CURR_CD")
	Currency currCd;

	@OneToMany(mappedBy="expense")
    Set<TransactionEntity> transaction;


	public TransactionExpenseEntity() {

	}

	public TransactionExpenseEntity(Long trxExpId, String trxExpDesc, Currency currCd,
			Set<TransactionEntity> transaction) {
		super();
		this.trxExpId = trxExpId;
		this.trxExpDesc = trxExpDesc;
		this.currCd = currCd;
		this.transaction = transaction;
	}




	public Long getTrxExpId() {
		return this.trxExpId;
	}


	public void setTrxExpId(Long trxExpId) {
		this.trxExpId = trxExpId;
	}


	public String getTrxExpDesc() {
		return this.trxExpDesc;
	}


	public void setTrxExpDesc(String trxExpDesc) {
		this.trxExpDesc = trxExpDesc;
	}


	public Currency getCurrCd() {
		return this.currCd;
	}


	public void setCurrCd(Currency currCd) {
		this.currCd = currCd;
	}


	public Set<TransactionEntity> getTransaction() {
		return this.transaction;
	}


	public void setTransaction(Set<TransactionEntity> transaction) {
		this.transaction = transaction;
	}

}
