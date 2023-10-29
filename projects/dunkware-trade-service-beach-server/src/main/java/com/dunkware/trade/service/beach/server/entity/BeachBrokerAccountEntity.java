package com.dunkware.trade.service.beach.server.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity(name = "BeachAccountEntity")
@Table(name = "trade_broker_account")
public class BeachBrokerAccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@ManyToOne()
	private BeachBrokerEnttity broker; 
	private String identifier;
	

	//@Transient
	//private List<BeachOrderEnt> orders = new ArrayList<BeachOrderEnt>();


	@OneToMany(cascade = CascadeType.ALL, orphanRemoval =  true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "account_id")
	private List<BeachSystemEntity> systems = new ArrayList<BeachSystemEntity>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public BeachBrokerEnttity getBroker() {
		return broker;
	}

	public void setBroker(BeachBrokerEnttity broker) {
		this.broker = broker;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public List<BeachSystemEntity> getSystems() {
		return systems;
	}

	public void setSystems(List<BeachSystemEntity> systems) {
		this.systems = systems;
	}
	
	

	
	

	
	
}
