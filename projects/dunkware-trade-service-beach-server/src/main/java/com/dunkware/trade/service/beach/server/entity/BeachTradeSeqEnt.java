package com.dunkware.trade.service.beach.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "BeachTradeSeqEnt")
@Table(name = "beach_trade_seq")
public class BeachTradeSeqEnt {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String symbol; 
	
	private int sequence; 
	
	@ManyToOne
	private BeachPlayEnt play;

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

	public BeachPlayEnt getPlay() {
		return play;
	}

	public void setPlay(BeachPlayEnt play) {
		this.play = play;
	} 
	
	
}
