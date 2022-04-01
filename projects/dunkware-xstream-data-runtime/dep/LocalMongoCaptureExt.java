package com.dunkware.xstream.data.capture;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamExtension;
import com.dunkware.xstream.core.annotations.AXStreamExtension;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

@AXStreamExtension(type = MongoCaptureExtType.class)
public class LocalMongoCaptureExt implements XStreamExtension {

	
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(XStream arg0, XStreamExtensionType arg1) throws XStreamException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preDispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preStart() throws XStreamException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() throws XStreamException {
		// TODO Auto-generated method stub
		
	}
	
	

}
