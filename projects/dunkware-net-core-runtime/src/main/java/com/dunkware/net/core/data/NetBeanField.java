package com.dunkware.net.core.data;

public interface NetBeanField {
	
	String getName();
	
	NetBeanFieldType getType();
	
	Object getValue();
	
	void setVaue(String value); 
	

}
