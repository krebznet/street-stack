package com.dunkware.xstream.core.query;

import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamRow;

public interface XQueryFilter {
	
	boolean canEvaluate();
	
	boolean match(XStreamRow row) throws XStreamQueryException;

}
