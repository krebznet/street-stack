package com.dunkware.stream.data.cassy.helpers;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.stream.data.cassy.entity.stats.SessionEntityStatKey;
import com.dunkware.stream.data.cassy.entity.stats.SessionEntityStatRow;
import com.dunkware.stream.data.model.stats.entity.EntityStatTypes;
import com.dunkware.stream.data.model.stats.entity.EntityStatsModel;
import com.dunkware.stream.data.model.stats.entity.EntityVarStatsModel;

public class SessionEntityStatHelper {
	
	public static List<SessionEntityStatRow> toRows(EntityStatsModel model) { 
		List<SessionEntityStatRow> rows = new ArrayList<SessionEntityStatRow>();
		for (Integer signal : model.getSigcounts().keySet()) {
			SessionEntityStatRow row = new SessionEntityStatRow();
			row.setElement(signal);
			row.setValue(model.getSigcounts().get(signal));
			row.setKey(new SessionEntityStatKey(model.getStream(), model.getDate(), model.getEntity(), EntityStatTypes.SigCount));
			rows.add(row);
		}
		for (Integer var : model.getVarstats().keySet()) {
			EntityVarStatsModel varStats = model.getVarstats().get(var);
			for (Integer stat : varStats.getStats().keySet()) {
				SessionEntityStatRow row = new SessionEntityStatRow();
				row.setElement(var);
				row.setValue(varStats.getStats().get(stat));
				row.setTime(varStats.getTimes().get(stat));
				row.setKey(new SessionEntityStatKey(model.getStream(), model.getDate(), model.getEntity(), stat));
				rows.add(row);
			}
		}

		return rows; 
	}

}
