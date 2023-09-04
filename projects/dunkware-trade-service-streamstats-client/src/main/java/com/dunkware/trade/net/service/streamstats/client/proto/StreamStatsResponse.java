package com.dunkware.trade.net.service.streamstats.client.proto;

import com.dunkware.xstream.model.stats.proto.EntityStatResp;

public class StreamStatsResponse {
	
	private String reqId;
	private double time;
	
	private EntityStatResp resp;
	
	public EntityStatResp getResp() {
		return resp;
	}
	public void setResp(EntityStatResp resp) {
		this.resp = resp;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	
	
	
	
	
	
	

}
