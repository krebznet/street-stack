package com.dunkware.trade.service.beach.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "BeachSystemEntity")
@Table(name = "trade_system")
public class BeachSystemEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	private String name; 
	
	@Column(columnDefinition = "text")
	private String model; 
	
	private int tradeSequence = 0;
	
	
	@ManyToOne()
	private BeachBrokerAccountEntity account; // right it must reference an account; 
									 // you can swap accounts if you want

//	private List<BeachSessionEntity> sessions = new ArrayList<BeachSessionEntity>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BeachBrokerAccountEntity getAccount() {
		return account;
	}

	public void setAccount(BeachBrokerAccountEntity account) {
		this.account = account;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getTradeSequence() {
		return tradeSequence;
	}

	public void setTradeSequence(int tradeSequence) {
		this.tradeSequence = tradeSequence;
	}
	
	
	
	
	
	
					
	

	

}
