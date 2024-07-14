package com.dunkware.xstream.core.search.row.value;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;

public class XStreamEntitySignalCountValue implements XStreamEntityQueryValue {


	@Override
	public boolean isResolvable(XStreamEntity row) throws XStreamQueryException {
		
		return false;
	}

	@Override
	public Number resolve(XStreamEntity row) throws XStreamResolveException, XStreamQueryException {
		
		return null;
	}

	
	
	

}
