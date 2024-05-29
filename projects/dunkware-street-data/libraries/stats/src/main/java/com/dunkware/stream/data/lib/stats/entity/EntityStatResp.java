package com.dunkware.stream.data.lib.stats.entity;

public class EntityStatResp {

	private boolean resolved = false; 
	private double value;
	
	
	public EntityStatResp() { 
		
	}
	
	public EntityStatResp(boolean resolved) { 
		this.resolved = resolved;
	}
	
	public EntityStatResp(double value) { 
		this.resolved = true;
		this.value = value;;
	}
	
	public boolean isResolved() {
		return resolved;
	}
	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	} 
	
	


}
