package com.dunkware.xstream.net.core.container;

public interface ContainerEntityListener {
	
	public void init(String entityId, String entityIdentifier);
	
	void entitySignal(ContainerEntitySignal signal);
	
	void entitySnapshot(ContainerValueSet snapshot);
	
}
