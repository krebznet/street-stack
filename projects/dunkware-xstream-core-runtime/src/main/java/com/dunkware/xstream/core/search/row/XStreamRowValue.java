package com.dunkware.xstream.core.search.row;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.model.query.XStreamRowValueModel;

public interface XStreamRowValue {
	
	public void init(XStreamRowValueModel model, XStream stream) throws XStreamQueryException ;
	
	public boolean canResolve(XStreamEntity row) throws XStreamQueryException;
	
	public Number resolve(XStreamEntity row) throws XStreamResolveException, XStreamQueryException;
		
	
}
