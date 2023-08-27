package com.dunkware.xstream.api;

import java.util.List;

import com.dunkware.xstream.core.search.row.criteria.XStreamEntityPredicate;

public interface XStreamEntityQuery {
	
	/**
	 * Sets the predicates and the build time and the stream
	 * @param predicates
	 * @param buildTime
	 */
	public void init(List<XStreamEntityPredicate> predicates, XStream stream, double buildTime);
	
	/**
	 * Returns the number of seconds it took to build the query this includes loading all agg data
	 * needed for all entities in the stream 
	 * @return
	 */
	public double getBuildTime();
	
	/**
	 * Executes this query against all entities in the stream
	 * @return
	 */
	public XStreamEntityQueryRun execute();
	
	/**
	 * Returns the query crieria predicates 
	 * @return
	 */
	public List<XStreamEntityPredicate> getPredicates();
	
	/***
	 * Queries a set of entities 
	 * @param rows
	 * @return
	 */
	public XStreamEntityQueryRun execute(List<XStreamEntity> entities);
	
	/**
	 * Returns true if all criteria predicates are runnable, some might not be 
	 * if for example agg data request is not available based on time range
	 * @return
	 */
	public boolean isRunnable();
	
}

