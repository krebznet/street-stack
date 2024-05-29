package com.dunkware.stream.data.cassy.helpers;

import java.util.HashMap;
import java.util.Map;

import com.dunkware.stream.data.cassy.entity.stats.EntityVarStats;
import com.dunkware.stream.data.cassy.entity.stats.SessionEntityStatsKey;
import com.dunkware.stream.data.cassy.entity.stats.SessionEntityStatsRow;
import com.dunkware.stream.data.model.stats.entity.EntityStatsModel;
import com.dunkware.stream.data.model.stats.entity.EntityVarStatsModel;

public class SessionEntityStatsRowHelper {
	

	
	
	public static SessionEntityStatsRow toRow(EntityStatsModel model) {
		SessionEntityStatsKey key = new SessionEntityStatsKey(model.getStream(),model.getDate(),model.getEntity());
		SessionEntityStatsRow row = new SessionEntityStatsRow();
		row.setKey(key);
		row.setSignals(model.getSigcounts());
		row.setVars(toRowVarStatsMap(model));
		return row;
		
	}
	
	private static Map<Integer, EntityVarStats> toRowVarStatsMap(EntityStatsModel model) {
		Map<Integer, EntityVarStats> results = new HashMap<Integer, EntityVarStats>();
		for (Integer varId : model.getVarstats().keySet() ) {
			EntityVarStatsModel var = model.getVarstats().get(varId);
			results.put(varId, toRowVarStats(var));
		}
		return results;
		
	}

	private static EntityVarStats toRowVarStats(EntityVarStatsModel input) {
		EntityVarStats varStats = new EntityVarStats();
		varStats.setStats(input.getStats());
		varStats.setTimes(input.getTimes());
		return varStats;

	}

}
