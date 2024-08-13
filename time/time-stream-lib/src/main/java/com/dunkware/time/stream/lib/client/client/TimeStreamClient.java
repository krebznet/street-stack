package com.dunkware.time.stream.lib.client.client;

import java.util.Collection;

import com.dunkware.utils.core.web.DunkWebClient;

public interface TimeStreamClient {
	
	public void connect(DunkWebClient client) throws Exception;

	public Collection<TimeStream> getStreams();
	
	public TimeStream getStream(String name) throws Exception;
 
}
	
