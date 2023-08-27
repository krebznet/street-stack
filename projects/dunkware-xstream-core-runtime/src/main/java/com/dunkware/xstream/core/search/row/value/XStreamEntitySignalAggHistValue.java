package com.dunkware.xstream.core.search.row.value;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.model.query.XStreamRowValueModel;
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatReqType;
import com.dunkware.xstream.model.stats.EntityStatResp;
import com.dunkware.xstream.model.stats.EntityStatRespType;

public class XStreamEntitySignalAggHistValue implements XStreamEntityQueryValue {

	
	private XStreamRowValueModel model; 
	private XStream stream; 
	
	private String varIdent; 
	private XStreama
	
	private Map<String,EntityStatResp> cache = new ConcurrentHashMap<String,EntityStatResp>();
	
	
	@Override
	public void init(XStreamRowValueModel model, XStream stream) throws XStreamQueryException {
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
