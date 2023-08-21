package com.dunkware.xstream.core.search.row.values;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.core.search.row.XStreamRowValue;
import com.dunkware.xstream.model.query.XStreamRowValueModel;
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatReqType;
import com.dunkware.xstream.model.stats.EntityStatResp;
import com.dunkware.xstream.model.stats.EntityStatRespType;

public class XStreamRowSignalHistoricalCountValue implements XStreamRowValue {

	
	private XStreamRowValueModel model; 
	private XStream stream; 
	
	private Map<String,EntityStatResp> cache = new ConcurrentHashMap<String,EntityStatResp>();
	
	
	@Override
	public void init(XStreamRowValueModel model, XStream stream) throws XStreamQueryException {
		this.model = model;
		this.stream = stream; 
	}

	@Override
	public boolean canResolve(XStreamEntity row) throws XStreamQueryException {
		EntityStatResp resp = cache.get(row.getId());
		if(resp == null) { 
			// make a new request
			EntityStatReq req = new EntityStatReq();
			req.setStream(stream.getInput().getIdentifier());
			req.setRelativeDays(model.getHistoricalTimeRange().getRealtiveTimeRange());
			req.setEntity(row.getId());
			req.setTarget(model.getSignalIdent());
			req.setType(EntityStatReqType.SignalCountRelative);
			// okay we have a request here now we need to run it? 
			// block here 
		}
		if(resp.getType() == EntityStatRespType.Resolved)  {
			return true;
		}
		return false;
	}

	@Override
	public Number resolve(XStreamEntity row) throws XStreamResolveException, XStreamQueryException {
		// assuming it returned true. we shoudl get resp and get number
		EntityStatResp resp = cache.get(row.getId());
		if(resp == null) { 
			throw new XStreamQueryException("Internal exception resolve invoked canResolve returned ture no EntityStatsResp " + row.getId());
		}
		if(resp.getType() != EntityStatRespType.Resolved) { 
			throw new XStreamQueryException("Exception in code logic, resolve entity resp is type " + resp.getType().name());
		}
		return resp.getValue();
	}
	
	
	

}
