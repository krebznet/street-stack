package com.dunkware.xstream.core.util;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.api.XDataType;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.model.util.Condition;
import com.dunkware.xstream.model.util.ConditionNumeric;
import com.dunkware.xstream.model.util.ConditionNumericOperator;
import com.dunkware.xstream.model.util.ConditionType;
import com.dunkware.xstream.model.util.Time;
import com.dunkware.xstream.model.util.TimeUnit;
import com.dunkware.xstream.xScript.DataType;

public class XStreamModelHelper {

	private static Logger logger = LoggerFactory.getLogger(XStreamModelHelper.class);

	/**
	 * Resolve a Time Range
	 * 
	 * @param value
	 * @param unit
	 * @return
	 */
	public static int getTimeRangeSeconds(int value, TimeUnit unit) {
		if (unit == TimeUnit.Seconds) {
			return value;
		}
		if (unit == TimeUnit.Minutes) {
			return value * 60;
		}
		if (unit == TimeUnit.Hours) {
			return 3600 * value;
		}
		if (unit == TimeUnit.Days) {
			return 86400 * value;
		}
		// log me maybe?

		return value;
	}

	public static LocalTime toLocalTime(Time time) {
		return LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());
	}

	public static XDataType getXDataType(XStreamVar var) {
		DataType dt = var.getVarType().getType();
		if (dt == DataType.BO_OL) {
			return XDataType.BOOL;
		}
		if (dt == DataType.DUB) {
			return XDataType.DOUBLE;
		}
		if (dt == DataType.LONG) {
			return XDataType.LONG;
		}
		if (dt == DataType.STR) {
			return XDataType.STRING;
		}
		if (dt == DataType.INT) {
			return XDataType.INTEGER;
		}
		if (dt == DataType.DATE) {
			return XDataType.DATE;
		}
		logger.error("Converting XScript DataType to enum error for " + dt.getName());
		return XDataType.INTEGER;

	}

	public static boolean testCondition(Object value, Condition condition) throws XStreamRuntimeException {
		if (condition.getType() != ConditionType.Numerical) {
			throw new XStreamRuntimeException("Test Condition Is Unknown Type " + condition.getType());
		}
		Number valueNumber = null;
		if (value instanceof Number) {
			valueNumber = (Number) value;
		} else {
			throw new XStreamRuntimeException(
					"Cannot evaualte test condition value is not instance of number " + value.getClass().getName());
		}

		double valueDouble = valueNumber.doubleValue();
		double conditionDouble = condition.getNumeric().getValue().doubleValue();

		ConditionNumeric numericCondition = condition.getNumeric();
		if (numericCondition.getOperator() == ConditionNumericOperator.EqualTo) {
			if (valueDouble == conditionDouble) {
				return true;
			}
			return false;
		}
		if (numericCondition.getOperator() == ConditionNumericOperator.GreaterThan) {
			if (valueDouble > conditionDouble) {
				return true;
			}
			return false;
		}
		if (numericCondition.getOperator() == ConditionNumericOperator.LessThan) {
			if (valueDouble < conditionDouble) {
				return true;
			}
			return false;
		}

		if (numericCondition.getOperator() == ConditionNumericOperator.NotEqualTo) {
			if (valueDouble != conditionDouble) {
				return true;
			}
			return false;
		}
		throw new XStreamRuntimeException("Exception at end of testCondition should have returned case "
				+ numericCondition.getOperator().name() + " not handled");
	}

}
