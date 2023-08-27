package com.dunkware.xstream.core.search.row;

import java.util.List;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.api.XStreamEntityQueryRun;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.core.search.row.criteria.XStreamEntityPredicate;
import com.dunkware.xstream.model.query.XStreamEntityQueryModel;

public class XStreamEntityQueryImpl implements XStreamEntityQuery   {

	private XStream stream; 
	private List<XStreamEntityPredicate> predicates;
	private double buildTime; 
	
	public XStreamEntityQueryImpl() { 
		
	}
	

	@Override
	public void init(List<XStreamEntityPredicate> predicates, XStream stream, double buildTime) {
		this.predicates = predicates;
		this.buildTime = buildTime;
		this.stream = stream;
	}


	@Override
	public double getBuildTime() {
		return buildTime; 
	}

	@Override
	public XStreamEntityQueryRun execute() {
		XStreamEntityQueryRun run = new XStreamEntityQueryRunImpl();
		DStopWatch timer = DStopWatch.create();
		for (XStreamEntityPredicate pre : predicates) {
			pre.setQueryRun(run);
		}
		//stream.getRows().stream()
		return run;
	}


	@Override
	public XStreamEntityQueryRun execute(List<XStreamEntity> entities) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isRunnable() {
		for (XStreamEntityPredicate predicate : predicates) {
			if(!predicate.isRunnable()) { 
				return false; 
			}
		}
		return true;
	}





	@Override
	public List<XStreamEntityPredicate> getPredicates() {
		return predicates;
	}



	
	
	
	
	

	
	

}
