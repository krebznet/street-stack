package com.dunkware.trade.service.stream.server.stats.core;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.trade.service.stream.server.stats.repository.SessionEntityStatsDoc;
import com.dunkware.xstream.model.stats.EntityStats;
import com.dunkware.xstream.model.stats.EntityVarStats;

public class StreamStatsHelper {
	
	
	public static SessionEntityStatsDoc toSessionEntityStatsDoc(EntityStats stats) { 
		SessionEntityStatsDoc doc = new SessionEntityStatsDoc();
		doc.setEntityId(stats.getEntityId());
		doc.setEntityIdent(stats.getEntityIdent());
		doc.setFrom(stats.getFrom());
		doc.setTo(stats.getTo());
		// might want to change this here 
		doc.setId(DRandom.getRandom(1, 200000));
		doc.setStreamId(stats.getStreamId());
		for (EntityVarStats varStats : stats.getVarStats()) {
			doc.getVarStats().add(varStats);
		}
		return doc;
		
	}

}
