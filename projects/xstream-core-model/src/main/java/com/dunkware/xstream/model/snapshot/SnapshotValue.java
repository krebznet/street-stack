package com.dunkware.xstream.model.snapshot;

public class SnapshotValue {

	private int type; 
	private SnapshotEntity entity; 
	private SnapshotVariable variable;
	private SnapshotSignal signal;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public SnapshotEntity getEntity() {
		return entity;
	}
	public void setEntity(SnapshotEntity entity) {
		this.entity = entity;
	}
	public SnapshotVariable getVariable() {
		return variable;
	}
	public void setVariable(SnapshotVariable variable) {
		this.variable = variable;
	}
	public SnapshotSignal getSignal() {
		return signal;
	}
	public void setSignal(SnapshotSignal signal) {
		this.signal = signal;
	}
	
	
}
