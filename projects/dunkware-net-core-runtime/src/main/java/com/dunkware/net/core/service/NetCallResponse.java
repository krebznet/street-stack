package com.dunkware.net.core.service;

import com.dunkware.net.core.data.NetBean;

public interface NetCallResponse {
	
	public boolean hasException();
	
	public String getException();
	
	public int getRequestId();
	
	public NetBean getData();
	
	public void setException(String exception);
	
	public void setString(String field, String value);
	
	public void setDouble(String field, Double value);
	
	public void setJson(String field, Object value);

}
