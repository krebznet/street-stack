package com.dunkware.trade.net.service.streamstats.server.controller;

import com.dunkware.trade.net.service.streamstats.client.proto.StreamStatsRequest;
import com.dunkware.trade.net.service.streamstats.client.proto.StreamStatsResponse;
import com.dunkware.trade.net.service.streamstats.server.service.StreamStats;
import com.dunkware.trade.net.service.streamstats.server.service.StreamStatsEntity;
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatReqType;
import com.dunkware.xstream.model.stats.EntityStatResp;
import com.dunkware.xstream.model.stats.EntityStatRespType;

public class StreamStatsRunnable implements Runnable {

	private StreamStatsController controller;
	private StreamStatsRequest req; 
	
	
	public void init(StreamStatsRequest request, StreamStatsController controler) { 
		this.req = request;
		this.controller = controler;
	}
	
	
	@Override
	public void run() {
		StreamStatsResponse resp = new StreamStatsResponse();
		EntityStatResp statResp = new EntityStatResp();
		try {
			StreamStats stats = controller.getstreamStats(req.getReq().getStream());	
			EntityStatReq statReq = req.getReq();
			try {
				StreamStatsEntity statsEntity = stats.getEntity(statReq.getEntity());	
				// all you need here is to figure out how to get the fuckin stts
				//EntityStatReqType.VarHighRelative;
				//EntityStatReqType.VarLowRelative
				//EntityStatReqType.SignalCountRelative
			} catch (Exception e) {
				statResp.setException("Exception getting entity stats " + req.getReq().getEntity());
				statResp.setType(EntityStatRespType.Exception);
				resp.setReqId(req.getReqId());
				resp.setResp(statResp);
				controller.sendResponse(req,resp);
			}
			
		} catch (Exception e) {
			statResp.setType(EntityStatRespType.Exception);
			statResp.setException("Stream " + req.getReq().getStream() + " not found");
			statResp.setValue(null);
			resp.setReqId(req.getReqId());
			resp.setResp(statResp);
			controller.sendResponse(req,resp);
		}
		
	}
	
	
	
	
	

}
