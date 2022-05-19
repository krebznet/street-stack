package com.dunkware.xstream.net.core.container.search;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.dunkware.net.core.runtime.core.helpers.GProtoHelper;
import com.dunkware.net.proto.core.GCalendarRangeType;
import com.dunkware.net.proto.core.GDate;
import com.dunkware.net.proto.core.GDateTime;
import com.dunkware.net.proto.core.GDurationRange;
import com.dunkware.net.proto.core.GTime;
import com.dunkware.net.proto.stream.GEntitySignalQuery;
import com.dunkware.net.proto.stream.GEntityVarCriteria;
import com.dunkware.xstream.net.core.container.ContainerEntitySignal;
import com.dunkware.xstream.net.core.container.search.signal.SignalEntityPredicate;
import com.dunkware.xstream.net.core.container.search.signal.SignalRelativeRangePredicate;
import com.dunkware.xstream.net.core.container.search.signal.SignalTimeRangePredicate;
import com.dunkware.xstream.net.core.container.search.signal.SignalTypePredicate;
import com.dunkware.xstream.net.core.container.search.signal.SignalVariablePredicate;

public class SignalEntityQueryPredicateBuilder {
	
	public static List<Predicate<ContainerEntitySignal>> build(GEntitySignalQuery query) {
		List<Predicate<ContainerEntitySignal>> preds = new ArrayList<Predicate<ContainerEntitySignal>>();
		if(query.getEntitiesCount() > 0) { 
			preds.add(SignalEntityPredicate.newInstance(query.getEntitiesList()));
		}
		
		if(query.getSignalTypesCount() > 0) { 
			preds.add(SignalTypePredicate.newInstance(query.getSignalTypesList()));
		}
		if(query.getSearchRange().getType() == GCalendarRangeType.TIME_DURATION) { 
			GDurationRange range = query.getSearchRange().getDurationRange();
			preds.add(SignalRelativeRangePredicate.newInstance(range.getTimeUnit(), range.getValue()));
		}
		if(query.getSearchRange().getType() == GCalendarRangeType.TIME_RANGE) {
			GTime start = query.getSearchRange().getTimeRange().getStartTime();
			GTime stop = query.getSearchRange().getTimeRange().getStopTime();
			LocalDateTime startDateTime = GProtoHelper.toLocalDateTime(start, LocalDate.now(ZoneId.systemDefault()));
			LocalDateTime stopDateTime = GProtoHelper.toLocalDateTime(stop, LocalDate.now(ZoneId.systemDefault()));
			preds.add(SignalTimeRangePredicate.newInstance(startDateTime, stopDateTime));
		}
		if(query.getSearchRange().getType() == GCalendarRangeType.DATE_RANGE) { 
			GDate start = query.getSearchRange().getDateRange().getStartDate();
			GDate stop = query.getSearchRange().getDateRange().getStopDate();
			LocalDateTime startDateTIme = GProtoHelper.toLocalDateTime(start);
			LocalDateTime stopDateTime = GProtoHelper.toLocalDateTime(stop);
			preds.add(SignalTimeRangePredicate.newInstance(startDateTIme, stopDateTime));
		}
		if(query.getSearchRange().getType() == GCalendarRangeType.DATE_TIME_RANGE) { 
			GDateTime start = query.getSearchRange().getDateTimeRange().getStart();
			GDateTime stop = query.getSearchRange().getDateTimeRange().getStop();
			LocalDateTime startDateTime  = GProtoHelper.toLocalDateTime(start);
			LocalDateTime stopDateTime = GProtoHelper.toLocalDateTime(stop);
			preds.add(SignalTimeRangePredicate.newInstance(startDateTime, stopDateTime));
			
		}
		if(query.getVarCriteriasCount() > 0) { 
			for (GEntityVarCriteria crit : query.getVarCriteriasList()) {
				preds.add(SignalVariablePredicate.newInstance(crit.getIdentifier(), crit.getOperator(), crit.getValue()));
			}
		}
		return preds;
	}

}
