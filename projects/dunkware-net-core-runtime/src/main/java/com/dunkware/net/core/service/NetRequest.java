package com.dunkware.net.core.service;

import com.dunkware.net.core.data.NetBean;

public interface NetRequest {

	int getRequestId();

	String getEndpoint();

	NetBean getData();

	String getReplyTopic();

	String getString(String field) throws NetServiceException;

	Double getDouble(String field) throws NetServiceException;

	Integer getInt(String field) throws NetServiceException;

	Object getJson(String field, Class type) throws NetServiceException;

}
