package com.dunkware.trade.net.service.streamstats.client.proto;

import com.dunkware.xstream.model.stats.EntityStatReq;

public class StreamStatsRequest {

	private String reqId;
	private EntityStatReq req; 
	private String respTopic;
	
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public EntityStatReq getReq() {
		return req;
	}
	public void setReq(EntityStatReq req) {
		this.req = req;
	}
	public String getRespTopic() {
		return respTopic;
	}
	public void setRespTopic(String respTopic) {
		this.respTopic = respTopic;
	}
	

	
	
	
	
}
