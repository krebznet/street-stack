package com.dunkware.xstream.model.stats;

import java.time.LocalDateTime;

public class EntityVarStats {
	
	private int varId; 
	private String varIdent; 
	private LocalDateTime from; 
	private LocalDateTime to; 
	private Number max;
	private LocalDateTime maxTime; 
	private LocalDateTime minTime; 
	private Number min;
	private int updates; 


	public int getVarId() {
		return varId;
	}

	public void setVarId(int varId) {
		this.varId = varId;
	}

	public String getVarIdent() {
		return varIdent;
	}

	public void setVarIdent(String varIdent) {
		this.varIdent = varIdent;
	}

	public LocalDateTime getFrom() {
		return from;
	}

	public void setFrom(LocalDateTime from) {
		this.from = from;
	}

	public LocalDateTime getTo() {
		return to;
	}

	public void setTo(LocalDateTime to) {
		this.to = to;
	}

	public Number getMax() {
		return max;
	}

	public void setMax(Number max) {
		this.max = max;
	}

	public LocalDateTime getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(LocalDateTime maxTime) {
		this.maxTime = maxTime;
	}

	public Number getMin() {
		return min;
	}

	public void setMin(Number min) {
		this.min = min;
	}

	public LocalDateTime getMinTime() {
		return minTime;
	}

	public void setMinTime(LocalDateTime minTime) {
		this.minTime = minTime;
	}

	public int getUpdates() {
		return updates;
	}

	public void setUpdates(int updates) {
		this.updates = updates;
	}

	

	
	


	
	
	
	
	
	
	
	

	

	
	

	
}
