package com.dunkware.xstream.core.search.row;

import java.util.List;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRowQuery;
import com.dunkware.xstream.api.XStreamRowQueryResults;
import com.dunkware.xstream.model.query.XStreamQueryModel;

public class XStreamRowQueryImpl implements XStreamRowQuery   {

	private XStream stream; 
	private XStreamQueryModel model; 
	
	
	@Override
	public void init(XStreamQueryModel model, XStream stream) throws XStreamQueryException {
		this.model = model;
		this.stream = stream; 
	
		
	}



	@Override
	public XStreamRowQueryResults query(List<XStreamRow> rows) throws XStreamQueryException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	

}
