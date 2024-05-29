package com.dunkware.stream.data.cassy.helpers;

import java.util.HashMap;
import java.util.Map;

import com.dunkware.stream.data.cassy.entity.stats.EntityStatsRow;
import com.dunkware.stream.data.cassy.entity.stats.EntityStatsKey;
import com.dunkware.stream.data.cassy.entity.stats.EntityVarStats;
import com.dunkware.stream.data.model.stats.entity.EntityStatsModel;
import com.dunkware.stream.data.model.stats.entity.EntityVarStatsModel;

public class EntityStatsRowHelper {
	
	
	public static EntityStatsModel toModel(EntityStatsRow ent) {
		EntityStatsModel model = new EntityStatsModel();
		model.setDate(ent.getKey().getDate());
		model.setEntity(ent.getKey().getEntity());
		
		for (Integer key : ent.getVars().keySet()) {
			EntityVarStatsModel modStats = new EntityVarStatsModel();
			modStats.setVar(key);
			
			EntityVarStats evarStats = ent.getVars().get(key);
			modStats.setStats(evarStats.getStats());
			modStats.setTimes(evarStats.getTimes());
			model.getVarstats().put(key, modStats);
		}
		return model;
	}
	
	public static EntityStatsRow toRow(EntityStatsModel model) {
		EntityStatsKey key = new EntityStatsKey(model.getStream(), model.getEntity(), model.getDate());
		EntityStatsRow entity = new EntityStatsRow();
		entity.setKey(key);
		entity.setSignals(model.getSigcounts());
		entity.setVars(toRowVarStatsMap(model));
		return entity;
		
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
