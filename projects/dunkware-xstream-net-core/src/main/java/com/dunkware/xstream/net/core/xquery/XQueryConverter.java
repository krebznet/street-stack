package com.dunkware.xstream.net.core.xquery;

import com.dunkware.xstream.model.search.Condition;
import com.dunkware.xstream.model.search.ConditionNumeric;
import com.dunkware.xstream.model.search.ConditionNumericOperator;
import com.dunkware.xstream.model.search.ConditionType;
import com.dunkware.xstream.model.search.SessionEntityFilter;
import com.dunkware.xstream.model.search.SessionEntityFilterType;
import com.dunkware.xstream.model.search.SessionEntityFilterValue;
import com.dunkware.xstream.model.search.SessionEntitySearch;
import com.dunkware.xstream.model.search.SessionEntitySearchFilter;
import com.dunkware.xstream.model.search.SessionEntityValue;
import com.dunkware.xstream.model.search.SessionEntityValueType;
import com.dunkware.xstream.xScript.XQueryFilterValueType;
import com.dunkware.xstream.xScript.XQueryType;
import com.dunkware.xstream.xScript.XScriptFactory;
import com.dunkware.xstream.xScript.XStreamOperator;
import com.dunkware.xstream.xScript.XTimeRange;
import com.dunkware.xstream.xScript.XValueSessionVarValueType;
import com.dunkware.xstream.xScript.XValueType;

/**
 * Converts Web Search To Xquery
 * 
 * Yeah Bag it -- too hard fuck
 * 
 * @author Duncan Krebs
 *
 */
public class XQueryConverter {

	public static XQueryType toXQuery(SessionEntitySearch model) throws Exception {
		XQueryType query = XScriptFactory.eINSTANCE.createXQueryType();
		query.setName(model.getName());
		if (model.getFilterSearch() == null) {
			throw new Exception("Expecting SessionEntitySearch But Null");
		}
		SessionEntitySearchFilter filterSearch = model.getFilterSearch();
		for (SessionEntityFilter filter : filterSearch.getFilters()) {
			SessionEntityFilterType filterType = filter.getType();
			boolean handledType = false;
			if (filterType == SessionEntityFilterType.Value) {
				handledType = true;
				XQueryFilterValueType queryFilterType = XScriptFactory.eINSTANCE.createXQueryFilterValueType();
				SessionEntityFilterValue filterValue = filter.getValue();
				XStreamOperator operator = toXOperator(filterValue.getCondition());
				queryFilterType.setOperator(operator);
				// filterValue.ge
				// queryFilterType.setva

			}

			if (filterType == SessionEntityFilterType.ValueCompare) {

			}

		}

		return query;
	}

	private static XValueType toXValueType(SessionEntityValue value) throws Exception {

		XValueType type = XScriptFactory.eINSTANCE.createXValueType();
		if (value.getType() == SessionEntityValueType.CurrentValue) {
			XValueSessionVarValueType varValueType = XScriptFactory.eINSTANCE.createXValueSessionVarValueType();
			//varValueType.
		}
		if (value.getType() == SessionEntityValueType.AggSession) {

		}

		if (value.getType() == SessionEntityValueType.AggHistorical) {

		}

		return type;
	}

	private static XTimeRange toXTimeRange(Object model) throws Exception {
		return null;
	}

	private static XStreamOperator toXOperator(Condition condition) throws Exception {
		XStreamOperator operator = null;

			// so the blocker here is i can't convert
			// then you will have to convert it yeah
			// serialize --> 
		if (condition.getType() != ConditionType.Numerical) {
			throw new Exception("Cannot convert Condition is not Numerical");
		}
		ConditionNumeric numeric = condition.getNumeric();
		if (numeric.getOperator() == ConditionNumericOperator.EqualTo) {
			operator = XStreamOperator.EQ;
		}
		if (numeric.getOperator() == ConditionNumericOperator.GreaterThan) {
			operator = XStreamOperator.GT;
		}

		if (numeric.getOperator() == ConditionNumericOperator.LessThan) {
			operator = XStreamOperator.LT;
		}

		if (numeric.getOperator() == ConditionNumericOperator.NotEqualTo) {
			operator = XStreamOperator.NQ;
		}
		if (operator == null) {
			throw new Exception("Operator unhadled  " + numeric.getOperator().toString());
		}
		return operator;
	}

}
