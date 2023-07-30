package com.dunkware.xstream.model.stats.comparator;

import java.util.Comparator;

import com.dunkware.common.util.helpers.DNumberHelper;
import com.dunkware.xstream.model.stats.EntityStatsSessionVar;

public class EntityStatsSessionVarHighComparator implements Comparator<EntityStatsSessionVar> {

	private static EntityStatsSessionVarHighComparator instance = null;
	
	public static EntityStatsSessionVarHighComparator getInstance() { 
		if(instance == null) { 
			instance = new EntityStatsSessionVarHighComparator();
		}
		return instance; 
	}
	
	@Override
	public int compare(EntityStatsSessionVar o1, EntityStatsSessionVar o2) {
		return DNumberHelper.compare(o1.getHigh(), o2.getHigh());
	}

}
