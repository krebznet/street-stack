package com.dunkware.trade.persistence.broker;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "BrokerEntity")
@Table(name = "broker")
public class BrokerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	@Column(length = 2000)
	private String type;
	private String identifier; 
	

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval =  true)
	@JoinColumn(name = "broker_id")
	private List<AccountEntity> accounts = new ArrayList<AccountEntity>();
	
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
	public List<AccountEntity> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<AccountEntity> accounts) {
		this.accounts = accounts;
	}
	
	
	
	
}
