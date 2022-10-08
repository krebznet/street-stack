package com.dunkware.xstream.core.xquery;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XQuery;
import com.dunkware.xstream.api.XQueryException;
import com.dunkware.xstream.api.XQueryResults;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.xScript.XQueryType;

public class XQueryImpl implements XQuery {

	private XQueryType type;
	
	public void init(XQueryType type) throws XQueryException { 
		this.type = type; 
		
	}
	
	@Override
	public XQueryResults execute() throws XQueryException {
		// TODO Auto-generated method stub
		return null;
	}

	
	


}
