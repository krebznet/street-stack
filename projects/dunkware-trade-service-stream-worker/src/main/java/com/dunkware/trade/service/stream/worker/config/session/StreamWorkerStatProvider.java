package com.dunkware.trade.service.stream.worker.config.session;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamStatProvider;
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatResp;

public class StreamWorkerStatProvider implements XStreamStatProvider {

	@Autowired
	private DunkNet dunkNet;
	
	@Override
	public EntityStatResp entityStat(EntityStatReq req) throws XStreamQueryException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
