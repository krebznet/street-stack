package com.dunkware.xstream.core.search.row;

import java.util.List;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.api.XStreamRowQueryResults;
import com.dunkware.xstream.model.query.XStreamEntityQueryModel;

public class XStreamRowQueryImpl implements XStreamEntityQuery   {

	private XStream stream; 
	private XStreamEntityQueryModel model; 
	private XStreamRowCriterias criterias;
	
	
	@Override
	public void init(XStreamEntityQueryModel model, XStream stream) throws XStreamQueryException {
		this.model = model;
		this.stream = stream; 
		this.criterias = XStreamRowQueryHelper.createCriterias(model, stream);

	}
	

	@Override
	public XStreamRowQueryResults query(List<XStreamEntity> rows)  {
		XStreamRowQueryResultsImpl results = new XStreamRowQueryResultsImpl();
		results.setQueryCount(rows.size());
		DStopWatch timer = DStopWatch.create();
		timer.start();
		for (XStreamEntity xStreamRow : rows) {
			boolean resolved = false; 
			try {
				resolved = criterias.canResolve(xStreamRow);
				if(resolved) { 
					results.incrementResolvedCount();
					if(criterias.test(xStreamRow)) { 
						results.getMatched().add(xStreamRow);
					}
				}
				
			} catch (XStreamQueryException e) {
				results.addException(xStreamRow.getId(), e.toString());
			}
			catch(XStreamResolveException e2) {
				results.incremenetUnresolvedCount();
			}
			
			
		}
		return results;
	}
	
	
	
	
	
	

}
