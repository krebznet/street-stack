package com.dunkware.trade.service.beach.server.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "BeachBrokerEnt")
@Table(name = "beach_broker")
public class BeachBrokerEnt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	@Column(length = 2000)
	private String type;
	private String identifier; 
	
	@OneToMany
	@JoinColumn(name = "broker_id")
	private List<BeachAccountEnt> accounts = new ArrayList<BeachAccountEnt>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public List<BeachAccountEnt> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<BeachAccountEnt> accounts) {
		this.accounts = accounts;
	}
	
	
	
}
