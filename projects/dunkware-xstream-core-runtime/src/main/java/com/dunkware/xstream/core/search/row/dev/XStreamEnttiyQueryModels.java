package com.dunkware.xstream.core.search.row.dev;

import com.dunkware.xstream.model.query.XStreamCriteriaModel;
import com.dunkware.xstream.model.query.XStreamEntityCriteriaType;
import com.dunkware.xstream.model.query.XStreamEntityQueryModel;
import com.dunkware.xstream.model.query.XStreamOperator;
import com.dunkware.xstream.model.query.XStreamEntityValueModel;
import com.dunkware.xstream.model.query.XStreamEntityValueType;

public class XStreamEnttiyQueryModels {

	public static XStreamEntityQueryModel test1() { 
		XStreamEntityQueryModel model = new XStreamEntityQueryModel();
		XStreamCriteriaModel crit = new XStreamCriteriaModel();
		crit.setType(XStreamEntityCriteriaType.Value);
		XStreamEntityValueModel value = new XStreamEntityValueModel();
		value.setType(XStreamEntityValueType.VarCurrentValue);
		crit.setValue1(value);
		crit.setOperator(XStreamOperator.GreaterThan);
		crit.setOperatorValue(30);
		model.getCriterias().add(crit);
		return model;
				
	}
}
