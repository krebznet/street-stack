package com.dunkware.trade.service.beach.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "BeachTradeLogEnt")
@Table(name = "beach_trade_log")
public class BeachTradeLogEnt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String level; 
	
	private String message; 
	
	@ManyToOne
	private BeachTradeEnt trade;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BeachTradeEnt getTrade() {
		return trade;
	}

	public void setTrade(BeachTradeEnt trade) {
		this.trade = trade;
	} 
	
	
}
