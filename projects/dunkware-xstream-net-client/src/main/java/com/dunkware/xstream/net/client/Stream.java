package com.dunkware.xstream.net.client;

import java.util.Collection;

public interface Stream {
	
	public void connect(StreamConnector connector) throws StreamException;
	
	public StreamEntity getEntity(String ident) throws StreamException;
	
	public Collection<StreamEntity> getEntities() throws StreamException;
	
	
	
	
	
}
