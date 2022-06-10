package com.dunkware.xstream.net.protocol.data;

import java.util.ArrayList;
import java.util.List;

public class TableStreamUpdate {

	private int id; 
	private List<TableStreamRow> inserts = new ArrayList<TableStreamRow>();
	private List<TableStreamRow> updates = new ArrayList<TableStreamRow>();
	private List<Integer> deletes = new ArrayList<Integer>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<TableStreamRow> getInserts() {
		return inserts;
	}
	public void setInserts(List<TableStreamRow> inserts) {
		this.inserts = inserts;
	}
	public List<TableStreamRow> getUpdates() {
		return updates;
	}
	public void setUpdates(List<TableStreamRow> updates) {
		this.updates = updates;
	}
	public List<Integer> getDeletes() {
		return deletes;
	}
	public void setDeletes(List<Integer> deletes) {
		this.deletes = deletes;
	}
	
	
	
}
