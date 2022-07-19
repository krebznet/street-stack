package com.dunkware.xstream.net.core.container.search.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.dunkware.common.util.calc.DCalc;
import com.dunkware.xstream.model.search.Condition;
import com.dunkware.xstream.model.search.ConditionNumeric;
import com.dunkware.xstream.model.search.ConditionNumericOperator;
import com.dunkware.xstream.model.search.ConditionType;
import com.dunkware.xstream.model.search.SessionEntityFIlterValueCompareFunction;
import com.dunkware.xstream.model.search.SessionEntityFilter;
import com.dunkware.xstream.model.search.SessionEntityFilterType;
import com.dunkware.xstream.model.search.SessionEntitySearch;
import com.dunkware.xstream.model.search.SessionEntitySearchFilter;
import com.dunkware.xstream.model.search.SessionEntitySearchType;
import com.dunkware.xstream.model.search.SessionEntityValue;
import com.dunkware.xstream.model.search.SessionEntityValueType;
import com.dunkware.xstream.model.search.Time;
import com.dunkware.xstream.model.search.TimeRangeSession;
import com.dunkware.xstream.model.search.TimeRangeSessionType;
import com.dunkware.xstream.model.search.TimeUnit;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.search.value.EntityValue;
import com.dunkware.xstream.net.core.container.search.value.EntityValueAggHistorical;
import com.dunkware.xstream.net.core.container.search.value.EntityValueAggSession;
import com.dunkware.xstream.net.core.container.search.value.EntityValueCurrentVar;
import com.dunkware.xstream.net.core.container.search.value.EntityValueSignalCountHistorical;
import com.dunkware.xstream.net.core.container.search.value.EntityValueSignalCountSession;
import com.dunkware.xstream.net.core.container.support.DateTimeRange;

public class EntitySearchHelper {
	
	public static boolean testValueCompare(Object targetValue, Object compareValue, SessionEntityFIlterValueCompareFunction function, Condition condition) throws ContainerSearchException { 
		Number targetNumber = null; 
		Number compareNumber = null;
		if (targetValue instanceof Number) {
			targetNumber = (Number) targetValue;
		} else { 
			throw new ContainerSearchException("Test Value Compare target value is not Number but " + targetValue.getClass().getName());
		}
		if (compareNumber instanceof Number ) {
			compareNumber = (Number)compareNumber;
		} else { 
			throw new ContainerSearchException("Compare Value is not numeric but " + compareValue.getClass().getName() + " in testValueCompare");
		}
		double compareDouble = compareNumber.doubleValue();
		double targetDouble = targetNumber.doubleValue();
		
		double computedDouble = Double.MIN_VALUE;
		if(function == SessionEntityFIlterValueCompareFunction.PercentChange) { 
			computedDouble = DCalc.getPercentageChange(targetDouble, compareDouble);
		}
		if(function == SessionEntityFIlterValueCompareFunction.Difference) { 
			computedDouble = targetDouble - compareDouble;
		}
		if(function == SessionEntityFIlterValueCompareFunction.Sum) { 
			compareDouble = compareDouble + targetDouble;
		}
		if(function == SessionEntityFIlterValueCompareFunction.Range) { 
			computedDouble = Math.abs(targetDouble - compareDouble);
		}
		if(condition.getType() != ConditionType.Numerical) { 
			throw new ContainerSearchException("test value compare condition is not numerical can't evaluate");
		}
		ConditionNumeric numericCondition = condition.getNumeric();
		double conditionDouble = numericCondition.getValue().doubleValue();
		
		if (numericCondition.getOperator() == ConditionNumericOperator.EqualTo) {
			if(computedDouble == conditionDouble) { 
				return true; 
			}
			return false; 
		}
		if (numericCondition.getOperator() == ConditionNumericOperator.GreaterThan) {
			if(computedDouble > conditionDouble) { 
				return true; 
			}
			return false; 
		}
		if (numericCondition.getOperator() == ConditionNumericOperator.LessThan) {
			if(computedDouble < conditionDouble) { 
				return true; 
			}
			return false; 
		}	

		if (numericCondition.getOperator() == ConditionNumericOperator.NotEqualTo) {
			if(computedDouble != conditionDouble) { 
				return true; 
			}
			return false; 
		}
		throw new ContainerSearchException("Exception handling value compare test condition in end of method no return ");
		
	}

