package com.dunkware.xstream.core.search.row.criteria;

import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.api.XStreamEntity;

public interface XStreamRowCriteria {
	
	public boolean canResolve(XStreamEntity row) throws XStreamQueryException, XStreamResolveException;
	
	public boolean test(XStreamEntity row) throws XStreamResolveException, XStreamQueryException;

}
