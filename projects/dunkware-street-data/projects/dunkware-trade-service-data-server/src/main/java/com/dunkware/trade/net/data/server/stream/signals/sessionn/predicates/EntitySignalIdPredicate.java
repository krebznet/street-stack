package com.dunkware.trade.net.data.server.stream.signals.sessionn.predicates;

import java.util.List;
import java.util.function.Predicate;

import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;

public class EntitySignalIdPredicate implements Predicate<StreamSignalBean> {

	private List<Integer> signalIds;
	
	public EntitySignalIdPredicate(List<Integer> signalIds) { 
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
