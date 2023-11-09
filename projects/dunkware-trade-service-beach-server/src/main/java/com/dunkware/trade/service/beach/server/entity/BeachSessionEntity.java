package com.dunkware.trade.service.beach.server.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "BeachSessionEntity")
@Table(name = "trade_session")
public class BeachSessionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	//@ManyToOne()
	//private BeachSystemEntity system; 
	
	private LocalDateTime startTime; 
	
	private LocalDateTime closingTime; 
	
	private LocalDateTime closedTime; 
	
	//private List<BeachSessionTradeEntity> trades = new ArrayList<BeachSessionTradeEntity>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(LocalDateTime closingTime) {
		this.closingTime = closingTime;
	}

	public LocalDateTime getClosedTime() {
		return closedTime;
	}

	public void setClosedTime(LocalDateTime closedTime) {
		this.closedTime = closedTime;
	}


	

}
