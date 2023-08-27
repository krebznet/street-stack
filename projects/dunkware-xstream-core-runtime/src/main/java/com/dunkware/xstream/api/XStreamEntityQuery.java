package com.dunkware.xstream.api;

import java.util.List;

import com.dunkware.xstream.core.search.row.criteria.XStreamEntityPredicate;
import com.dunkware.xstream.model.query.XStreamEntityQueryModel;

public interface XStreamEntityQuery {
	
	public void init(XStreamEntityQueryModel model, XStream stream) throws XStreamQueryException;
	
	/**
	 * Queries all entities 
	 * @return
	 */
	public XStreamEntityQueryRun run();
	
	public List<XStreamEntityPredicate> getPredicates();
	
	/***
	 * Queries a set of entities 
	 * @param rows
	 * @return
	 */
	public XStreamEntityQueryRun run(List<XStreamEntity> entities);
	
}

