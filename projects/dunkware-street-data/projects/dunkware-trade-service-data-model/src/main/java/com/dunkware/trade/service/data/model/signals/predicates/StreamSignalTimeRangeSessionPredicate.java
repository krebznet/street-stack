package com.dunkware.trade.service.data.model.signals.predicates;

import java.time.LocalDate;
import java.util.function.Predicate;

import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;

public class StreamSignalTimeRangeSessionPredicate implements Predicate<StreamSignalBean> {
	
	private LocalDate date;
	
	public StreamSignalTimeRangeSessionPredicate(LocalDate date) { 
		this.date = date;
	}

	@Override
	public boolean test(StreamSignalBean t) {
		if(t.getDateTime().toLocalDate().equals(date)) { 
			return true;
		}
		return false;
	}
	
	

}
