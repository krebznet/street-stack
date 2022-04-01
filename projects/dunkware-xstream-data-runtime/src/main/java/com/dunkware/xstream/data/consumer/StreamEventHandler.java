package com.dunkware.xstream.data.consumer;

import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;

public interface StreamEventHandler {
	
	public void entitySnapshot(GEntitySnapshot snapshot);
	
	
	public void entitySignal(GEntitySignal signal);

}
