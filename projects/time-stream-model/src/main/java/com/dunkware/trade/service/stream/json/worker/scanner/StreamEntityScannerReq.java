package com.dunkware.trade.service.stream.json.worker.scanner;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.model.entity.query.type.XStreamEntityQueryType;

public class StreamEntityScannerReq {

	private XStreamEntityQueryType query;
	private List<String> fields = new ArrayList<String>();
	private int scanInterval; 
	private String scannerId;
	
	public XStreamEntityQueryType getQuery() {
		return query;
	}
	public void setQuery(XStreamEntityQueryType query) {
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
