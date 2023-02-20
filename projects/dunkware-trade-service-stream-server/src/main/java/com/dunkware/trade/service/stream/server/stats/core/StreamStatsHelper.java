package com.dunkware.trade.service.stream.server.stats.core;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.trade.service.stream.server.stats.repository.StreamEntityDayStatsDoc;
import com.dunkware.xstream.model.stats.StreamEntityDayStats;

public class StreamStatsHelper {
	
	/**
	 * 
	 * @param stats
	 * @return
	 */
	public static StreamEntityDayStatsDoc toStreamEntityDayStatsDoc(StreamEntityDayStats stats ) {
		StreamEntityDayStatsDoc doc = new StreamEntityDayStatsDoc();
		doc.setDate(stats.getDate());
		doc.setEntityId(stats.getEntityId());
		doc.setEntityIdent(stats.getEntityIdent());
		doc.setVariables(stats.getVariables());
		doc.setSignals(stats.getSignals());
		doc.setId(DRandom.getRandom(1, 443434344));
		return doc;
		
	}

}
