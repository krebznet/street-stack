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
	
	public XStreamEntityQueryImpl(List<XStreamEntityPredicate> predicates) { 
		this.predicates = predicates;
	}
	
	
	
	@Override
	public List<XStreamEntityPredicate> getPredicates() {
		return predicates;
	}



	@Override
	public void init(XStreamEntityQueryModel model, XStream stream) throws XStreamQueryException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public XStreamEntityQueryRun run() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public XStreamEntityQueryRun run(List<XStreamEntity> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	

	
	

}
