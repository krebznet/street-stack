package com.dunkware.xstream.api;

import java.util.concurrent.Future;

import com.dunkware.xstream.model.stats.proto.EntityStatBulkReq;
import com.dunkware.xstream.model.stats.proto.EntityStatBulkResp;
import com.dunkware.xstream.model.stats.proto.EntityStatReq;
import com.dunkware.xstream.model.stats.proto.EntityStatResp;

public interface XStreamStatService {
	
	Future<EntityStatBulkResp> entityStatBulkRequest(EntityStatBulkReq req);
	
	EntityStatResp entityStat(EntityStatReq req) throws XStreamQueryException;

}
