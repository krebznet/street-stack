package com.dunkware.xstream.core.search.row.value;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.model.query.XStreamEntityValueModel;

public class XStreamEntitySignalCountHistValue implements XStreamEntityQueryValue {

	
	private XStreamEntityValueModel model; 
	private XStream stream; 
	private String varIdent; 
	
	public void init(XStreamEntityValueModel model, XStream stream) throws XStreamQueryException {
		this.model = model;
		this.stream = stream; 
	}


	@Override
	public boolean isRunnable(XStreamEntity entity) throws XStreamQueryException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isResolvable(XStreamEntity row) throws XStreamQueryException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Number resolve(XStreamEntity row) throws XStreamResolveException, XStreamQueryException {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	
	
	

}
