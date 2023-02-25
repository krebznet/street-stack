package com.dunkware.trade.service.stream.server.stats.core;

import com.dunkware.trade.service.stream.server.stats.repository.StreamEntityDayStatsDoc;
import com.dunkware.xstream.model.stats.StreamEntityDayStats;

public class StreamStatsUtils {
	
	public static StreamEntityDayStats toDayStats(StreamEntityDayStatsDoc doc) { 
		StreamEntityDayStats stats = new StreamEntityDayStats();
		stats.setDate(doc.getDate());
		stats.setEntityIdent(doc.getEntIdent());
		stats.setEntityId(doc.getEntId());
		stats.setStreamIdent(doc.getStream());
		stats.setVariables(doc.getVars());
		stats.setSignals(doc.getSigs());
		return stats;
	}

}
