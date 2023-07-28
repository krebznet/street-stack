package com.dunkware.trade.service.stream.server.util;

import com.dunkware.trade.service.stream.json.query.WebStreamCriteria;
import com.dunkware.trade.service.stream.json.query.WebStreamCriteriaValue;
import com.dunkware.trade.service.stream.json.query.WebStreamQuery;
import com.dunkware.xstream.model.query.XStreamCriteriaCompareFunc;
import com.dunkware.xstream.model.query.XStreamCriteriaModel;
import com.dunkware.xstream.model.query.XStreamCriteriaType;
import com.dunkware.xstream.model.query.XStreamHIstoryTimeRangeType;
import com.dunkware.xstream.model.query.XStreamHistoryTimeRange;
import com.dunkware.xstream.model.query.XStreamOperator;
import com.dunkware.xstream.model.query.XStreamQueryModel;
import com.dunkware.xstream.model.query.XStreamTimeUnit;
import com.dunkware.xstream.model.query.XStreamRowValueModel;
import com.dunkware.xstream.model.query.XStreamValueType;
import com.dunkware.xstream.model.query.XStreamVarHistoricalAggFunc;

public class XStreamQueryConverter {

	public static XStreamQueryModel toXStreamQuery(WebStreamQuery web) throws Exception  { 
		XStreamQueryModel model = new XStreamQueryModel();
		for (WebStreamCriteria webCriteria : web.getCriterias()) {
			XStreamCriteriaModel criteriaModel = toXStreamCriteria(webCriteria);
			model.getCriterias().add(criteriaModel);
		}
		return model;
	}
	
	private static XStreamCriteriaModel toXStreamCriteria(WebStreamCriteria web) throws Exception { 
		XStreamCriteriaModel model = new XStreamCriteriaModel();
		model.setType(toXStreamCriteriaType(web.getType()));
		if(model.getType() == XStreamCriteriaType.Value) { 
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
	
	private static XStreamRowValueModel toXStreamValue(WebStreamCriteriaValue web) throws Exception { 
		XStreamRowValueModel value = new XStreamRowValueModel();
		value.setType(toXStreamValueType(web));
		if(value.getType() == XStreamValueType.VarCurrentValue || value.getType() == XStreamValueType.VarHistoricalAgg || 
				value.getType() == XStreamValueType.VarSessionAgg) { 
			value.setVarIdent(web.getIdentifier());
		}
		if(value.getType() == XStreamValueType.SignalHistoricalCount || value.getType() ==  XStreamValueType.SignalSessionCount) { 
			value.setSignalIdent(web.getIdentifier());;
		}
		if(value.getType() == XStreamValueType.VarHistoricalAgg) {
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
	
	private static XStreamVarHistoricalAggFunc toXStreamHistoricalAggFunc(WebStreamCriteriaValue value) throws Exception {
		if(value.getSessionAggregation().equals("High")) { 
			return XStreamVarHistoricalAggFunc.HIGH;
		}
		if(value.getSessionAggregation().equals("Low")) { 
			return XStreamVarHistoricalAggFunc.LOW;
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
	
	private static XStreamValueType toXStreamValueType(WebStreamCriteriaValue web) throws Exception{ 
		String type = web.getValueType();
		if(type.equals("Field Current Value")) { 
			return XStreamValueType.VarCurrentValue;
			
		}
		throw new Exception("Add logic for converting value type " + web.getValueType());
	}
	
	private static XStreamCriteriaType toXStreamCriteriaType(String web) throws Exception { 
		if(web.equals("Value Compare Filter")) {
			return XStreamCriteriaType.ValueCompare;
		}
		return XStreamCriteriaType.Value;
	}
}
