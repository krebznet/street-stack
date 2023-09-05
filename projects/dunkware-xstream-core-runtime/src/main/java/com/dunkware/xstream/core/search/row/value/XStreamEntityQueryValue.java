package com.dunkware.xstream.core.search.row.value;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;

public interface XStreamEntityQueryValue {
	
	public boolean isResolvable(XStreamEntity row) throws XStreamQueryException;
	
	public Number resolve(XStreamEntity row) throws XStreamResolveException, XStreamQueryException;
		
	
}
