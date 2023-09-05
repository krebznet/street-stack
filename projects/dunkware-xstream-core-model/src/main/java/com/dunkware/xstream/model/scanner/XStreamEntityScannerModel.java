package com.dunkware.xstream.model.scanner;

import java.util.List;

import com.dunkware.xstream.model.entity.query.type.XStreamEntityQueryType;

public class XStreamEntityScannerModel {
	
	private XStreamEntityQueryType queryModel;
	private int updateInterval = 1; 
	private String scannerId;
	
	public XStreamEntityQueryType getQueryModel() {
		return queryModel;
	}
	public void setQueryModel(XStreamEntityQueryType queryModel) {
		this.queryModel = queryModel;
	}
	public int getUpdateInterval() {
		return updateInterval;
	}
	public void setUpdateInterval(int updateInterval) {
		this.updateInterval = updateInterval;
	}
	public String getScannerId() {
		return scannerId;
	}
	public void setScannerId(String scannerId) {
		this.scannerId = scannerId;
	} 
	
	
	

}
