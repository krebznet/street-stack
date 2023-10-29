package com.dunkware.trade.service.beach.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "BeachSessionTradeSeqEntity")
@Table(name = "trade_session_trade_sequence")
public class BeachSessionTradeSeqEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String symbol; 
	
	private int sequence; 
	
	@OneToOne
	private BeachSystemEntity system;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public BeachSystemEntity getSystem() {
		return system;
	}

	public void setSystem(BeachSystemEntity system) {
		this.system = system;
	}

	
	
}
