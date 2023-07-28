package com.dunkware.xstream.core.search.row;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.core.search.row.criteria.XStreamRowCriteria;
import com.dunkware.xstream.core.search.row.criteria.XStreamRowValueCompareCriteria;
import com.dunkware.xstream.core.search.row.criteria.XStreamRowValueCriteria;
import com.dunkware.xstream.core.search.row.values.XStreamRowVarCurrentValue;
import com.dunkware.xstream.model.query.XStreamCriteriaModel;
import com.dunkware.xstream.model.query.XStreamCriteriaType;
import com.dunkware.xstream.model.query.XStreamQueryModel;
import com.dunkware.xstream.model.query.XStreamRowValueModel;
import com.dunkware.xstream.model.query.XStreamValueType;

public class XStreamRowQueryHelper {

	
	public static XStreamRowCriterias createCriterias(XStreamQueryModel model) throws XStreamQueryException { 
		List<XStreamRowCriteria> criterias = new ArrayList<XStreamRowCriteria>();
		for (XStreamCriteriaModel criteriaModel : model.getCriterias()) {
			if(criteriaModel.getType() == XStreamCriteriaType.Value) {
				XStreamRowValueCriteria crit = new XStreamRowValueCriteria();
				XStreamRowValue value = createValue(criteriaModel.getValue1());
				crit.init(value, criteriaModel.getOperator(), criteriaModel.getOperatorValue().doubleValue());
				criterias.add(crit);
				continue;
			}
			if(criteriaModel.getType() == XStreamCriteriaType.ValueCompare) {
				XStreamRowValue value1 = createValue(criteriaModel.getValue1());
				XStreamRowValue value2 = createValue(criteriaModel.getValue2());
				XStreamRowValueCompareCriteria crit = new XStreamRowValueCompareCriteria();
				crit.init(value1, value2,criteriaModel.getCompareFunc(), criteriaModel.getOperator(), criteriaModel.getOperatorValue().doubleValue());
				criterias.add(crit);
				continue;
			}
			throw new XStreamQueryException("Building criterias criteria type " + criteriaModel.getType().name() + " not handled?");
		}
		return new XStreamRowCriterias(criterias);
	}
	
	private static XStreamRowCriteria createCriteria() throws XStreamQueryException { 
		return null;
	}
	
	public static XStreamRowValue createValue(XStreamRowValueModel model) throws XStreamQueryException  { 
		if(model.getType() == XStreamValueType.VarCurrentValue) {
			XStreamRowVarCurrentValue value = new XStreamRowVarCurrentValue();
			value.init(model);
		}
		throw new XStreamQueryException("XStreamRowValueFactory not hadnling value type " + model.getType().name());
	}
}
