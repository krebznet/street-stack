package com.dunkware.trade.service.data.model.signals.predicates;

import java.util.List;
import java.util.function.Predicate;

import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;

public class StreamSignalQueryPredicate implements Predicate<StreamSignalBean> {

	
	private List<Predicate<StreamSignalBean>> predicates;

	public StreamSignalQueryPredicate(List<Predicate<StreamSignalBean>> predicates) {
		this.predicates = predicates;
	}

	@Override
	public boolean test(StreamSignalBean t) {
		if (predicates.size() == 0) {
			return true;
		}
		for (Predicate<StreamSignalBean> predicate : predicates) {
			if (!predicate.test(t)) {
				return false;
			}
		}
		return true;

	}

}
