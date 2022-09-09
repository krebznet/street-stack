package com.dunkware.common.util.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class NetScannerDeltaWeb {
	
	
	private List<Map<String,Object>> inserts = null;
	private List<Map<String,Object>> updates = null;
	private List<Object> deletes = null;
	
	public List<Map<String, Object>> getInserts() {
		return inserts;
	}
	public void setInserts(List<Map<String, Object>> inserts) {
		this.inserts = inserts;
	}
	public List<Map<String, Object>> getUpdates() {
		return updates;
	}
	public void setUpdates(List<Map<String, Object>> updates) {
		this.updates = updates;
	}
	public List<Object> getDeletes() {
		return deletes;
	}
	public void setDeletes(List<Object> deletes) {
		this.deletes = deletes;
	}
	
	
	
	
	
	

}
