package com.dunkware.trade.service.stream.server.stats.entity;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class EntityStatsDO {
	
	
	@Id
	private BigInteger id;
	
	private LocalDate date;
	
	private int entId;
	
	private String ident; 
	
	
	private List<EntityVarStatsDO> vars = new ArrayList<EntityVarStatsDO>();

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public List<EntityVarStatsDO> getVars() {
		return vars;
	}

	public void setVars(List<EntityVarStatsDO> vars) {
		this.vars = vars;
	}


	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public int getEntId() {
		return entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}
	
	
	
	
	

}
