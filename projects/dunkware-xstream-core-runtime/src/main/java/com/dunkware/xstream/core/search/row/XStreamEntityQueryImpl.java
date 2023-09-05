package com.dunkware.xstream.core.search.row;

import java.util.List;
import java.util.stream.Collectors;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.api.XStreamEntityQueryRun;
import com.dunkware.xstream.core.search.row.criteria.XStreamEntityPredicate;
import com.dunkware.xstream.core.search.row.criteria.XStreamEntityQueryPredicates;

public class XStreamEntityQueryImpl implements XStreamEntityQuery   {

	private XStream stream; 
	private List<XStreamEntityPredicate> predicates;
	private double buildTime; 
	private XStreamEntityQueryPredicates queryPredicates;
	
	public XStreamEntityQueryImpl() { 
		
	}
	

	@Override
	public void init(List<XStreamEntityPredicate> predicates, XStream stream, double buildTime) {
		queryPredicates = new XStreamEntityQueryPredicates();
		queryPredicates.init(predicates);
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
		return execute(stream.getRows());
	}


	@Override
	public synchronized XStreamEntityQueryRun execute(List<XStreamEntity> entities) {
		XStreamEntityQueryRunImpl run = new XStreamEntityQueryRunImpl();
		DStopWatch timer = DStopWatch.create();
		for (XStreamEntityPredicate pre : predicates) {
			pre.setQueryRun(run);
		}
		queryPredicates.setQueryRun(run);
		timer.start();
		List<XStreamEntity> results = stream.getRows().stream().filter(queryPredicates).collect(Collectors.toList());
		timer.stop();
		run.setTime(timer.getCompletedSeconds());
		run.setResults(results);
		
		return run;
		
	}



	@Override
	public List<XStreamEntityPredicate> getPredicates() {
		return predicates;
	}



	
	
	
	
	

	
	

}
