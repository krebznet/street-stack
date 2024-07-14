package com.dunkware.stream.data.cassy.helpers;

import java.util.HashMap;
import java.util.Map;

import com.dunkware.stream.data.cassy.entity.EntityStats;
import com.dunkware.stream.data.cassy.entity.EntityStatsKey;
import com.dunkware.stream.data.cassy.entity.EntityVarStats;
import com.dunkware.time.data.model.entity.EntityStatsModel;
import com.dunkware.time.data.model.entity.EntityVarStatsModel;

public class SessionEntityStatsRowHelper {
	

	
	
	public static EntityStats toRow(EntityStatsModel model) {
		EntityStatsKey key = new EntityStatsKey(model.getStream(),model.getEntity(),model.getDate());
		EntityStats row = new EntityStats();
		row.setKey(key);
		row.setSignals(model.getSigcounts());
		row.setVars(toRowVarStatsMap(model));
		return row;
		
	}
	
	private static Map<Integer,EntityVarStats> toRowVarStatsMap(EntityStatsModel model) {
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
