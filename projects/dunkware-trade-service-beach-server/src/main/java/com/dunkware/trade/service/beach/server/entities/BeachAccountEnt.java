package com.dunkware.trade.service.beach.server.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

public class BeachAccountEnt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@ManyToOne
	private BeachAccountEnt broker; 
	private String identifier;
	

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
	private List<BeachPlayEnt> plays = new ArrayList<BeachPlayEnt>();
	
	@Transient
	private List<BeachOrderEnt> orders = new ArrayList<BeachOrderEnt>();
	
}
