package com.dunkware.time.stream.lib.client.client;

import java.util.List;

import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;

public interface TimeStream {

	public XScriptDescriptor getScriptModel();
	
	public List<String> getBotNames();
	
	public String geStreamName();
	
	public boolean isRunning();
	
	
}
