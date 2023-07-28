package com.dunkware.trade.service.beach.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dunkware.trade.service.beach.model.trade.BeachTradeStatus;

//@Entity(name = "BeachTradeEnt")
//@Table(name = "beach_trade")
public class BeachTradeEnt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//@ManyToOne()
	//private BeachSystemEnt system; 
	
	@ManyToOne()
	private BeachAccountEnt account; 
	
	@ManyToOne()
	private BeachBrokerEnt broker; 
	
	@Column(columnDefinition = "text")
	private String model; 
	
	@Enumerated(EnumType.ORDINAL)
	private BeachTradeStatus status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BeachAccountEnt getAccount() {
		return account;
	}

	public void setAccount(BeachAccountEnt account) {
		this.account = account;
	}

	public BeachBrokerEnt getBroker() {
		return broker;
	}

	public void setBroker(BeachBrokerEnt broker) {
		this.broker = broker;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BeachTradeStatus getStatus() {
		return status;
	}

	public void setStatus(BeachTradeStatus status) {
		this.status = status;
	}

	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
