package com.dunkware.xstream.core.search.row.criteria;

import java.util.List;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityQueryRun;
import com.dunkware.xstream.core.search.row.XStreamEntityQueryRunImpl;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityCriteriaType;

/**
 * This is run first to validate that all the other predicates can resolve against
 * the entity being tested. does not make sense to go through predicates whe wen know
 * that there is at least one which cannot resolve. 
 * 
 * @author duncankrebs
 *
 */
public class XStreamEntityQueryPredicates implements XStreamEntityPredicate {

	private List<XStreamEntityPredicate> predicates;
	private XStreamEntityQueryRunImpl queryRun = null;
	
	public void init(List<XStreamEntityPredicate> predicates) { 
		this.predicates = predicates;
	}

	@Override
	public boolean test(XStreamEntity t) {
		for (XStreamEntityPredicate xStreamEntityPredicate : predicates) {
			if(!xStreamEntityPredicate.isResolvable(t)) { 
				queryRun.incremenetUnresolvedCount();
				return false;
			}
		}
		queryRun.incrementResolvedCount();
		for (XStreamEntityPredicate xStreamEntityPredicate : predicates) {
			if(!xStreamEntityPredicate.test(t)) { 
				return false;
			}
		}
		return true; 
	}

	@Override
	public XStreamEntityCriteriaType getModel() {
		return null;
	}

	
	@Override
	public boolean isResolvable(XStreamEntity entity) {
		return true;
	}

	@Override
	public void setQueryRun(XStreamEntityQueryRun run) {
		this.queryRun = (XStreamEntityQueryRunImpl)run;
	}
	
	

}
