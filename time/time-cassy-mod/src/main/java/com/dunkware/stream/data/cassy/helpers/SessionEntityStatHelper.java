package com.dunkware.stream.data.cassy.helpers;

public class SessionEntityStatHelper {
	
	/*
	 * public static List<DBSessionEntityStat> toRows(EntityStatsModel model) {
	 * List<DBSessionEntityStat> rows = new ArrayList<DBSessionEntityStat>(); for
	 * (Integer signal : model.getSigcounts().keySet()) { DBSessionEntityStat row =
	 * new DBSessionEntityStat(); row.setElement(signal);
	 * row.setValue(model.getSigcounts().get(signal)); row.setKey(new
	 * DBSessionEntityStatKey(model.getStream(), model.getDate(), model.getEntity(),
	 * EntityStatTypes.SigCount)); rows.add(row); } for (Integer var :
	 * model.getVarstats().keySet()) { EntityVarStatsModel varStats =
	 * model.getVarstats().get(var); for (Integer stat :
	 * varStats.getStats().keySet()) { DBSessionEntityStat row = new
	 * DBSessionEntityStat(); row.setElement(var);
	 * row.setValue(varStats.getStats().get(stat));
	 * row.setTime(varStats.getTimes().get(stat)); row.setKey(new
	 * DBSessionEntityStatKey(model.getStream(), model.getDate(), model.getEntity(),
	 * stat)); rows.add(row); } }
	 * 
	 * return rows; }
	 */

}
