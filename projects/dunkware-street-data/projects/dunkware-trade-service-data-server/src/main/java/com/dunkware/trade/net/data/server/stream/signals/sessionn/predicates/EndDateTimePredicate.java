package com.dunkware.trade.net.data.server.stream.signals.sessionn.predicates;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.google.common.base.Predicate;

public class EndDateTimePredicate implements Predicate<StreamSignalBean> {

	private LocalDateTime criteria; 
	
	public EndDateTimePredicate(LocalDateTime time) { 
		this.criteria = time; 
	}
	
	@Override
	public boolean apply(StreamSignalBean input) {
		if(input.getDateTime().isAfter(criteria)) { 
			return false; 
		}
		return true; 
		
	}

	
}
