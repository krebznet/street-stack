package com.dunkware.xstream.core.search;

import com.dunkware.xstream.api.XQueryException;
import com.dunkware.xstream.api.XStreamRow;

public interface XQueryFilter {
	
	boolean canEvaluate();
	
	boolean match(XStreamRow row) throws XQueryException;

}
