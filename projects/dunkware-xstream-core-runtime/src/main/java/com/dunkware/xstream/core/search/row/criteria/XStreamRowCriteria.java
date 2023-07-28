package com.dunkware.xstream.core.search.row.criteria;

import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.api.XStreamRow;

public interface XStreamRowCriteria {
	
	public boolean canResolve(XStreamRow row) throws XStreamQueryException, XStreamResolveException;
	
	public boolean test(XStreamRow row) throws XStreamResolveException, XStreamQueryException;

}
