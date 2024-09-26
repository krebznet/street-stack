package com.dunkware.time.client.lib;

import com.dunkware.time.client.lib.exception.TimeException;
import com.dunkware.time.client.lib.script.TimeScripts;
import com.dunkware.time.client.lib.stream.TimeStreams;
import com.dunkware.utils.reactive.client.ReactiveClient;

//TODO: AVINASHANV-03 TimeClient
/**
 * so all the projects that are prefixed with time- all make up Dunkware Time Streams 
 * and the computing of the stock market is just a time stream, there are a lot of rest
 * api's into it from different micro services this interface here is going to be a composite 
 * of other clients and direct methods so that a client of a time stream does not have to import
 * alls sorts of depenencies just this. 
 */
public class TimeClient {
	
	public static TimeClient connect(String endpoint, String username, String password) throws TimeException  {
		return new TimeClient(endpoint, username, password) ;
	}
	
	
	// time-client-runtime
	// time-client-lib
	// time-client
	// Time-
	// CloudConnector
	
	// TradeBot
	
	private TimeScripts timeScripts = null;
	private TimeStreams timeStreams = null;
	private ReactiveClient webClient = null;
	
	private TimeClient(String endpoint, String username, String password) throws TimeException { 
		try {
			webClient = ReactiveClient.newInstance(endpoint, username, password);
		} catch (Exception e) {
			throw new TimeException("Exception creating client reactive " + e.toString());
		}
		try {
			webClient.getString(TimeConstants.API_GATEWAY_ECHO,null);	
		} catch (Exception e) {
			throw new TimeException("Time Connection Check Failed");
		}
		
		timeScripts = new TimeScripts(this);
		timeStreams = new TimeStreams(this);
		
	}
	
	
	public TimeScripts getScriptClient() { 
		return timeScripts;
	}
	
	public TimeStreams getStreamClient() { 
		return timeStreams; 
	}
	
	public ReactiveClient getWebClient() { 
		return webClient;
	}
	
	
}