	public static boolean testCondition(Object value, Condition condition) throws ContainerSearchException {
		if (condition.getType() != ConditionType.Numerical) {
			throw new ContainerSearchException("Test Condition Is Unknown Type " + condition.getType());
		}
		Number valueNumber = null;
		if (value instanceof Number) {
			valueNumber = (Number) value;
		} else {
			throw new ContainerSearchException(
					"Cannot evaualte test condition value is not instance of number " + value.getClass().getName());
		}

		double valueDouble = valueNumber.doubleValue();
		double conditionDouble = condition.getNumeric().getValue().doubleValue();

		ConditionNumeric numericCondition = condition.getNumeric();
		if (numericCondition.getOperator() == ConditionNumericOperator.EqualTo) {
			if(valueDouble == conditionDouble) { 
				return true; 
			}
			return false; 
		}
		if (numericCondition.getOperator() == ConditionNumericOperator.GreaterThan) {
			if(valueDouble > conditionDouble) { 
				return true; 
			}
			return false; 
		}
		if (numericCondition.getOperator() == ConditionNumericOperator.LessThan) {
			if(valueDouble < conditionDouble) { 
				return true; 
			}
			return false; 
		}	

		if (numericCondition.getOperator() == ConditionNumericOperator.NotEqualTo) {
			if(valueDouble != conditionDouble) { 
				return true; 
			}
			return false; 
		}
		throw new ContainerSearchException("Exception at end of testCondition should have returned case " + numericCondition.getOperator().name() + " not handled");
	}

	
	public static DateTimeRange resolveTimeRange(TimeRangeSession session, Container container) { 
		DateTimeRange range = new DateTimeRange();
		if(session.getType() == TimeRangeSessionType.Today) { 
			range.setStart(container.getStartTime());
			range.setStop(container.getDateTime());;
			return range; 
		}
		
		if(session.getType() == TimeRangeSessionType.Relative) { 
			int seconds = getSeconds(session.getRelativeValue(), session.getRelativeUnit());
			range.setStop(container.getDateTime());
			range.setStart(container.getDateTime().minusSeconds(seconds));
			return range; 
		}
		
		// else we are assuming Absolute 
		LocalDateTime start = container.getStartTime();
		
		LocalTime startTime = LocalTime.of(session.getAbsoluteStart().getHour(), session.getAbsoluteStart().getMinute(), session.getAbsoluteStart().getSecond());
		LocalDateTime startDateTime = LocalDateTime.of(start.toLocalDate(), startTime);
		LocalTime stopTime = LocalTime.of(session.getAbsoluteEnd().getHour(), session.getAbsoluteEnd().getMinute(),session.getAbsoluteEnd().getSecond());
		LocalDateTime stopDateTime = LocalDateTime.of(start.toLocalDate(),stopTime);
		range.setStart(startDateTime);
		range.setStop(stopDateTime);
		return range; 
		
	}
	
	public static int getSeconds(int value, TimeUnit protoTimeUnit) {
		if(protoTimeUnit == TimeUnit.Seconds) { 
			return value; 
		}
		if(protoTimeUnit == TimeUnit.Minutes) { 
			return value * 60;
		}
		if(protoTimeUnit == TimeUnit.Hours) { 
			return value * 3600;
		}
		if(protoTimeUnit == TimeUnit.Days) {
			return value * 86400;
		}
		return value;
	}
	
	public static int getSignalCount(TimeRangeSession session, ContainerEntity entity, String signalIdent) { 
		DateTimeRange timeRange = resolveTimeRange(session, entity.getContainer());
		return entity.getSignalCount(timeRange.getStart(), timeRange.getStop(), signalIdent);
	}
	
