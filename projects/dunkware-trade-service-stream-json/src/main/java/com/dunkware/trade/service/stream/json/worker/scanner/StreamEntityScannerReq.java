package com.dunkware.trade.service.stream.json.worker.scanner;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.model.query.XStreamEntityQueryModel;

public class StreamEntityScannerReq {

	private XStreamEntityQueryModel query;
	private List<String> fields = new ArrayList<String>();
	private int scanInterval; 
	private String scannerId;
	
	public XStreamEntityQueryModel getQuery() {
		return query;
	}
	public void setQuery(XStreamEntityQueryModel query) {
		this.query = query;
	}
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	public int getScanInterval() {
		return scanInterval;
	}
	public void setScanInterval(int scanInterval) {
		this.scanInterval = scanInterval;
	}
	public String getScannerId() {
		return scannerId;
	}
	public void setScannerId(String scannerId) {
		this.scannerId = scannerId;
	} 
	
	
	
	
	
	
}
