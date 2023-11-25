package com.dunkware.trade.service.data.model.signals.predicates;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;

public class StreamSignalTimeRangeEndPredicate implements Predicate<StreamSignalBean> {

	private LocalDateTime criteria; 
	
	public StreamSignalTimeRangeEndPredicate(LocalDateTime time) { 
		this.criteria = time; 
	}
	
	
	
	
	@Override
	public boolean test(StreamSignalBean input) {
		if(input.getDateTime().isAfter(criteria)) { 
			return false; 
		}
		return true; 
	}




	

	
}
