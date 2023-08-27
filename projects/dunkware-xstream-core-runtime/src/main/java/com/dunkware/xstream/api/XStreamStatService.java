package com.dunkware.xstream.api;

import java.util.concurrent.Future;

import com.dunkware.xstream.model.stats.EntityStatBulkReq;
import com.dunkware.xstream.model.stats.EntityStatBulkResp;
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatResp;

public interface XStreamStatService {
	
	Future<EntityStatBulkResp> entityStatBulkRequest(EntityStatBulkReq req);
	
	EntityStatResp entityStat(EntityStatReq req) throws XStreamQueryException;

}
