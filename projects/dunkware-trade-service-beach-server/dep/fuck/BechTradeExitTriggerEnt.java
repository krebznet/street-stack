package com.dunkware.trade.service.beach.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Entity(name = "BechExitTriggerEnt")
//@Table(name = "beach_trade_exit_trigger")
public class BechTradeExitTriggerEnt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column(columnDefinition = "text")
	private String state; 
	
	@ManyToOne()
	private BeachTradeEnt trade;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BeachTradeEnt getTrade() {
		return trade;
	}

	public void setTrade(BeachTradeEnt trade) {
		this.trade = trade;
	} 

	
}
