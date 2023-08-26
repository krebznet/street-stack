package com.dunkware.trade.service.stream.worker.session;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatResp;

public class StreamSessionWorkerStatProvider {

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
