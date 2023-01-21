package com.dunkware.xstream.model.stats;

import java.time.LocalDateTime;
import java.util.List;

public class EntityVarStats {
	
	private int entityId; 
	private String entityIdent; 
	private int varId; 
	private String varIdent; 
	private String streamIdent; 
	private long streamId; 
	private LocalDateTime from; 
	private LocalDateTime to; 
	private Number max;
	private LocalDateTime maxTime; 
	private LocalDateTime minTime; 
	private Number min;
	private int updateCount; 

	

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public String getEntityIdent() {
		return entityIdent;
	}

	public void setEntityIdent(String entityIdent) {
		this.entityIdent = entityIdent;
	}

	public int getVarId() {
		return varId;
	}

	public void setVarId(int varId) {
		this.varId = varId;
	}

	public String getStreamIdent() {
		return streamIdent;
	}

	public void setStreamIdent(String streamIdent) {
		this.streamIdent = streamIdent;
	}
 
	public long getStreamId() {
		return streamId;
	}

	public void setStreamId(long streamId) {
		this.streamId = streamId;
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

	public int getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}

	
	


	
	
	
	
	
	
	
	

	

	
	

	
}
