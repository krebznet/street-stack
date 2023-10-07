package com.dunkware.trade.persistence.broker;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class AccountEntity {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@ManyToOne()
	private BrokerEntity broker; 
	private String identifier;
	
	//@Transient
	//private List<BeachOrderEnt> orders = new ArrayList<BeachOrderEnt>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval =  true)
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public BrokerEntity getBroker() {
		return broker;
	}

	public void setBroker(BrokerEntity broker) {
		this.broker = broker;
	}
	
	

	
	


}
