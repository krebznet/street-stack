package com.dunkware.trade.service.data.service.concept;

import java.time.LocalDateTime;
import java.util.List;

import com.dunkware.net.core.runtime.core.helpers.GProtoHelper;
import com.dunkware.net.proto.core.GCalendarRange;
import com.dunkware.net.proto.core.GCalendarRangeType;
import com.dunkware.net.proto.core.GDateRange;
import com.dunkware.net.proto.core.GDurationRange;
import com.dunkware.net.proto.core.GOperator;
import com.dunkware.net.proto.core.GTimeUnit;
import com.dunkware.net.proto.stream.GEntitySignalQuery;
import com.dunkware.net.proto.stream.GEntityVarCriteria;

public class MongoSignalQueryConcept {
	
	
	/**
	 * 	Challenge here is to build a MongoDB Query from the 
	 * GEntitySignalQuery that is is built in our angular UI
	 * @param protoQuery
	 * @return
	 */
	public Object buildQuery(GEntitySignalQuery protoQuery) {
		// streamIdentifier will tell us what collection in mongo to query;;'
		// naming convention for stream signal collections its just stream_{streamIdentifier}_signal
		String streamIdentifier = protoQuery.getStreamId();
		
		
		// build the search where its an OR Condition for list of entities
		// these are include entities 
		// this will be a list of OR entities -
		List<String> entities = protoQuery.getEntitiesList();
		
		// this will be a list of AND variable criterias
		List<GEntityVarCriteria> varCriterias = protoQuery.getVarCriteriasList();
		for (GEntityVarCriteria criteria : varCriterias) {
			double value = criteria.getValue();
			String varName = criteria.getIdentifier();
			if(criteria.getOperator() == GOperator.EQ) {
				// then var name == value
			}
		}
		
		// this will be signal type criteria using OR
		List<String> signalTypes = protoQuery.getSignalTypesList();
		//"where signal name is "GOOG" || "FAZ" etc. 
		// if signalTypes list is empty then no signal criteria needed
		
		
		GCalendarRange range = protoQuery.getSearchRange();
		// this is the hardest one because there can be different types of time ranges specified
		if(range.getType() == GCalendarRangeType.DATE_RANGE) {
			// then we add a simple time range 
			GDateRange dateRange = range.getDateRange();
			LocalDateTime from = GProtoHelper.toLocalDateTime(dateRange.getStartDate());
			LocalDateTime to = GProtoHelper.toLocalDateTime(dateRange.getStopDate());
			// thense use this 
		}
		
		if(range.getType() == GCalendarRangeType.DATE_TIME_RANGE) { 
			LocalDateTime start = GProtoHelper.toLocalDateTime(range.getDateTimeRange().getStart());
			
		}
		if(range.getType() == GCalendarRangeType.TIME_DURATION) { 
			// this one is more tricky 
			GDurationRange duration = range.getDurationRange();
			LocalDateTime stopTime = LocalDateTime.now(); // need to make sure its in time zone of stream
			LocalDateTime startTime = null;
			if(duration.getTimeUnit() == GTimeUnit.DAY) { 
				startTime = stopTime.minusDays(duration.getValue());
			}
			if(duration.getTimeUnit() == GTimeUnit.MINUTE) { 
				startTime = stopTime.minusMinutes(duration.getValue());
			}
			if(duration.getTimeUnit() == GTimeUnit.SECOND) { 
				startTime = stopTime.minusSeconds(duration.getValue());
			}
			if(duration.getTimeUnit() == GTimeUnit.HOUR) { 
				startTime = stopTime.minusHours(duration.getValue());
			}	
			
			// then we include the start/stop timestamps in the query. 
			
			/**
			 * TOtal Mongo Query will be select documents from signal collection for stream 
			 * with a time range determined by code above where signal entities are one of the values
			 * in the entity list and where signal is one of the types in the signal type list and where
			 * variable a > 39 AND variable b > 40 --> that comes from the GVariableCritiera
			 */
			
			// I don't think we can use this using JPA or maybe we can. 
		}
		
		return null;
	}

}
