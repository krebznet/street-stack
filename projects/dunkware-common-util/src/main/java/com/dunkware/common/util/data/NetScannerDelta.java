package com.dunkware.common.util.data;

import java.util.ArrayList;
import java.util.List;

public class NetScannerDelta {

	private List<NetBean> inserts = new ArrayList<NetBean>();
	private List<Object> deletes = new ArrayList<Object>();
	private List<NetBean> updates = new ArrayList<NetBean>();
	
	public List<NetBean> getInserts() {
		return inserts;
	}
	public void setInserts(List<NetBean> inserts) {
		this.inserts = inserts;
	}
	
	public List<NetBean> getUpdates() {
		return updates;
	}
	public void setUpdates(List<NetBean> updates) {
		this.updates = updates;
	}
	public List<Object> getDeletes() {
		return deletes;
	}
	public void setDeletes(List<Object> deletes) {
		this.deletes = deletes;
	}
	
	
	
	
}
