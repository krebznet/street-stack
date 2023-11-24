package com.dunkware.trade.net.data.server.stream.signals.sessionn.predicates;

import java.time.LocalDateTime;

import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.google.common.base.Predicate;

public class StartDateTimePredicate implements Predicate<StreamSignalBean> {

	private LocalDateTime criteria; 
	
	public StartDateTimePredicate(LocalDateTime time) { 
		this.criteria = time; 
	}
	
	@Override
	public boolean apply(StreamSignalBean input) {
		if(input.getDateTime().isAfter(criteria) || input.getDateTime().isEqual(criteria)) { 
			return false; 
		}
		return true; 
		
	}

	
}
