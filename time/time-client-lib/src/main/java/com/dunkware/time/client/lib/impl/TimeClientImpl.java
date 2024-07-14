package com.dunkware.time.client.lib.impl;

import com.dunkware.time.client.lib.TimeClient;
import com.dunkware.time.client.lib.stream.StreamSignalSubscription;
import com.dunkware.time.data.model.entity.EntitySignalModel;
import com.dunkware.time.script.lib.client.TimeScriptClient;
import com.dunkware.time.script.lib.client.impl.HttpTimeScriptClient;
import com.dunkware.utils.core.web.DunkWebClient;
//TODO: AVINASHANV-4 TimeClientImpl
/**
 * this is the implementation of the interface, notice it creates an instance 
 * of TimeScriptClient specific features will have their own clients and this will
 * just generate instances and add getters. 
 */
public class TimeClientImpl implements TimeClient {

	private HttpTimeScriptClient scriptClient; 
	
	
	private String baseURL; 
	private String username; 
	private String password; 
	
	
	private DunkWebClient webClient; 
	
	private TimeClientImpl(String baseURL, String username, String password) throws Exception{ 
		this.baseURL = baseURL;
		this.username = username;; 
		this.password = password;
		
		webClient = DunkWebClient.newInstance(baseURL, username, password);
		scriptClient = HttpTimeScriptClient.instance(baseURL, username, password);
	}
	
	@Override
	public TimeScriptClient getScriptClient() {
		return scriptClient;
	}

	//TODO: AVINASHANV-4 Signal Subscriptions
	/**
	 * this is getting to the core of one thing we need, the ability to consume signals without 
	 * kafka, so the concept of a SIgnalSubscription is you pass in the stream identifier always
	 * us-equity for now and then a list of signal names to subscribe to. this here will be reactive
	 * and i am trying to encapsulate it into a StreamSignalSubscription not directly in this method 
	 * i will show way
	 */
	@Override
	public StreamSignalSubscription signalSubscription(String streamIdentifier, String... signalTypes) {
		// here we need to make a reactive call and get back a WebFlux (i think) that we consume 
		EntitySignalModel model; // objecs of that type from and then 
		
		return null;
	}
	
	

}
