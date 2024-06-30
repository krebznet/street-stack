package com.dunkware.xstream.core.search.row;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.dunkware.utils.core.stopwatch.StopWatch;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.api.XStreamEntityQueryRun;
import com.dunkware.xstream.core.search.row.criteria.XStreamEntityPredicate;
import com.dunkware.xstream.core.search.row.criteria.XStreamEntityQueryPredicates;

public class XStreamEntityQueryImpl implements XStreamEntityQuery   {

	private XStream stream; 
	private List<XStreamEntityPredicate> predicates;
	private double builLocalTime; 
	private XStreamEntityQueryPredicates queryPredicates;
	
	public XStreamEntityQueryImpl() { 
		
	}
	

	@Override
	public void init(List<XStreamEntityPredicate> predicates, XStream stream, double builLocalTime) {
		queryPredicates = new XStreamEntityQueryPredicates();
		queryPredicates.init(predicates);
		this.predicates = predicates;
		this.builLocalTime = builLocalTime;
		this.stream = stream;
	}

	

	@Override
	public double getBuilLocalTime() {
		return builLocalTime; 
	}

	@Override
	public XStreamEntityQueryRun execute() {
		return execute(stream.getRows());
	}


	@Override
	public synchronized XStreamEntityQueryRun execute(Collection<XStreamEntity> entities) {
		XStreamEntityQueryRunImpl run = new XStreamEntityQueryRunImpl();
		StopWatch timer = StopWatch.newInstance();
		for (XStreamEntityPredicate pre : predicates) {
			pre.setQueryRun(run);
		}
		queryPredicates.setQueryRun(run);
		timer.start();
		List<XStreamEntity> results = stream.getRows().stream().filter(queryPredicates).collect(Collectors.toList());
		timer.stop();
		run.setTime(timer.seconds());
		run.setResults(results);
		
		return run;
		
	}



	@Override
	public List<XStreamEntityPredicate> getPredicates() {
		return predicates;
	}



	
	
	
	
	

	
	

}
