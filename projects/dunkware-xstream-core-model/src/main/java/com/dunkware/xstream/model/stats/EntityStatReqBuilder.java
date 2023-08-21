package com.dunkware.xstream.model.stats;

import java.time.LocalDate;

public class EntityStatReqBuilder {

	
	public static EntityStatReqBuilder builder() { 
		return new EntityStatReqBuilder();
	}
	
	private EntityStatReq req = new EntityStatReq();
	
	public EntityStatReqBuilder realitveVarHigh(String stream, String entity, String var, LocalDate fromDate, int relativeDays) { 
		req.setStream(stream);
		req.setTarget(var);
		req.setEntity(entity);
		req.setDate(fromDate);
		req.setRelativeDays(relativeDays);
		req.setType(EntityStatReqType.VarHighRelative);
		return this;
	}
	
	public EntityStatReq build() { 
		return req;
	}
}
