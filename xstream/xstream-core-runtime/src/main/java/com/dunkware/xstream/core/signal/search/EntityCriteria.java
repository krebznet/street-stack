package com.dunkware.xstream.core.signal.search;

import java.util.function.Predicate;

import com.dunkware.xstream.api.XStreamSignal;


public class EntityCriteria implements Predicate<XStreamSignal>{

	private int[] ids;
	
	public EntityCriteria(int...ids) { 
		this.ids = ids;
	}
	
	@Override
	public boolean test(XStreamSignal t) {
		for (int i : ids) {
			if(t.getEntity().getIdentifier() == i) { 
				return true;
			}
		}
		return false;
	}

	
	

	
}
