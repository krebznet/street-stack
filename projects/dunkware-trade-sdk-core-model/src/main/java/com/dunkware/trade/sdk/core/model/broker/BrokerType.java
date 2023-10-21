package com.dunkware.trade.sdk.core.model.broker;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public interface BrokerType {

	String getIdentifier();
	
	
}
