package com.dunkware.xstream.core.comparator;

import java.util.Comparator;

import com.dunkware.xstream.api.XStreamEntityVar;

public class VarNameComparator implements Comparator<XStreamEntityVar> {

	@Override
	public int compare(XStreamEntityVar o1, XStreamEntityVar o2) {
		return o1.getVarType().getName().compareTo(o2.getVarType().getName());
	}
	
	

}
