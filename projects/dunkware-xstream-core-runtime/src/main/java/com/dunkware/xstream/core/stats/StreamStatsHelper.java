package com.dunkware.xstream.core.stats;

import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.xScript.DataType;
import com.dunkware.xstream.xScript.VarType;

public class StreamStatsHelper {

	public static boolean isNumeric(XStreamEntityVar var) {
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
	
	
	
	

}
