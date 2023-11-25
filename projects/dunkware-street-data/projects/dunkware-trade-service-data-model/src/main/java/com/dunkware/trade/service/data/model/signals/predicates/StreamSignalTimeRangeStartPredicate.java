package com.dunkware.trade.service.data.model.signals.predicates;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;

public class StreamSignalTimeRangeStartPredicate implements Predicate<StreamSignalBean> {

	private LocalDateTime criteria; 
	
	public StreamSignalTimeRangeStartPredicate(LocalDateTime time) { 
		this.criteria = time; 
	}
	
	
	
	@Override
	public boolean test(StreamSignalBean t) {
		if(t.getDateTime().isAfter(criteria) || t.getDateTime().equals(criteria)) { 
			return true; 
		}
		return false;
	}


	
}