	/**
	 * Checks if there is enough data set for the session time range 
	 * @param entity
	 * @param range
	 * @return
	 */
	public static boolean canFillTimeRangeSession(ContainerEntity entity, TimeRangeSession range) throws ContainerSearchException {
		if(range.getType() == TimeRangeSessionType.Today) { 
			return true; 
		}
		LocalDateTime now = entity.getContainer().getDateTime();
		LocalDateTime sessionStart = entity.getContainer().getStartTime();
		if(range.getType() == TimeRangeSessionType.Relative) { 
			int seconds = getTimeRangeSeconds(range.getRelativeValue(), range.getRelativeUnit());
			if(now.minusSeconds(seconds).isAfter(sessionStart))	{ 
				return true; 
			} else { 
				return false; 
			}
			
		}
		
		if(range.getType() == TimeRangeSessionType.Absolute) { 
			LocalTime startTime = toLocalTime(range.getAbsoluteStart());
			LocalTime endTime = toLocalTime(range.getAbsoluteEnd());
			Time absoluteStart = range.getAbsoluteStart();
			Time absoluteStop = range.getAbsoluteEnd();
			LocalTime  absoluteStartLocalTime = LocalTime.of(absoluteStart.getHour(), absoluteStart.getMinute(), absoluteStart.getSecond());
			LocalTime  absoluteStopLocalTime = LocalTime.of(absoluteStop.getHour(), absoluteStop.getMinute(),absoluteStop.getSecond());
			
			if(startTime.isAfter(absoluteStartLocalTime) && endTime.isBefore(absoluteStopLocalTime)) {
				return true;
			} else { 
				return false;
			}
		}
		
		
		throw new ContainerSearchException("Unhandled code in canFillTimeRangeSession " + range.getType());
	}
	
	public static List<Object> getTimeRangeSessionVars(ContainerEntity entity, TimeRangeSession session, String var) throws ContainerSearchException { 
		// this could be optimized; 
		return null;  // isinstance ContainerValue implements comparable 
	}
	
	
	public static LocalTime toLocalTime(Time time) { 
		return LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());
		
	}
	
	public static int getTimeRangeSeconds(int value, TimeUnit unit) { 
		if(unit == TimeUnit.Seconds) { 
			return value; 
		}
		if(unit == TimeUnit.Minutes) { 
			return value * 60; 
		}
		if(unit == TimeUnit.Hours) { 
			return 3600 * value;
		}
		if(unit == TimeUnit.Days) { 
			return 86400 * value;
		}
		// log me maybe? 
		return value;
	}
	
	public static List<Predicate<ContainerEntity>> createEntitySearchPredicates(SessionEntitySearch search, Container container) throws ContainerSearchException { 
		if(search.getType() == SessionEntitySearchType.Filter) { 
			return createEntityFilterSearchPredicates(search.getFilterSearch(), container);
		}
		throw new ContainerSearchException("Session Entity Search Type " + search.getType().name() + " Not supported");
	}
	
	public static List<Predicate<ContainerEntity>> createEntityFilterSearchPredicates(SessionEntitySearchFilter search, Container container) throws ContainerSearchException { 
		List<Predicate<ContainerEntity>> predicates = new ArrayList<Predicate<ContainerEntity>>();

		for (SessionEntityFilter filter : search.getFilters()) {
			
			
			if(filter.getType() == SessionEntityFilterType.Value) { 
				EntitySearchValuePredicate valuePred = new EntitySearchValuePredicate();
				valuePred.init(filter.getValue(), container);
				predicates.add(valuePred);
			}
			
			if(filter.getType() == SessionEntityFilterType.ValueCompare) { 
				EntitySearchValeuComparePredicate pred = new EntitySearchValeuComparePredicate();
				pred.init(filter.getValueCompare(), container);
			}
		}
		
		return predicates;
	}
	
	
	public static EntityValue createEntityValue(SessionEntityValue value, Container container) throws ContainerSearchException { 
		if(value.getType() == SessionEntityValueType.CurrentValue) { 
			EntityValueCurrentVar currentVar = new EntityValueCurrentVar();
			currentVar.init(value, container);
			return currentVar;
		}
		if(value.getType() == SessionEntityValueType.AggSession) { 
			EntityValueAggSession aggSession = new EntityValueAggSession();
			aggSession.init(value, container);
			return aggSession;
		}
		if(value.getType() == SessionEntityValueType.AggHistorical) { 
			EntityValueAggHistorical aggHistorical = new EntityValueAggHistorical(); 
			aggHistorical.init(value, container);
			return aggHistorical;
		}
		if(value.getType() == SessionEntityValueType.SignalCountSession) { 
			EntityValueSignalCountSession session = new EntityValueSignalCountSession();
			session.init(value, container);
			return session;
		}
		if(value.getType() == SessionEntityValueType.SignalCountHistorical) { 
			EntityValueSignalCountHistorical historical = new EntityValueSignalCountHistorical();
			historical.init(value, container);
			return historical;
		}
		throw new ContainerSearchException("Container Entity Value Not Handled for Type " + value.getType());
		

	}



}
