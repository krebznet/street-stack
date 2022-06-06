package com.dunkware.net.core.service;

import com.dunkware.net.core.data.GBeanReader;

public interface NetRequest {

	int getRequestId();

	String getEndpoint();

	GBeanReader getData();


}
