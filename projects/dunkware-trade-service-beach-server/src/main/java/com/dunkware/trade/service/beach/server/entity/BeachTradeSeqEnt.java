package com.dunkware.trade.service.beach.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "BeachTradeSeqEnt")
@Table(name = "beach_trade_seq")
public class BeachTradeSeqEnt {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String symbol; 
	
	private int sequence; 
	
	@OneToOne
	private BeachSystemEnt system;

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

	public BeachSystemEnt getSystem() {
		return system;
	}

	public void setSystem(BeachSystemEnt system) {
		this.system = system;
	}

	
	
}
