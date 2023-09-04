package com.dunkware.trade.net.service.streamstats.client.proto;

import java.time.LocalDate;

import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.trade.net.service.streamstats.client.StreamStatsClientException;
import com.dunkware.xstream.model.stats.proto.EntityStatReq;
import com.dunkware.xstream.model.stats.proto.EntityStatReqType;

public class StreamStatsRequest {

	private String reqId;
	private EntityStatReq req; 
	private String respTopic;
	
	
	public static StreamStatsRequestBuilder builder() { 
		return new StreamStatsRequestBuilder();
	}
	
	public static class StreamStatsRequestBuilder { 
		
		
		private EntityStatReq req = new EntityStatReq();
		
		
		public StreamStatsRequestBuilder stream(String stream) { 
			this.req.setStream(stream);
			return this;
		}
		
		public StreamStatsRequestBuilder varHighReative(String var) { 
			req.setType(EntityStatReqType.VarHighRelative);
			this.req.setTarget(var);
			return this;
		}
		
		public StreamStatsRequestBuilder entity(String ident) { 
			req.setEntity(ident);
			return this;
		}
		
		public StreamStatsRequestBuilder relativeDays(int days) { 
			req.setRelativeDays(days);
			return this;
		}
		
		public StreamStatsRequestBuilder varLowRelative(String var) { 
			req.setType(EntityStatReqType.VarLowRleative);
			req.setTarget(var);
			return this;
			
		}
		
		public StreamStatsRequestBuilder date(LocalDate date) { 
			this.req.setDate(date);
			return this;
		}
		
		
		
		
		public StreamStatsRequest buid() throws StreamStatsClientException { 
			StreamStatsRequest req = new StreamStatsRequest();
			req.setReqId(DUUID.randomUUID(6));
			if(this.req.getType() == null ) { 
				throw new StreamStatsClientException("req type not set");
			}
			if(this.req.getDate() == null) { 
				throw new StreamStatsClientException("date is not set");
			}
			if(this.req.getEntity() == null) { 
				throw new StreamStatsClientException("entity is not set");
			}
			 
				if(this.req.getTarget() == null) { 
					throw new StreamStatsClientException("target is null");
				}
			req.setReq(this.req);
			req.setRespTopic("stream_stat_resp");
			return req;
		}
		
		
		
	}
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
