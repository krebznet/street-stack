package com.dunkware.xstream.core.search.row.value;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.model.query.XStreamEntityValueModel;
import com.dunkware.xstream.model.query.XStreamEntityVarAggHistType;
import com.dunkware.xstream.model.stats.EntityStatBulkResp;

public class XStreamEntityVarAggHistValue implements XStreamEntityQueryValue {

	private XStreamEntityValueModel model; 
	private XStream stream; 
	
	private XStreamEntityVarAggHistType aggType; 
	
	private EntityStatBulkResp statResponse;
	
	public void init(XStreamEntityValueModel model, XStream stream, EntityStatBulkResp statResp) throws XStreamQueryException {
		this.model = model;
		this.stream = stream; 
		this.statResponse = statResp;
		this.aggType = model.getHistoricalAgg();
		
	}
	

	@Override
	public boolean isRunnable(XStreamEntity entity) throws XStreamQueryException {
		String exception = statResponse.getExceptionStats().get(entity.getId());
		if(exception != null) { 
			throw new XStreamQueryException("Entity " + entity.getId() + " var agg stat exception " + exception);
		}
		if(statResponse.getResolvedStats().containsKey(entity.getId())) { 
			return true;
		}
		
		return false; 
	}



	@Override
	public boolean isResolvable(XStreamEntity row) throws XStreamQueryException {
		return isRunnable(row);
	}



	@Override
	public Number resolve(XStreamEntity row) throws XStreamResolveException, XStreamQueryException {
		return statResponse.getResolvedStats().get(row.getId());

	}
	
	
}
