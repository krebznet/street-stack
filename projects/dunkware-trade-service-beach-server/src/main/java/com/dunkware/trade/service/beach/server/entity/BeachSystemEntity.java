package com.dunkware.trade.service.beach.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "BeachSystemEnt")
@Table(name = "beach_system")
public class BeachSystemEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	private String name; 
	
	@Column(columnDefinition = "text")
	private String model; 
	
	@ManyToOne
	private BeachAccountEnt account; // right it must reference an account; 
									 // you can swap accounts if you want

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

	public BeachAccountEnt getAccount() {
		return account;
	}

	public void setAccount(BeachAccountEnt account) {
		this.account = account;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	
					
	

	

}
