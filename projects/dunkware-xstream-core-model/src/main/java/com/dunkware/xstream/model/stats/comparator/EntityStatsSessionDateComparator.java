package com.dunkware.xstream.model.stats.comparator;

import java.util.Comparator;

import com.dunkware.xstream.model.stats.session.EntitySessionStats;

public class EntityStatsSessionDateComparator implements Comparator<EntitySessionStats> {

	private static EntityStatsSessionDateComparator instance = null;
	
	public static EntityStatsSessionDateComparator recentFirst() { 
		if(instance == null) { 
			instance = new EntityStatsSessionDateComparator();
		}
		return instance; 
	}
	
	@Override
	public int compare(EntitySessionStats o1, EntitySessionStats o2) {
		if(o1.getDate().isEqual(o2.getDate())) { 
			return 0;
		}
		if(o1.getDate().isAfter(o2.getDate())) { 
			return -1;
		}
		return 1;
	}

}
