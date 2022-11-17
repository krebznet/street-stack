package com.dunkware.xstream.api;

import com.dunkware.xstream.model.value.SessionEntityValue;

public interface XStreamRowValue {
	
	void init(SessionEntityValue model, XStreamRow row) throws XStreamRuntimeException;
	
	boolean isValid();
	
	boolean isResoolvable();
	
	Object resolve() throws XStreamRuntimeException;

}
