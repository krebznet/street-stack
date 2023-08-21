package com.dunkware.xstream.core.search.row.criteria;

import com.dunkware.common.util.calc.DCalc;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.core.search.XStreamSearchHelper;
import com.dunkware.xstream.core.search.row.XStreamRowValue;
import com.dunkware.xstream.model.query.XStreamCriteriaCompareFunc;
import com.dunkware.xstream.model.query.XStreamOperator;

public class XStreamRowValueCompareCriteria implements XStreamRowCriteria {

	private XStreamRowValue value1; 
	private XStreamRowValue value2; 
	private XStreamCriteriaCompareFunc compareFunc;
	private XStreamOperator operator; 
	private double eval; 
	
	public void init(XStreamRowValue value1, XStreamRowValue value2, XStreamCriteriaCompareFunc compareFunc, XStreamOperator operator, Double eval) throws XStreamQueryException { 
		this.value1 = value1; 
		this.value2 = value2; 
		this.compareFunc = compareFunc;
		this.operator = operator;
		this.eval = eval;
	}

	@Override
	public boolean canResolve(XStreamEntity row) throws XStreamQueryException, XStreamResolveException {
		if(value1.canResolve(row) && value2.canResolve(row)) { 
			return true; 
		}
		return false; 
	}

	@Override
	public boolean test(XStreamEntity row) throws XStreamResolveException, XStreamQueryException {
		Number rowValue1 = value1.resolve(row);
		Number rowValue2 = value2.resolve(row);
		Number computed = doCompare(rowValue1, rowValue2);
		return XStreamSearchHelper.testCondition(computed, eval, operator);
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
