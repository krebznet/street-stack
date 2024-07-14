package com.dunkware.time.client.lib;

import com.dunkware.time.client.lib.stream.StreamSignalSubscription;
import com.dunkware.time.script.lib.client.TimeScriptClient;

//TODO: AVINASHANV-03 TimeClient
/**
 * so all the projects that are prefixed with time- all make up Dunkware Time Streams 
 * and the computing of the stock market is just a time stream, there are a lot of rest
 * api's into it from different micro services this interface here is going to be a composite 
 * of other clients and direct methods so that a client of a time stream does not have to import
 * alls sorts of depenencies just this. 
 */
public interface TimeClient {
	
	public TimeScriptClient getScriptClient();

	
	public StreamSignalSubscription signalSubscription(String streamIdentifier, String...signalTypes);
	
	
}
