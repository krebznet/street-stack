package com.dunkware.net.core.messaging;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.dunkware.net.core.runtime.proto.core.DateTime;

public interface DMessage {

	int getMessageId();
	
	void setCallbackid(int id);
	
	void setInt(String key, Integer value);
	
	void setDouble(String key, Double value);
	
	void setDateTime(String key, LocalDateTime dateTime);
	
	void setTime(String key, LocalTime time);
	
	void setBytes(String key, byte[] bytes);
	
	void setJson(String key, Object json) throws DException; 
	
	DateTime getDateTime(String key) throws DException;
	
	String getString(String key) throws DException;
	
	Double getDouble(String key) throws DException;
	
	byte[] getBytes(String key) throws DException;
	
	Object getJson(String key, Class type) throws DException;
	
	
	
}
