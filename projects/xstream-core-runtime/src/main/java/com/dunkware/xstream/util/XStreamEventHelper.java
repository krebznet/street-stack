package com.dunkware.xstream.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.core.comparator.VarNameComparator;

public class XStreamEventHelper {
	




	/**
	 * Builds a snapshot string of vars
	 * 
	 * @param row
	 * @return
	 */
	public static String varSnapshotString(XStreamEntity row) {
		List<XStreamEntityVar> varList = new ArrayList<XStreamEntityVar>();
		varList.addAll(row.getVars());
		Collections.sort(varList, new VarNameComparator());
		StringBuilder builder = new StringBuilder();
		int count = 0;
		for (XStreamEntityVar xStreamVar : varList) {
			if (count > 0) {
				builder.append(",");
			}
			builder.append(xStreamVar.getVarType().getName());
			builder.append("=");
			if (xStreamVar.getSize() == 0) {
				builder.append("null");
			} else {
				builder.append(xStreamVar.getValue(0).toString());
			}
			count++;
		}
		return builder.toString();
	}

}
