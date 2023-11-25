package com.dunkware.trade.service.data.model.signals.predicates;

import java.util.List;
import java.util.function.Predicate;

import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;

public class StreamSignalTypePredicate implements Predicate<StreamSignalBean> {

	private List<Integer> signalIds;
	
	public StreamSignalTypePredicate(List<Integer> signalIds) { 
		this.signalIds = signalIds;
	}
	
	@Override
	public boolean test(StreamSignalBean t) {
		if(signalIds.size() == 0) { 
			return true; 
		}
		if(signalIds.contains(t.getSignalId())) { 
			return true;
		}
		return false; 
	}
}