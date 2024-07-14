package com.dunkware.xstream.core.search.row.value;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityValueType;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityVarAggType;
import com.dunkware.xstream.model.entity.query.type.XStreamSessionTimeRange;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;

import ca.odell.glazedlists.matchers.SetMatcherEditor.Mode;

public class XStreamEntityVarAggValue implements XStreamEntityQueryValue {

	private XStreamEntityValueType model;
	private XStream stream;
	
	private XStreamEntityVarAggType aggType; 
	private XStreamSessionTimeRange timeRange; 
	private String varIdent; 
	
	
	public void init(XStreamEntityValueType model, XStream stream) throws XStreamQueryException  {
		this.model = model; 
		this.stream = stream; 
		this.aggType = model.getSessionAgg();
		this.varIdent = model.getVarIdent();
		this.timeRange = model.getSessionTimeRange();
	}
	
	
	@Override
	public boolean isResolvable(XStreamEntity row) throws XStreamQueryException {
		
		// going to have to get time range right 
		return false;
	}
	@Override
	public Number resolve(XStreamEntity row) throws XStreamResolveException, XStreamQueryException {
		
		return null;
	}


	

	

}
