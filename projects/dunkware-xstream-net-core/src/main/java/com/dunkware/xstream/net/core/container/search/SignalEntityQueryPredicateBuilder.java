package com.dunkware.xstream.net.core.container.search;

public class SignalEntityQueryPredicateBuilder {
	
	/*
	 * public static List<Predicate<ContainerEntitySignal>> build(GEntitySignalQuery
	 * query) { List<Predicate<ContainerEntitySignal>> preds = new
	 * ArrayList<Predicate<ContainerEntitySignal>>(); if(query.getEntitiesCount() >
	 * 0) { preds.add(SignalEntityPredicate.newInstance(query.getEntitiesList())); }
	 * 
	 * if(query.getSignalTypesCount() > 0) {
	 * preds.add(SignalTypePredicate.newInstance(query.getSignalTypesList())); }
	 * if(query.getSearchRange().getType() == GCalendarRangeType.TIME_DURATION) {
	 * GDurationRange range = query.getSearchRange().getDurationRange();
	 * preds.add(SignalRelativeRangePredicate.newInstance(range.getTimeUnit(),
	 * range.getValue())); } if(query.getSearchRange().getType() ==
	 * GCalendarRangeType.TIME_RANGE) { GTime start =
	 * query.getSearchRange().getTimeRange().getStartTime(); GTime stop =
	 * query.getSearchRange().getTimeRange().getStopTime(); LocalDateTime
	 * startDateTime = GProtoHelper.toLocalDateTime(start,
	 * LocalDate.now(ZoneId.systemDefault())); LocalDateTime stopDateTime =
	 * GProtoHelper.toLocalDateTime(stop, LocalDate.now(ZoneId.systemDefault()));
	 * preds.add(SignalTimeRangePredicate.newInstance(startDateTime, stopDateTime));
	 * } if(query.getSearchRange().getType() == GCalendarRangeType.DATE_RANGE) {
	 * GDate start = query.getSearchRange().getDateRange().getStartDate(); GDate
	 * stop = query.getSearchRange().getDateRange().getStopDate(); LocalDateTime
	 * startDateTIme = GProtoHelper.toLocalDateTime(start); LocalDateTime
	 * stopDateTime = GProtoHelper.toLocalDateTime(stop);
	 * preds.add(SignalTimeRangePredicate.newInstance(startDateTIme, stopDateTime));
	 * } if(query.getSearchRange().getType() == GCalendarRangeType.DATE_TIME_RANGE)
	 * { GDateTime start = query.getSearchRange().getDateTimeRange().getStart();
	 * GDateTime stop = query.getSearchRange().getDateTimeRange().getStop();
	 * LocalDateTime startDateTime = GProtoHelper.toLocalDateTime(start);
	 * LocalDateTime stopDateTime = GProtoHelper.toLocalDateTime(stop);
	 * preds.add(SignalTimeRangePredicate.newInstance(startDateTime, stopDateTime));
	 * 
	 * } if(query.getVarCriteriasCount() > 0) { for (GEntityVarCriteria crit :
	 * query.getVarCriteriasList()) {
	 * preds.add(SignalVariablePredicate.newInstance(crit.getIdentifier(),
	 * crit.getOperator(), crit.getValue())); } } return preds; }
	 */

}
