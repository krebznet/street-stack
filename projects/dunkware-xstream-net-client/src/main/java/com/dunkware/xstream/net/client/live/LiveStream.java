package com.dunkware.xstream.net.client.live;

import java.util.Collection;

import com.dunkware.xstream.net.client.Stream;
import com.dunkware.xstream.net.client.StreamConnector;
import com.dunkware.xstream.net.client.StreamEntity;
import com.dunkware.xstream.net.client.StreamException;

public class LiveStream implements Stream {

	private StreamConnector connector; 
	
	@Override
	public void connect(StreamConnector connector) throws StreamException {
		// hits a web service --> 
		// going to have a topic for entity updates
		// a second topic for entity field snapshots 
		
	}

	@Override
	public StreamEntity getEntity(String ident) throws StreamException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<StreamEntity> getEntities() throws StreamException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
