package com.dunkware.net.core.data;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.dunkware.net.proto.data.GBean;

public interface NetBean {
	
	void setInt(String key, Integer value);
	
	void setDouble(String key, Double value);
	
	void setDateTime(String key, LocalDateTime dateTime);
	
	void setTime(String key, LocalTime time);
	
	void setBytes(String key, byte[] bytes);
	
	void setJson(String key, Object json) throws NetDataException; 
	
	LocalDateTime getDateTime(String key) throws NetDataException;
	
	String getString(String key) throws NetDataException;
	
	Double getDouble(String key) throws NetDataException;
	
	byte[] getBytes(String key) throws NetDataException;
	
	Object getJson(String key, Class type) throws NetDataException;
	
	public void addObserver(NetBeanObserver observer);
	
	public void removeObserver(NetBeanObserver observer);
	
	public GBean toProtoBean();

}
