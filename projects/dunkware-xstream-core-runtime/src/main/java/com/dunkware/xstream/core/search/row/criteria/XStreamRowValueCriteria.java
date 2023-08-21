package com.dunkware.xstream.core.search.row.criteria;

import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.core.search.XStreamSearchHelper;
import com.dunkware.xstream.core.search.row.XStreamRowValue;
import com.dunkware.xstream.model.query.XStreamOperator;

public class XStreamRowValueCriteria implements XStreamRowCriteria {

	private XStreamRowValue value;
	private XStreamOperator operator; 
	private double eval;
	
	public void init(XStreamRowValue value, XStreamOperator operator, double eval) throws XStreamQueryException { 
		this.value = value; 
		this.operator = operator;
		this.eval = eval; 
	}

	@Override
	public boolean canResolve(XStreamEntity row) throws XStreamQueryException, XStreamResolveException {
		return value.canResolve(row);
	}

	@Override
	public boolean test(XStreamEntity row) throws XStreamResolveException, XStreamQueryException {
		Number resolved = value.resolve(row);
		return XStreamSearchHelper.testCondition(resolved, eval, operator);
	}
	

	
	
}
