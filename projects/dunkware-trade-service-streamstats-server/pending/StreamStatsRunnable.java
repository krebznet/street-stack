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

	public EntityStatResp request(EntityStatReq req) {
		StreamStats stats = null;
		EntityStatResp resp = new EntityStatResp();
		try {
			stats = controller.getstreamStats(req.getStream());
		} catch (Exception e) {
			resp.setException("Exception finding stream stats for " + req.getStream());
			resp.setType(EntityStatRespType.Exception);
			return resp;
		}
		

	}

	@Override
	public void run() {
		StreamStatsResponse resp = new StreamStatsResponse();
		EntityStatResp statResp = new EntityStatResp();
		EntityStatReq statReq = req.getReq();
		try {
			StreamStats stats = controller.getstreamStats(req.getReq().getStream());

			try {
				StreamStatsEntity statsEntity = stats.getEntity(statReq.getEntity());
				if (statReq.getType() == EntityStatReqType.VarHighRelative) {
					try {
						Number hope = statsEntity.resolveVarRelativeHigh(statReq.getDate(), statReq.getTarget(),
								statReq.getRelativeDays());
						statResp.setValue(hope);
						statResp.setType(EntityStatRespType.Resolved);
						resp.setResp(statResp);
						resp.setReqId(req.getReqId());
						controller.sendResponse(req, resp);
						return;
					} catch (Exception e) {
						statResp.setException(e.toString());
						statResp.setType(EntityStatRespType.Exception);
						resp.setReqId(req.getReqId());
						resp.setResp(statResp);
						controller.sendResponse(req, resp);
						return;
					}

				}
				statResp.setException("Unahdled historical var agg " + req.getReq().getType().name());
				statResp.setType(EntityStatRespType.Exception);
				resp.setResp(statResp);
				controller.sendResponse(req, resp);
			} catch (Exception e) {
				statResp.setException("Exception getting entity stats " + req.getReq().getEntity());
				statResp.setType(EntityStatRespType.Exception);
				resp.setReqId(req.getReqId());
				resp.setResp(statResp);
				controller.sendResponse(req, resp);
			}

		} catch (Exception e) {
			statResp.setType(EntityStatRespType.Exception);
			statResp.setException("Stream " + req.getReq().getStream() + " not found");
			statResp.setValue(null);
			resp.setReqId(req.getReqId());
			resp.setResp(statResp);
			controller.sendResponse(req, resp);
		}

	}

}
