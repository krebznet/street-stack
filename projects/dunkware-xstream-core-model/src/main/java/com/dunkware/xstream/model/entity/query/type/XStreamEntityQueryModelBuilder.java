package com.dunkware.xstream.model.entity.query.type;

import com.dunkware.common.util.json.DJson;

public class XStreamEntityQueryModelBuilder {

	public static void main(String[] args) {
		XStreamEntityQueryType model = XStreamEntityQueryModelBuilder.query(XStreamEntityQueryModelBuilder
				.valueCritiera(XStreamOperator.GreaterThan, 2, XStreamEntityQueryModelBuilder.currentVarValue("Last")));
		try {
			System.out.println(DJson.serializePretty(model));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	
	
	public static XStreamEntityQueryType currentHistoricalCompare(String var, int days, String entity, boolean greater, Number operand) { 
		XStreamEntityQueryType model = new XStreamEntityQueryType();
		XStreamEntityCriteriaType crit = new XStreamEntityCriteriaType();
		crit.setType(XStreamEntityCriteriaKind.ValueCompare);
		if(greater)
			crit.setOperator(XStreamOperator.GreaterThan);
		crit.setOperator(XStreamOperator.LessThan);
		crit.setOperatorValue(operand);
		crit.setValue1(currentVarValue(var));
		crit.setValue2(varAggHistoryHigh(var, days));
		model.getCriterias().add(crit);
		return model;
	}
	
	
	public static XStreamEntityValueType varAggHistoryHigh(String var, int days) { 
		XStreamEntityValueType model = new XStreamEntityValueType();
		model.setType(XStreamEntityValueKind.VarHistoricalAgg);
		model.setVarIdent(var);
		XStreamHistoryTimeRange t = new XStreamHistoryTimeRange();
		t.setType(XStreamHIstoryTimeRangeType.RELATIVE);
		t.setRelativeTimeUnit(XStreamTimeUnit.Days);
		t.setRealtiveTimeRange(days);
		model.setHistoricalTimeRange(t);
		model.setHistoricalAgg(XStreamEntityVarAggHistType.HIGH);
		return model;
	}
	
	

	public static XStreamEntityQueryType query(XStreamEntityCriteriaType... models) {
		XStreamEntityQueryType m = new XStreamEntityQueryType();
		for (XStreamEntityCriteriaType xStreamCriteriaModel : models) {
			m.getCriterias().add(xStreamCriteriaModel);
		}
		return m;
	}

	public static XStreamEntityCriteriaType valueCritiera(XStreamOperator operator, Number operand,
			XStreamEntityValueType value) {
		XStreamEntityCriteriaType criteria = new XStreamEntityCriteriaType();
		criteria.setOperator(operator);
		criteria.setOperatorValue(operand);
		criteria.setValue1(value);
		;
		criteria.setType(XStreamEntityCriteriaKind.Value);
		;
		return criteria;

	}

	public static XStreamEntityValueType currentVarValue(String var) {
		XStreamEntityValueType model = new XStreamEntityValueType();
		model.setType(XStreamEntityValueKind.VarCurrentValue);
		model.setVarIdent(var);
		
		return model;
	}

}
