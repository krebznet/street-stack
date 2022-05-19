package com.dunkware.xstream.net.core.container.core;

import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.scanner.StreamEntityScannerItem;

public class ContainerEntityScannerItemImpl implements StreamEntityScannerItem {

	private String data; 
	private int timeInScanner;
	private String entityIdent; 
	
	private ContainerEntity entity;
	
	@Override
	public int getTimeInScanner() {
		return timeInScanner;
	}

	@Override
	public int getEntityId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getEntityIdent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return null;
	}

	public ContainerEntity getEntity() {
		return entity;
	}

	public void setEntity(ContainerEntity entity) {
		this.entity = entity;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setTimeInScanner(int timeInScanner) {
		this.timeInScanner = timeInScanner;
	}

	public void setEntityIdent(String entityIdent) {
		this.entityIdent = entityIdent;
	}
	
	

	
}
