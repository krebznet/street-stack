package com.dunkware.stream.data.cassy.helpers;

import java.util.HashMap;
import java.util.Map;

import com.dunkware.stream.data.cassy.entity.stats.DBEntityStatsRow;
import com.dunkware.stream.data.cassy.entity.stats.DBEntityStatsKey;
import com.dunkware.stream.data.cassy.entity.stats.DBEntityVarStats;
import com.dunkware.stream.data.model.stats.entity.EntityStatsModel;
import com.dunkware.stream.data.model.stats.entity.EntityVarStatsModel;

public class EntityStatsRowHelper {
	
	
	public static EntityStatsModel toModel(DBEntityStatsRow ent) {
		EntityStatsModel model = new EntityStatsModel();
		model.setDate(ent.getKey().getDate());
		model.setEntity(ent.getKey().getEntity());
		
		for (Integer key : ent.getVars().keySet()) {
			EntityVarStatsModel modStats = new EntityVarStatsModel();
			modStats.setVar(key);
			
			DBEntityVarStats evarStats = ent.getVars().get(key);
			modStats.setStats(evarStats.getStats());
			modStats.setTimes(evarStats.getTimes());
			model.getVarstats().put(key, modStats);
		}
		return model;
	}
	
	public static DBEntityStatsRow toRow(EntityStatsModel model) {
		DBEntityStatsKey key = new DBEntityStatsKey(model.getStream(), model.getEntity(), model.getDate());
		DBEntityStatsRow entity = new DBEntityStatsRow();
		entity.setKey(key);
		entity.setSignals(model.getSigcounts());
		entity.setVars(toRowVarStatsMap(model));
		return entity;
		
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
