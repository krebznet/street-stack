package com.dunkware.stream.data.util.helpers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.stream.data.model.stats.entity.EntityStatModel;
import com.dunkware.stream.data.model.stats.entity.EntityStatTypes;
import com.dunkware.stream.data.model.stats.entity.EntityStatsModel;
import com.dunkware.stream.data.model.stats.entity.EntityVarStatsModel;

public class EntityStatModelHelper {

	public static List<EntityStatModel> collect(EntityStatsModel model) { 
		List<EntityStatModel> results = new ArrayList<EntityStatModel>();
		int entity = model.getEntity();
		LocalDate date = model.getDate();
		// do the signal counts
		for (Integer signalId : model.getSigcounts().keySet()) {
			Integer sigCount = model.getSigcounts().get(signalId);
			EntityStatModel mod = new EntityStatModel();
			mod.setDate(date);
			mod.setElement(signalId);
			mod.setEntity(entity);
			mod.setStat(EntityStatTypes.SigCount);
			mod.setValue(sigCount);
			mod.setStream(model.getStream());
			results.add(mod);
		}
		// do the var stats; 
		for (Integer varId : model.getVarstats().keySet()) {
			EntityVarStatsModel varStatModel = model.getVarstats().get(varId);
			for (Integer stat : varStatModel.getStats().keySet()) {
				EntityStatModel statMod = new EntityStatModel();
				statMod.setDate(date);
				statMod.setStream(model.getStream());
				statMod.setEntity(entity);
				statMod.setStat(stat);
				statMod.setElement(varId);
				statMod.setValue(varStatModel.getStats().get(stat));
				if(varStatModel.getTimes().containsKey(stat)) { 
					statMod.setTime(varStatModel.getTimes().get(stat));
				}
				results.add(statMod);
			}
		}
		
		return results;
	}
}
