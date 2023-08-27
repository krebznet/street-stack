package com.dunkware.xstream.core.search.row.criteria;

import com.dunkware.common.util.calc.DCalc;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.core.search.XStreamSearchHelper;
import com.dunkware.xstream.core.search.row.value.XStreamEntityQueryValue;
import com.dunkware.xstream.model.query.XStreamCriteriaCompareFunc;
import com.dunkware.xstream.model.query.XStreamOperator;

public class XStreamEntityValueComparePredicate  {

	private XStreamEntityQueryValue value1; 
	private XStreamEntityQueryValue value2; 
	private XStreamCriteriaCompareFunc compareFunc;
	private XStreamOperator operator; 
	private double eval; 
	
	public void init(XStreamEntityQueryValue value1, XStreamEntityQueryValue value2, XStreamCriteriaCompareFunc compareFunc, XStreamOperator operator, Double eval) throws XStreamQueryException { 
		this.value1 = value1; 
		this.value2 = value2; 
		this.compareFunc = compareFunc;
		this.operator = operator;
		this.eval = eval;
	}



	public boolean test(XStreamEntity t) {
		// TODO Auto-generated method stub
		return false;
	}


	private Number doCompare(Number target, Number compare) throws XStreamQueryException {
		
		if(compareFunc == XStreamCriteriaCompareFunc.ROC) { 
			return DCalc.getPercentageChange(target.doubleValue(), compare.doubleValue());
		}
		if(compareFunc == XStreamCriteriaCompareFunc.DIFFERENCE) { 
			return target.doubleValue() - compare.doubleValue();
		}
		if(compareFunc == XStreamCriteriaCompareFunc.SUM) { 
			return target.doubleValue() + compare.doubleValue();
		}
		
		throw new XStreamQueryException("XStream Row Value Compare Function Not Handled " + compareFunc.name());
		
	}

	
	
}
