package com.dunkware.trade.service.beach.server.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class BeachPlayEnt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne()
	private BeachAccountEnt account;
	
	@Column(columnDefinition = "text")
	private String model; 
	
	private String name; 
	

}
