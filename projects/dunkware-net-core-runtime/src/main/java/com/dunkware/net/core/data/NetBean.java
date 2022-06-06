package com.dunkware.net.core.data;

import com.dunkware.net.proto.data.GBean;

public interface NetBean {
	
	public int getId();
	
	void clone(GBean bean);
	
	void setInt(String key, Integer value);
	
	void setString(String key, String value); 
	
	void setDouble(String key, Double value);
	
	/*
	 * void setDouble(String key, Double value);
	 * 
	 * void setDateTime(String key, LocalDateTime dateTime);
	 * 
	 * void setTime(String key, LocalTime time);
	 * 
	 * void setBytes(String key, byte[] bytes);
	 * 
	 * void setJson(String key, Object json) throws NetDataException;
	 * 
	 * LocalDateTime getDateTime(String key) throws NetDataException;
	 * 
	 * String getString(String key) throws NetDataException;
	 * 
	 * Double getDouble(String key) throws NetDataException;
	 * 
	 * byte[] getBytes(String key) throws NetDataException;
	 * 
	 * Object getJson(String key, Class type) throws NetDataException;
	 */
	public void addObserver(NetBeanObserver observer);
	
	public void removeObserver(NetBeanObserver observer);
	
	public GBean convert();

}
