package com.dunkware.trade.service.stream.protocol.controller;

public class DeleteStreamReq {

	private String name; 
	private boolean deleteData;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDeleteData() {
		return deleteData;
	}
	public void setDeleteData(boolean deleteData) {
		this.deleteData = deleteData;
	}
	
	
}
