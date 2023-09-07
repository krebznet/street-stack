package com.dunkware.xstream.core.signal.search;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.dunkware.xstream.api.XStreamSignal;
import com.dunkware.xstream.api.XStreamSignalSearch;

public class XStreamSignalSearchImpl implements XStreamSignalSearch {

	private List<Predicate<XStreamSignal>> predicates = new ArrayList<Predicate<XStreamSignal>>();
	
	public void addPredicate(Predicate<XStreamSignal> pred) { 
		this.predicates.add(pred);
	}
	
	@Override
	public boolean test(XStreamSignal t) {
		for (Predicate<XStreamSignal> predicate : predicates) {
			if(!predicate.test(t)) { 
				return false; 
			}
		}
		return true; 
	}

}
