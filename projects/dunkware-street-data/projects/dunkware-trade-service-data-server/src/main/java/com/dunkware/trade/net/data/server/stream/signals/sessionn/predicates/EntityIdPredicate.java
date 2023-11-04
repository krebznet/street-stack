package com.dunkware.trade.net.data.server.stream.signals.sessionn.predicates;

import java.util.List;
import java.util.function.Predicate;

import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;

public class EntityIdPredicate implements Predicate<StreamSignalBean> {

	private List<Integer> entityIds;

	public EntityIdPredicate(List<Integer> entityIds) { 
		this.entityIds = entityIds;
	}

	@Override
	public boolean test(StreamSignalBean t) {
		if (entityIds.size() == 0) {
			return true;
		}
		if (entityIds.contains(t.getEntityId())) {
			return true;
		}
		return false;
	}

}
