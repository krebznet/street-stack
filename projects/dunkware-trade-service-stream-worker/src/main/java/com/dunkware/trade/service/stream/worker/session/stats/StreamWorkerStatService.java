package com.dunkware.trade.service.stream.worker.session.stats;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.model.stats.proto.EntityStatReq;
import com.dunkware.xstream.model.stats.proto.EntityStatResp;

public class StreamWorkerStatService {

	private DunkNet dunkNet; 
	
	
	public EntityStatResp entityStatReq(EntityStatReq req) throws XStreamQueryException {
		try {
			EntityStatResp resp = (EntityStatResp)dunkNet.serviceBlocking(req);	
			return resp;
		} catch (Exception e) {
			throw new XStreamQueryException("Exception getting stat " + e.toString());
		}
		
	}
	
	
}
