package com.dunkware.time.client.lib;

import com.dunkware.time.client.lib.stream.StreamSignalSubscription;
import com.dunkware.time.script.lib.client.TimeScriptClient;

public interface TimeClient {
	
	public TimeScriptClient getScriptClient();

	
	public StreamSignalSubscription signalSubscription(String streamIdentifier, String...signalTypes);
	
	
}
