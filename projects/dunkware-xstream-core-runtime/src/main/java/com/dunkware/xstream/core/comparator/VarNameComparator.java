package com.dunkware.xstream.core.comparator;

import java.util.Comparator;

import com.dunkware.xstream.api.XStreamVar;

public class VarNameComparator implements Comparator<XStreamVar> {

	@Override
	public int compare(XStreamVar o1, XStreamVar o2) {
		return o1.getVarType().getName().compareTo(o2.getVarType().getName());
	}
	
	

}
