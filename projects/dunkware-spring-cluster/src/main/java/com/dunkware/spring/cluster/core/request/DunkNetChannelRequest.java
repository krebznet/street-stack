package com.dunkware.spring.cluster.core.request;

import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.anot.ADunkNetChannel;

public class DunkNetChannelRequest {
	
	public DunkNetChannel getChannel() {
		// will block until the client has initialized
		return null;
	}
	
	// called by client 
	public void start() { 
		// sends message to the server channel
		// unblocks startWait
	}
	
	public void closeChanell() { 
		// called by either party? 
	}
	
	public void onClosed() { 
		
	}
	
	public void onStarted() { 
		
	}
	
	public void startWait() { 
		// messsage comes back and says bam. 
	}
	
	public void setResponse(Object response) { 
		
	}
	
	public void setError(String error) { 
		
	}

}
