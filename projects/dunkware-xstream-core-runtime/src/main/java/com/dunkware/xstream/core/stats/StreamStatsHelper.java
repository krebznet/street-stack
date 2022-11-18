package com.dunkware.xstream.core.stats;

import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.model.stats.EntityVarStats;
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

	public static void updateVarStats(EntityVarStats stats, XStreamVar var, Object value) {
	}

}
