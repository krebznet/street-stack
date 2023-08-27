package com.dunkware.trade.service.util;

import com.dunkware.trade.service.stream.json.query.WebStreamCriteria;
import com.dunkware.trade.service.stream.json.query.WebStreamCriteriaValue;
import com.dunkware.trade.service.stream.json.query.WebStreamQuery;
import com.dunkware.xstream.model.query.XStreamCriteriaCompareFunc;
import com.dunkware.xstream.model.query.XStreamCriteriaModel;
import com.dunkware.xstream.model.query.XStreamEntityCriteriaType;
import com.dunkware.xstream.model.query.XStreamHIstoryTimeRangeType;
import com.dunkware.xstream.model.query.XStreamHistoryTimeRange;
import com.dunkware.xstream.model.query.XStreamOperator;
import com.dunkware.xstream.model.query.XStreamEntityQueryModel;
import com.dunkware.xstream.model.query.XStreamTimeUnit;
import com.dunkware.xstream.model.query.XStreamEntityValueModel;
import com.dunkware.xstream.model.query.XStreamEntityValueType;
import com.dunkware.xstream.model.query.XStreamEntityVarAggHistType;

public class XStreamQueryConverter {

	public static XStreamEntityQueryModel toXStreamQuery(WebStreamQuery web) throws Exception  { 
		XStreamEntityQueryModel model = new XStreamEntityQueryModel();
		for (WebStreamCriteria webCriteria : web.getCriterias()) {
			XStreamCriteriaModel criteriaModel = toXStreamCriteria(webCriteria);
			model.getCriterias().add(criteriaModel);
		}
		return model;
	}
	
	private static XStreamCriteriaModel toXStreamCriteria(WebStreamCriteria web) throws Exception { 
		XStreamCriteriaModel model = new XStreamCriteriaModel();
		model.setType(toXStreamCriteriaType(web.getType()));
		if(model.getType() == XStreamEntityCriteriaType.Value) { 
			model.setValue1(toXStreamValue(web.getValue().get(0)));
		}
		else { 
			model.setValue1(toXStreamValue(web.getValue().get(0)));
			model.setValue2(toXStreamValue(web.getValue().get(1)));
			model.setCompareFunc(toXStreamCompareFunc(web.getCompareFunction()));
			
		}
		model.setOperator(toXStreamOperator(web.getCondition()));
		model.setOperatorValue(web.getConditionValue());
		return model;
	}
	
	private static XStreamCriteriaCompareFunc toXStreamCompareFunc(String value) throws Exception { 
		if(value.equals("Percent Change")) { 
			return XStreamCriteriaCompareFunc.ROC;
		}
		if(value.equals("Difference")) { 
			return XStreamCriteriaCompareFunc.DIFFERENCE;
		}
		if(value.equals("Sum")) { 
			return XStreamCriteriaCompareFunc.SUM;
		}
		throw new Exception("Compare function not handled " + value);
	}
	
	private static XStreamEntityValueModel toXStreamValue(WebStreamCriteriaValue web) throws Exception { 
		XStreamEntityValueModel value = new XStreamEntityValueModel();
		value.setType(toXStreamValueType(web));
		if(value.getType() == XStreamEntityValueType.VarCurrentValue || value.getType() == XStreamEntityValueType.VarHistoricalAgg || 
				value.getType() == XStreamEntityValueType.VarSessionAgg) { 
			value.setVarIdent(web.getIdentifier());
		}
		if(value.getType() == XStreamEntityValueType.SignalHistoricalCount || value.getType() ==  XStreamEntityValueType.SignalSessionCount) { 
			value.setSignalIdent(web.getIdentifier());;
		}
		if(value.getType() == XStreamEntityValueType.VarHistoricalAgg) {
			value.setHistoricalTimeRange(toXStreamHistoricalTimeRange(web));
			value.setHistoricalAgg(toXStreamHistoricalAggFunc(web));
		}
		return value;
	}
	
	
	private static XStreamOperator toXStreamOperator(String value) throws Exception { 
		if(value.equals("Greater Than")) { 
			return XStreamOperator.GreaterThan;
		}
		if(value.equals("Less Than")) { 
			return XStreamOperator.LessThan;
		}
		throw new Exception("Operator " + value + " not handled");
	}
	
	private static XStreamHistoryTimeRange toXStreamHistoricalTimeRange(WebStreamCriteriaValue value) throws Exception  { 
		int timeValue = Integer.valueOf(value.getTime());
		XStreamHistoryTimeRange range = new XStreamHistoryTimeRange();
		range.setRealtiveTimeRange(timeValue);
		range.setRelativeTimeUnit(toXStreamTimeUnit(value.getTimeRange()));
		range.setType(XStreamHIstoryTimeRangeType.RELATIVE);
		return range;
	}
	
	private static XStreamEntityVarAggHistType toXStreamHistoricalAggFunc(WebStreamCriteriaValue value) throws Exception {
		if(value.getSessionAggregation().equals("High")) { 
			return XStreamEntityVarAggHistType.HIGH;
		}
		if(value.getSessionAggregation().equals("Low")) { 
			return XStreamEntityVarAggHistType.LOW;
		}
		throw new Exception("Hisotrical Agg Not Handled " + value.getSessionAggregation());
		
	}
	
	private static XStreamTimeUnit toXStreamTimeUnit(String value) throws Exception { 
		if(value.equals("Days")) { 
			return XStreamTimeUnit.Days;
		}
		if(value.equals("Hours")) { 
			return XStreamTimeUnit.Hours;
		}
		if(value.equals("Minutes")) { 
			return XStreamTimeUnit.Minutes;
		}
		if(value.equals("Seconds")) { 
			return XStreamTimeUnit.Seconds;
		}
		throw new Exception("Logic missing for time unit converstion of " + value);
	}
	
	private static XStreamEntityValueType toXStreamValueType(WebStreamCriteriaValue web) throws Exception{ 
		String type = web.getValueType();
		if(type.equals("Field Current Value")) { 
			return XStreamEntityValueType.VarCurrentValue;
			
		}
		throw new Exception("Add logic for converting value type " + web.getValueType());
	}
	
	private static XStreamEntityCriteriaType toXStreamCriteriaType(String web) throws Exception { 
		if(web.equals("Value Compare Filter")) {
			return XStreamEntityCriteriaType.ValueCompare;
		}
		return XStreamEntityCriteriaType.Value;
	}
}
