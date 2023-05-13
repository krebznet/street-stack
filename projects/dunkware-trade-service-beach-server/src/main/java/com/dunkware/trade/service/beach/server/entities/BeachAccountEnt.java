package com.dunkware.trade.service.beach.server.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "BeachAccountEnt")
@Table(name = "beach_account")
public class BeachAccountEnt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@ManyToOne
	private BeachBrokerEnt broker; 
	private String identifier;
	

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
	private List<BeachPlayEnt> plays = new ArrayList<BeachPlayEnt>();
	
	@Transient
	private List<BeachOrderEnt> orders = new ArrayList<BeachOrderEnt>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public BeachBrokerEnt getBroker() {
		return broker;
	}

	public void setBroker(BeachBrokerEnt broker) {
		this.broker = broker;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public List<BeachPlayEnt> getPlays() {
		return plays;
	}

	public void setPlays(List<BeachPlayEnt> plays) {
		this.plays = plays;
	}

	public List<BeachOrderEnt> getOrders() {
		return orders;
	}

	public void setOrders(List<BeachOrderEnt> orders) {
		this.orders = orders;
	}
	
	
}
