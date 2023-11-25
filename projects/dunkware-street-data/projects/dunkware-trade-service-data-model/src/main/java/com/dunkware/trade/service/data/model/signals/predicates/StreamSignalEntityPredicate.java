package com.dunkware.trade.service.data.model.signals.predicates;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;

public class StreamSignalEntityPredicate implements Predicate<StreamSignalBean> {

	private List<Integer> entityIds;
	private Map<Integer,Integer> keyMap;

	public StreamSignalEntityPredicate(List<Integer> entityIds) { 
		this.entityIds = entityIds;
		keyMap = new HashMap<Integer,Integer>();
		for (Integer integer : entityIds) {
			keyMap.put(integer, integer);
		}
	}

	@Override
	public boolean test(StreamSignalBean t) {
		if (entityIds.size() == 0) {
			return true;
		}
		if (keyMap.containsKey(t.getEntityId())) {
			return true;
		}
		return false;
	}

}
