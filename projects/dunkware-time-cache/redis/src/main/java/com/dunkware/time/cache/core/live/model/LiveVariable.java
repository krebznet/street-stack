package com.dunkware.time.cache.core.live.model;

import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;

@REntity
public class LiveVariable {

	public static final int NUMBER = 1; 
	public static final int STRING = 2; 
	
	@RId
	private long id; 
	private String ident; 
	private int type; 
	private Number number;
	private String string; 
	
	
	public LiveVariable() { 
		
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getIdent() {
		return ident;
	}


	public void setIdent(String ident) {
		this.ident = ident;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public Number getNumber() {
		return number;
	}


	public void setNumber(Number number) {
		this.number = number;
	}


	public String getString() {
		return string;
	}


	public void setString(String string) {
		this.string = string;
	}


	
	
}
