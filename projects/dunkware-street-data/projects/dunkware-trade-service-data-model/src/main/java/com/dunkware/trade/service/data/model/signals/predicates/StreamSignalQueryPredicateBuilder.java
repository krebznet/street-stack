package com.dunkware.trade.service.data.model.signals.predicates;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalQuery;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalTypeStatsQuery;

public class StreamSignalQueryPredicateBuilder {
	
	public static StreamSignalQueryPredicate build(StreamSignalTypeStatsQuery query, LocalDate sessionDate) throws Exception {
		List<Predicate<StreamSignalBean>> preds = new ArrayList<Predicate<StreamSignalBean>>();
		if (query.isTimeRangeSession()) {
			StreamSignalTimeRangeSessionPredicate pre = new StreamSignalTimeRangeSessionPredicate(sessionDate);
			preds.add(pre);
		}
		else { 
			if (query.getTimeRangeStart() != null) {
				StreamSignalTimeRangeStartPredicate pred = new StreamSignalTimeRangeStartPredicate(
						query.getTimeRangeStart());
				preds.add(pred);
			}
			if (query.getTimeRangeEnd() != null) {
				StreamSignalTimeRangeEndPredicate pred = new StreamSignalTimeRangeEndPredicate(query.getTimeRangeEnd());
				preds.add(pred);
			}	
		}
		if (query.getEntities() != null && query.getEntities().size() > 0) {
			StreamSignalEntityPredicate pre = new StreamSignalEntityPredicate(query.getEntities());
			preds.add(pre);
		}
		if (query.getSignalTypes() != null && query.getSignalTypes().size() > 0) {
			StreamSignalTypePredicate pred = new StreamSignalTypePredicate(query.getSignalTypes());
			preds.add(pred);
		}
		
		StreamSignalQueryPredicate pred = new StreamSignalQueryPredicate(preds);
		return pred;

	}

	public static StreamSignalQueryPredicate build(StreamSignalQuery query) throws Exception {
		List<Predicate<StreamSignalBean>> preds = new ArrayList<Predicate<StreamSignalBean>>();
		if (query.getEntities() != null && query.getEntities().size() > 0) {
			StreamSignalEntityPredicate pre = new StreamSignalEntityPredicate(query.getEntities());
			preds.add(pre);
		}
		if (query.getSignalTypes() != null && query.getSignalTypes().size() > 0) {
			StreamSignalTypePredicate pred = new StreamSignalTypePredicate(query.getSignalTypes());
			preds.add(pred);
		}
		if (query.getTimeRangeStart() != null) {
			StreamSignalTimeRangeStartPredicate pred = new StreamSignalTimeRangeStartPredicate(
					query.getTimeRangeStart());
			preds.add(pred);
		}
		if (query.getTimeRangeEnd() != null) {
			StreamSignalTimeRangeEndPredicate pred = new StreamSignalTimeRangeEndPredicate(query.getTimeRangeEnd());
			preds.add(pred);
		}
		StreamSignalQueryPredicate pred = new StreamSignalQueryPredicate(preds);
		return pred;
	}


}
