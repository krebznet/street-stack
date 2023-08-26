package com.dunkware.xstream.api;

import com.dunkware.xstream.xproject.model.XStreamExtensionType;

public interface XStreamExtension {

	public void init(XStream stream, XStreamExtensionType type) throws XStreamException;
	
	public void preStart() throws XStreamException;
	
	public void start() throws XStreamException;
	
	public void cancel();
	
	public void preDispose(); 
	
	public void dispose();
	
}
