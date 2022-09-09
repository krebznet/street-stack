package com.dunkware.common.util.data;

public class NetScannerDeltaWeb {
	
	
	private NetBean[] inserts = new NetBean[0];
	private Object[] deletes = new Object[0];
	private NetBean[] updates = new NetBean[0];
	public NetBean[] getInserts() {
		return inserts;
	}
	public void setInserts(NetBean[] inserts) {
		this.inserts = inserts;
	}
	public Object[] getDeletes() {
		return deletes;
	}
	public void setDeletes(Object[] deletes) {
		this.deletes = deletes;
	}
	public NetBean[] getUpdates() {
		return updates;
	}
	public void setUpdates(NetBean[] updates) {
		this.updates = updates;
	}
	
	
	
	

}
