package com.dunkware.xstream.model.query;

import com.dunkware.common.util.json.DJson;

public class XStreamEntityQueryModelBuilder {

	public static void main(String[] args) {
		XStreamEntityQueryModel model = XStreamEntityQueryModelBuilder.query(XStreamEntityQueryModelBuilder
				.valueCritiera(XStreamOperator.GreaterThan, 2, XStreamEntityQueryModelBuilder.currentVarValue("Last")));
		try {
			System.out.println(DJson.serializePretty(model));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	
	
	public static XStreamEntityQueryModel currentHistoricalCompare(String var, int days, String entity, boolean greater, Number operand) { 
		XStreamEntityQueryModel model = new XStreamEntityQueryModel();
		XStreamCriteriaModel crit = new XStreamCriteriaModel();
		crit.setType(XStreamEntityCriteriaType.ValueCompare);
		if(greater)
			crit.setOperator(XStreamOperator.GreaterThan);
		crit.setOperator(XStreamOperator.LessThan);
		crit.setOperatorValue(operand);
		crit.setValue1(currentVarValue(var));
		crit.setValue2(varAggHistoryHigh(var, days));
		model.getCriterias().add(crit);
		return model;
	}
	
	
	public static XStreamEntityValueModel varAggHistoryHigh(String var, int days) { 
		XStreamEntityValueModel model = new XStreamEntityValueModel();
		model.setType(XStreamEntityValueType.VarHistoricalAgg);
		model.setVarIdent(var);
		XStreamHistoryTimeRange t = new XStreamHistoryTimeRange();
		t.setType(XStreamHIstoryTimeRangeType.RELATIVE);
		t.setRelativeTimeUnit(XStreamTimeUnit.Days);
		t.setRealtiveTimeRange(days);
		model.setHistoricalTimeRange(t);
		model.setHistoricalAgg(XStreamEntityVarAggHistType.HIGH);
		return model;
	}
	
	

	public static XStreamEntityQueryModel query(XStreamCriteriaModel... models) {
		XStreamEntityQueryModel m = new XStreamEntityQueryModel();
		for (XStreamCriteriaModel xStreamCriteriaModel : models) {
			m.getCriterias().add(xStreamCriteriaModel);
		}
		return m;
	}

	public static XStreamCriteriaModel valueCritiera(XStreamOperator operator, Number operand,
			XStreamEntityValueModel value) {
		XStreamCriteriaModel criteria = new XStreamCriteriaModel();
		criteria.setOperator(operator);
		criteria.setOperatorValue(operand);
		criteria.setValue1(value);
		;
		criteria.setType(XStreamEntityCriteriaType.Value);
		;
		return criteria;

	}

	public static XStreamEntityValueModel currentVarValue(String var) {
		XStreamEntityValueModel model = new XStreamEntityValueModel();
		model.setType(XStreamEntityValueType.VarCurrentValue);
		model.setVarIdent(var);
		
		return model;
	}

}
