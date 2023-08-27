package com.dunkware.xstream.core.search.row.value;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.model.query.XStreamEntityVarAggHistType;
import com.dunkware.xstream.model.query.XStreamEntityValueModel;
import com.dunkware.xstream.model.query.XStreamVarAggHistType;
import com.dunkware.xstream.model.stats.EntityStatBulkResp;
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatReqType;
import com.dunkware.xstream.model.stats.EntityStatResp;
import com.dunkware.xstream.model.stats.EntityStatRespType;

public class XStreamEntityVarAggHistValue implements XStreamEntityQueryValue {

	private XStreamEntityValueModel model; 
	private XStream stream; 
	
	private XStreamEntityVarAggHistType aggType; 
	private int sessionCount; 
	
	private Map<String,EntityStatResp> cache = new ConcurrentHashMap<String,EntityStatResp>();
	
	
	
	public void init(XStreamEntityValueModel model, XStream stream, EntityStatBulkResp statResp) throws XStreamQueryException {
		this.model = model;
		this.stream = stream; 
		this.aggType = model.getHistoricalAgg();
		this.sessionCount = model.getHistoricalTimeRange().getRealtiveTimeRange();
		
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
		
	}
	
	
}
