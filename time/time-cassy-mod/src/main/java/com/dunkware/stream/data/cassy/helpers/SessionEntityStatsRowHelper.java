package com.dunkware.stream.data.cassy.helpers;

import java.util.HashMap;
import java.util.Map;

import com.dunkware.stream.data.cassy.entity.stats.DBEntityVarStats;
import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatsKey;
import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatsRow;
import com.dunkware.time.data.model.entity.EntityStatsModel;
import com.dunkware.time.data.model.entity.EntityVarStatsModel;

public class SessionEntityStatsRowHelper {
	

	
	
	public static DBSessionEntityStatsRow toRow(EntityStatsModel model) {
		DBSessionEntityStatsKey key = new DBSessionEntityStatsKey(model.getStream(),model.getDate(),model.getEntity());
		DBSessionEntityStatsRow row = new DBSessionEntityStatsRow();
		row.setKey(key);
		row.setSignals(model.getSigcounts());
		row.setVars(toRowVarStatsMap(model));
		return row;
		
	}
	
	private static Map<Integer, DBEntityVarStats> toRowVarStatsMap(EntityStatsModel model) {
		Map<Integer, DBEntityVarStats> results = new HashMap<Integer, DBEntityVarStats>();
		for (Integer varId : model.getVarstats().keySet() ) {
			EntityVarStatsModel var = model.getVarstats().get(varId);
			results.put(varId, toRowVarStats(var));
		}
		return results;
		
	}

	private static DBEntityVarStats toRowVarStats(EntityVarStatsModel input) {
		DBEntityVarStats varStats = new DBEntityVarStats();
		varStats.setStats(input.getStats());
		varStats.setTimes(input.getTimes());
		return varStats;

	}

}
