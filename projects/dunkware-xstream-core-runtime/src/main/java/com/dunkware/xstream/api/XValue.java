package com.dunkware.xstream.api;

import com.dunkware.xstream.xScript.XValueType;

public interface XValue {
	
	void init (XValueType type, XStream stream) throws XStreamException;
	
	

}
