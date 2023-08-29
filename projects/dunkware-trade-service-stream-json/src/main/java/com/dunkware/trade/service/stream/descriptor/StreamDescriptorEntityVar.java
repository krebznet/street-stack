package com.dunkware.trade.service.stream.descriptor;

import com.dunkware.common.util.data.DataType;

public class StreamDescriptorEntityVar {

	private int id; 
	private String identifier; 
	private DataType dataType;
	private double version;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public DataType getDataType() {
		return dataType;
	}
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
	public double getVersion() {
		return version;
	}
	public void setVersion(double version) {
		this.version = version;
	} 
	
	
}
