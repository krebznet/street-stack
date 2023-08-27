package com.dunkware.xstream.core.search.row.criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.dunkware.xstream.api.XStreamEntity;

public class XStreamEntityPredicates implements Predicate<XStreamEntityPredicate> {
	
	private List<XStreamEntityPredicate> predicates = new ArrayList<XStreamEntityPredicate>();
	
	public static XStreamEntityPredicates newInstance(List<XStreamEntityPredicate> predicates) { 
		return null;
	}
	
	private XStreamEntityPredicates(List<XStreamEntityPredicate> predicates) { 
		this.predicates = predicates;
	}
	
	public boolean isTestable(XStreamEntity entity) { 
		return false;
	}

	@Override
	public boolean test(XStreamEntityPredicate t) {
		// TODO Auto-generated method stub
		return false;
	}

}
