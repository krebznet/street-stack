package com.dunkware.common.util.dtime;

import java.util.Comparator;

public class DDateComparator implements Comparator<DDate> {
	
	public static DDateComparator INSTANCE = new DDateComparator();

	@Override
	public int compare(DDate o1, DDate o2) {
		if(o1.get().isBefore(o2.get())) {
			return -1;
		}
		if(o1.get().isAfter(o2.get())) { 
			return 1;
		}
		return 0;
	}

}
