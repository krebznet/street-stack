package com.dunkware.trade.service.beach.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "BeachPlayEnt")
@Table(name = "beach_play")
public class BeachPlayEnt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne()
	private BeachAccountEnt account;
	
	@Column(columnDefinition = "text")
	private String model; 
	
	@Column()
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	

}
