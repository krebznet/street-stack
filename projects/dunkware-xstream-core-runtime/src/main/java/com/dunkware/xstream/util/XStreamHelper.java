package com.dunkware.xstream.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.core.comparator.VarNameComparator;

public class XStreamHelper {

	public static String varSnapshotString(XStreamRow row) {
		List<XStreamVar> varList = new ArrayList<XStreamVar>();
		varList.addAll(row.getVars());
		Collections.sort(varList, new VarNameComparator());
		StringBuilder builder = new StringBuilder();
		int count = 0;
		for (XStreamVar xStreamVar : varList) {
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
