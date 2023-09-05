package com.dunkware.xstream.core.search.row.criteria;

import java.util.function.Predicate;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityQueryRun;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityCriteriaType;

public interface XStreamEntityPredicate extends Predicate<XStreamEntity> {
	
	boolean isRunnable();
	
	boolean isResolvable(XStreamEntity entity);
	
	public XStreamEntityCriteriaType getModel();
	
	public void setQueryRun(XStreamEntityQueryRun run);

}
