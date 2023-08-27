package com.dunkware.xstream.core.search.row.criteria;

import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.core.search.XStreamSearchHelper;
import com.dunkware.xstream.core.search.row.value.XStreamEntityQueryValue;
import com.dunkware.xstream.model.query.XStreamCriteriaModel;
import com.dunkware.xstream.model.query.XStreamOperator;

public class XStreamEntityValuePredicate implements XStreamEntityPredicate {

	private XStreamEntityPredicate predicate;

	private XStreamCriteriaModel model;

	@Override
	public boolean isTestable() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public XStreamCriteriaModel getModel() {
		return model;
	}



	@Override
	public boolean test(XStreamEntity t) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void init(XStreamCriteriaModel model) {
		
		this.model = model;
	}
	
	

	

	
	
}
