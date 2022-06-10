package com.dunkware.xstream.net.core.container.search2.filter;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.dunkware.common.util.calc.DCalc;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.model.search.Condition;
import com.dunkware.xstream.net.model.search.ConditionNumeric;
import com.dunkware.xstream.net.model.search.ConditionNumericOperator;
import com.dunkware.xstream.net.model.search.ConditionType;
import com.dunkware.xstream.net.model.search.SessionEntityFIlterValueCompareFunction;
import com.dunkware.xstream.net.model.search.TimeRangeSession;
import com.dunkware.xstream.net.model.search.TimeRangeSessionType;
import com.dunkware.xstream.net.model.search.TimeUnit;

public class EntityFilterHelper {
	
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

	
	public static EntityFilterTimeRange resolveTimeRange(TimeRangeSession session, Container container) { 
		EntityFilterTimeRange range = new EntityFilterTimeRange();
		if(session.getType() == TimeRangeSessionType.Today) { 
			range.setStart(container.getStartTime());
			range.setStop(container.getTime());;
			return range; 
		}
		
		if(session.getType() == TimeRangeSessionType.Relative) { 
			int seconds = getSeconds(session.getRelativeValue(), session.getRelativeUnit());
			range.setStop(container.getTime());
			range.setStart(container.getTime().minusSeconds(seconds));
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
		EntityFilterTimeRange timeRange = resolveTimeRange(session, entity.getContainer());
		return entity.getSignalCount(timeRange.getStart(), timeRange.getStop(), signalIdent);
	}
	

}
