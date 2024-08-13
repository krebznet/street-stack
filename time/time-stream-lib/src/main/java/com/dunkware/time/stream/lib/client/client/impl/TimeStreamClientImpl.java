package com.dunkware.time.stream.lib.client.client.impl;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.time.stream.lib.client.client.TimeStream;
import com.dunkware.time.stream.lib.client.client.TimeStreamClient;
import com.dunkware.utils.core.web.DunkWebClient;

//TODO: AVINASHANV-02 HttpTimeScriptClient
/**
 * This is an example of using the DunkWebClient line 57 uses it, the pattern is 
 * wrapping a set of related rest URL's into a client class, in this case this class
 * can create new stream scripts, compare local and remote scripts with domain models
 * and deploy updated scripts as release, i decided to not go with git. this is integrated
 * into Street Studio with wizards. 
 */
public class TimeStreamClientImpl implements TimeStreamClient {
	
	private static final String EndPointGetXScriptModel = "";

	private DunkWebClient client; 

	private Map<String,TimeStreamImpl> streams = new ConcurrentHashMap<String,TimeStreamImpl>();

	@Override
	public void connect(DunkWebClient client) throws Exception {
		this.client = client;
		
	}


	@Override
	public Collection<TimeStream> getStreams() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public TimeStream getStream(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
