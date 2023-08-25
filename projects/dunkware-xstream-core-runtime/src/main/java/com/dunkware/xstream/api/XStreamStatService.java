package com.dunkware.xstream.api;

import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatResp;

public interface XStreamStatService {
	
	EntityStatResp entityStat(EntityStatReq req) throws XStreamQueryException;

}
