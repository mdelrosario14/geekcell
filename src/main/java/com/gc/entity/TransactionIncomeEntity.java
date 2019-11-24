/**
 * TransactionIncomeEntity.java - Income list.
 * 2019 All rights reserved.
 */
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

/**
 * Entity POJO for TRX_INCOME table.
 * @author Mardolfh Del Rosario
 *
 */
@Entity
@Table(name="TRX_INCOME")
public class TransactionIncomeEntity implements Serializable {

	private static final long serialVersionUID = -5553941085095529423L;

	@Id
	@Column(name = "TRX_INC_ID")
	Long trxIncId;

	@Column(name =  "TRX_INC_DESC")
	String trxIncDesc;


	@Enumerated(EnumType.STRING)
	@Column(name = "CURR_CD")
	Currency currCd;

	@OneToMany(mappedBy="income")
    Set<TransactionEntity> transaction;

	public TransactionIncomeEntity() {

	}

	public TransactionIncomeEntity(Long trxIncId, String trxIncDesc, Currency currCd,
			Set<TransactionEntity> transaction) {
		super();
		this.trxIncId = trxIncId;
		this.trxIncDesc = trxIncDesc;
		this.currCd = currCd;
		this.transaction = transaction;
	}

	public Long getTrxIncId() {
		return this.trxIncId;
	}


	public void setTrxIncId(Long trxIncId) {
		this.trxIncId = trxIncId;
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


	public Set<TransactionEntity> getTransaction() {
		return this.transaction;
	}


	public void setTransaction(Set<TransactionEntity> transaction) {
		this.transaction = transaction;
	}


}
