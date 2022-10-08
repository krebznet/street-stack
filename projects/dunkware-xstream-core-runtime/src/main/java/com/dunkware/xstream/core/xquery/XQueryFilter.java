package com.dunkware.xstream.core.xquery;

import com.dunkware.xstream.api.XQueryException;
import com.dunkware.xstream.api.XStreamRow;

public interface XQueryFilter {
	
	boolean canEvaluate();
	
	boolean match(XStreamRow row) throws XQueryException;

}
