package com.dunkware.xstream.model.util;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.model.entity.query.type.StreamEntityCriteriaType;
import com.dunkware.xstream.model.entity.query.type.StreamEntityQueryType;
import com.dunkware.xstream.model.entity.query.type.StreamEntityQueryValueType;
import com.dunkware.xstream.model.entity.query.type.XStreamCriteriaCompareFunc;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityCriteriaKind;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityCriteriaType;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityQueryType;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityValueKind;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityValueType;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityVarAggHistType;
import com.dunkware.xstream.model.entity.query.type.XStreamHIstoryTimeRangeType;
import com.dunkware.xstream.model.entity.query.type.XStreamHistoryTimeRange;
import com.dunkware.xstream.model.entity.query.type.XStreamOperator;
import com.dunkware.xstream.model.entity.query.type.XStreamTimeUnit;
import com.dunkware.xstream.model.signal.type.StreamSignalType;
import com.dunkware.xstream.model.signal.type.XStreamSignalType;

public class XStreamConverter {

	public static XStreamSignalType toXStreamSignalType(StreamSignalType type) throws Exception { 
		XStreamSignalType xType = new XStreamSignalType();
		xType.setEnableEntityLimit(type.isEnableSymbolLimit());
		if(xType.isEnableEntityLimit()) {
			xType.setEntityLimit(type.getSymbolLimit());
		}
		
		xType.setEnableEntityThrottle(type.isEnableSymbolThrottle());
		if(xType.isEnableEntityThrottle()) { 
			xType.setEntityThrottle(type.getSymbolThrottle());
		}
		xType.setId(type.getId());
		xType.setIdentifier(type.getName());
		xType.setRunInterval(2);
		xType.setRunIntervalTimeUnit(XStreamTimeUnit.Seconds);
		XStreamEntityQueryType xQueryType = new XStreamEntityQueryType();
		for (StreamEntityCriteriaType ct : type.getCriterias()) {
			if(ct.getType().equals("Value Compare Filter") == false) {
				if(ct.getValue().size() > 1) 
					ct.getValue().remove(1);
			}
		}
		List<XStreamEntityCriteriaType> xCriterias = toXStreamCriterias(type.getCriterias());
	
		xQueryType.setCriterias(xCriterias);
		xType.setQuery(xQueryType);
		return xType;
	}
	
	
	public static XStreamEntityQueryType toXStreamEntityQueryType(StreamEntityQueryType web) throws Exception  { 
		XStreamEntityQueryType model = new XStreamEntityQueryType();
		for (StreamEntityCriteriaType webCriteria : web.getCriterias()) {
			XStreamEntityCriteriaType criteriaModel = toXStreamCriteria(webCriteria);
			model.getCriterias().add(criteriaModel);
		}
		return model;
	}
	
	private static List<XStreamEntityCriteriaType> toXStreamCriterias(List<StreamEntityCriteriaType> input) throws Exception { 
		List<XStreamEntityCriteriaType> results = new ArrayList<XStreamEntityCriteriaType>();
		for (StreamEntityCriteriaType sc : input) {
			results.add(toXStreamCriteria(sc));
		}
		return results;
	}
	
	private static XStreamEntityCriteriaType toXStreamCriteria(StreamEntityCriteriaType web) throws Exception { 
		XStreamEntityCriteriaType model = new XStreamEntityCriteriaType();
		model.setType(toXStreamCriteriaType(web.getType()));
		if(model.getType() == XStreamEntityCriteriaKind.Value) { 
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
	
	private static XStreamEntityValueType toXStreamValue(StreamEntityQueryValueType web) throws Exception { 
		XStreamEntityValueType value = new XStreamEntityValueType();
		value.setType(toXStreamValueType(web));
		if(value.getType() == XStreamEntityValueKind.VarCurrentValue || value.getType() == XStreamEntityValueKind.VarHistoricalAgg || 
				value.getType() == XStreamEntityValueKind.VarSessionAgg) { 
			value.setVarIdent(web.getIdentifier());
		}
		if(value.getType() == XStreamEntityValueKind.SignalHistoricalCount || value.getType() ==  XStreamEntityValueKind.SignalSessionCount) { 
			value.setSignalIdent(web.getIdentifier());;
		}
		if(value.getType() == XStreamEntityValueKind.VarHistoricalAgg) {
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
	
	private static XStreamHistoryTimeRange toXStreamHistoricalTimeRange(StreamEntityQueryValueType value) throws Exception  { 
		int timeValue = Integer.valueOf(value.getTime());
		XStreamHistoryTimeRange range = new XStreamHistoryTimeRange();
		range.setRealtiveTimeRange(timeValue);
		range.setRelativeTimeUnit(toXStreamTimeUnit(value.getTimeUnit()));
		range.setType(XStreamHIstoryTimeRangeType.RELATIVE);
		return range;
	}
	
	private static XStreamEntityVarAggHistType toXStreamHistoricalAggFunc(StreamEntityQueryValueType value) throws Exception {
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
	
	private static XStreamEntityValueKind toXStreamValueType(StreamEntityQueryValueType web) throws Exception{ 
		String type = web.getValueType();
		if(type.equals(StreamEntityQueryValueType.VALUE_TYPE_VAR_CURRENT_VALUE)) { 
			return XStreamEntityValueKind.VarCurrentValue;
		}
		if(type.equals(StreamEntityQueryValueType.VALUE_TYPE_VAR_AGG_HISTORICAL)) { 
			return XStreamEntityValueKind.VarHistoricalAgg;
		}
		if(type.equals(StreamEntityQueryValueType.VALUE_TYPE_VAR_AGG)) { 
			return XStreamEntityValueKind.VarSessionAgg;
		}
		
		throw new Exception("Add logic for converting value type " + web.getValueType());
	}
	
	private static XStreamEntityCriteriaKind toXStreamCriteriaType(String web) throws Exception { 
		if(web.equals("Value Compare Filter")) {
			return XStreamEntityCriteriaKind.ValueCompare;
		}
		return XStreamEntityCriteriaKind.Value;
	}
}
