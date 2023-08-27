package com.dunkware.xstream.core.search.row.criteria;

import java.util.function.Predicate;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.model.query.XStreamCriteriaModel;

public interface XStreamEntityPredicate extends Predicate<XStreamEntity> {
	
	boolean isTestable();
	
	public XStreamCriteriaModel getModel();
	
	public void init(XStreamCriteriaModel model);

}
