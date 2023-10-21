package com.dunkware.trade.service.beach.server.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity(name = "BeachAccountEnt")
//@Table(name = "beach_account")
public class BeachAccountEnt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	//@ManyToOne
	private BeachBrokerEnt broker; 
	private String identifier;
	

	//@Transient
	//private List<BeachOrderEnt> orders = new ArrayList<BeachOrderEnt>();

	//@OneToMany()
	//@JoinColumn(name = "acccount_id")
	//private List<BeachSystemEnt> systems = new ArrayList<BeachSystemEnt>();
	
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

	
	

	
	
}
