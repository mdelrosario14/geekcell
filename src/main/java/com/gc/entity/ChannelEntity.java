package com.gc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gc.model.Currency;

@Entity
@Table(name="CHANNEL")
public class ChannelEntity implements Serializable {

	private static final long serialVersionUID = 6123170373806622806L;

	@Id
	@Column(name = "CHNL_ID")
	Long channelId;

	@Column(name =  "CHNL_DESC")
	String channelDesc;


	@Enumerated(EnumType.STRING)
	@Column(name = "CURR_CD")
	Currency currCd;


	public ChannelEntity() {

	}

	public ChannelEntity(Long channelId, String channelDesc, Currency currCd) {
		super();
		this.channelId = channelId;
		this.channelDesc = channelDesc;
		this.currCd = currCd;
	}


	public Long getChannelId() {
		return this.channelId;
	}


	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}


	public String getChannelDesc() {
		return this.channelDesc;
	}


	public void setChannelDesc(String channelDesc) {
		this.channelDesc = channelDesc;
	}


	public Currency getCurrCd() {
		return this.currCd;
	}


	public void setCurrCd(Currency currCd) {
		this.currCd = currCd;
	}

}
