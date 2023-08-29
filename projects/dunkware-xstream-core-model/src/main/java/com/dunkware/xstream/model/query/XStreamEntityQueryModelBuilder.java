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
