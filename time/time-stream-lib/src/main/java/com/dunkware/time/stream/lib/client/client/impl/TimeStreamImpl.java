package com.dunkware.time.stream.lib.client.client.impl;

import java.util.List;

import com.dunkware.time.stream.lib.client.client.TimeStream;
import com.dunkware.utils.core.web.DunkWebClient;
import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;

public class TimeStreamImpl implements TimeStream {
	
	private String name; 
	private DunkWebClient client; 
	
	private TimeStreamImpl(DunkWebClient client, String streamName) throws Exception { 
		this.name = streamName; 
		this.client = client; 
	}

	@Override
	public XScriptDescriptor getScriptModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getBotNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String geStreamName() {
		return name; 
	}

	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	

}
