package com.dunkware.xstream.core.search.row.criteria;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityQueryRun;
import com.dunkware.xstream.core.search.row.XStreamEntityQueryRunImpl;
import com.dunkware.xstream.core.search.row.value.XStreamEntityQueryValue;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityCriteriaType;
import com.dunkware.xstream.model.entity.query.type.XStreamOperator;

public class XStreamEntityValuePredicate implements XStreamEntityPredicate {

	private XStreamEntityPredicate predicate;
	private XStreamEntityCriteriaType model;
	private XStreamEntityQueryValue value; 
	private XStreamOperator operator; 
	
	private XStreamEntityQueryRunImpl queryRun;
	
	public void init(XStreamEntityCriteriaType model, XStreamEntityQueryValue value) {
		this.model = model;
		this.value = value;
	}

	@Override
	public boolean isRunnable() {
		return true;
	}


	@Override
	public void setQueryRun(XStreamEntityQueryRun run) {
		queryRun = (XStreamEntityQueryRunImpl)run;
	}

	@Override
	public boolean isResolvable(XStreamEntity entity) {
		try {
			if(value.isResolvable(entity)) { 
				return true;
			}	
		} catch (Exception e) {
			queryRun.addException("Value Criteria is resolvable entity " + entity.getId() + " " + e.toString());
			return false;
		}
		
		return false;
	}





	@Override
	public XStreamEntityCriteriaType getModel() {
		return model;
	}



	@Override
	public boolean test(XStreamEntity t) {
		// TODO Auto-generated method stub
		return false;
	}



	
	

	

	
	
}
