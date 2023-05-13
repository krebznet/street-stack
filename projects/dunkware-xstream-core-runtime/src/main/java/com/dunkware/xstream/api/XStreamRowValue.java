package com.dunkware.xstream.api;

import com.dunkware.xstream.model.search.XStreamValue;

public interface XStreamRowValue {
	
	void init(XStreamValue model, XStreamRow row) throws XStreamException; 
	
	boolean isValid();
	
	boolean isResoolvable();
	
	boolean isSessionResolvable();
	
	Object resolve() throws XStreamRuntimeException;

}
