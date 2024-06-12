package com.dunkware.xstream.core.search.row.criteria;

import com.dunkware.utils.core.helpers.DunkMath;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityQueryRun;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.core.search.row.XStreamEntityQueryRunImpl;
import com.dunkware.xstream.core.search.row.value.XStreamEntityQueryValue;
import com.dunkware.xstream.model.entity.query.type.XStreamCriteriaCompareFunc;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityCriteriaType;
import com.dunkware.xstream.model.entity.query.type.XStreamOperator;

public class XStreamEntityValueComparePredicate implements XStreamEntityPredicate  {

	private XStreamEntityQueryValue value1; 
	private XStreamEntityQueryValue value2; 
	private XStreamCriteriaCompareFunc compareFunc;
	private XStreamOperator operator; 
	private Number eval; 
	
	private XStreamEntityQueryRunImpl queryRun; 
	
	public void init(XStreamEntityQueryValue value1, XStreamEntityQueryValue value2, XStreamEntityCriteriaType model) throws XStreamQueryException { 
		this.value1 = value1; 
		this.value2 = value2; 
		this.compareFunc = model.getCompareFunc();
		this.operator = model.getOperator();
		this.eval = model.getOperatorValue();
	}

	public boolean test(XStreamEntity t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	@Override
	public boolean isResolvable(XStreamEntity entity) {
		try {
			if(value1.isResolvable(entity) && value2.isResolvable(entity)) { 
				return true; 
			}	
		} catch (Exception e) {
			queryRun.addException("Value Predicate Exception in isResolvable " + e.toString());
			return false; 
		}
		
		return false; 
	}

	@Override
	public XStreamEntityCriteriaType getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQueryRun(XStreamEntityQueryRun run) {
		queryRun = (XStreamEntityQueryRunImpl)run;
	}

	
	
	
}
