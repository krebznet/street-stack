package com.dunkware.xstream.core.stats;

import java.util.List;

import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.model.stats.EntityStats;
import com.dunkware.xstream.model.stats.SessionStats;
import com.dunkware.xstream.xScript.DataType;
import com.dunkware.xstream.xScript.VarType;

public class StreamStatsHelper {

	public static boolean isNumeric(XStreamVar var) {
		VarType type = var.getVarType();
		DataType dataType = type.getType();

		if (dataType == DataType.BO_OL)
			return false;
		if (dataType == DataType.DATE)
			return false;
		if (dataType == DataType.STR)
			return false;
		return true;

	}
	
	
	public static SessionStats toSessionStats(List<EntityStats> entities, StreamStatsExtType type) { 
		SessionStats stats = new SessionStats();
		for (EntityStats entityStats : entities) {
			stats.getEntityStats().add(entityStats);
		}
		stats.setSessionId(type.getSessionId());
		stats.setStreamIdent(stats.getStreamIdent());
		return stats;
	}

	

}
