package com.dunkware.stream.data.cassy.helpers;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatKey;
import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatRow;
import com.dunkware.time.data.model.entity.EntityStatTypes;
import com.dunkware.time.data.model.entity.EntityStatsModel;
import com.dunkware.time.data.model.entity.EntityVarStatsModel;

public class SessionEntityStatHelper {
	
	public static List<DBSessionEntityStatRow> toRows(EntityStatsModel model) { 
		List<DBSessionEntityStatRow> rows = new ArrayList<DBSessionEntityStatRow>();
		for (Integer signal : model.getSigcounts().keySet()) {
			DBSessionEntityStatRow row = new DBSessionEntityStatRow();
			row.setElement(signal);
			row.setValue(model.getSigcounts().get(signal));
			row.setKey(new DBSessionEntityStatKey(model.getStream(), model.getDate(), model.getEntity(), EntityStatTypes.SigCount));
			rows.add(row);
		}
		for (Integer var : model.getVarstats().keySet()) {
			EntityVarStatsModel varStats = model.getVarstats().get(var);
			for (Integer stat : varStats.getStats().keySet()) {
				DBSessionEntityStatRow row = new DBSessionEntityStatRow();
				row.setElement(var);
				row.setValue(varStats.getStats().get(stat));
				row.setTime(varStats.getTimes().get(stat));
				row.setKey(new DBSessionEntityStatKey(model.getStream(), model.getDate(), model.getEntity(), stat));
				rows.add(row);
			}
		}

		return rows; 
	}

}
