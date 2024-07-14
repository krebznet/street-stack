package com.dunkware.time.data.dto.entity;

import java.time.LocalDateTime;
import java.util.Map;

public class EntitySignalDTO {

    private int entityId;
    private int signalId;
    private int streamId;
    private LocalDateTime timestamp;
    private Map<Integer, Double> vars;

	    public EntitySignalDTO(int entityId, int signalId, int streamId, LocalDateTime timestamp, Map<Integer, Double> vars) {
	        this.entityId = entityId;
	        this.signalId = signalId;
	        this.streamId = streamId;
	        this.timestamp = timestamp;
	        this.vars = vars;
	    }

	    // Getters and setters

	    public int getEntityId() {
	        return entityId;
	    }

	    public void setEntityId(int entityId) {
	        this.entityId = entityId;
	    }

	    public int getSignalId() {
	        return signalId;
	    }

	    public void setSignalId(int signalId) {
	        this.signalId = signalId;
	    }

	    public int getStreamId() {
	        return streamId;
	    }

	    public void setStreamId(int streamId) {
	        this.streamId = streamId;
	    }

	    public LocalDateTime getTimestamp() {
	        return timestamp;
	    }

	    public void setTimestamp(LocalDateTime timestamp) {
	        this.timestamp = timestamp;
	    }

	    public Map<Integer, Double> getVars() {
	        return vars;
	    }

	    public void setVars(Map<Integer, Double> vars) {
	        this.vars = vars;
	    }
}
