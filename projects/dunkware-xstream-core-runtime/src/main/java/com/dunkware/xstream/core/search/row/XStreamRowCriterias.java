package com.dunkware.xstream.core.search.row;

import java.util.List;

import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.core.search.row.criteria.XStreamRowCriteria;

public class XStreamRowCriterias {

	private List<XStreamRowCriteria> criterias;
	
	public XStreamRowCriterias(List<XStreamRowCriteria> criterias) { 
		this.criterias = criterias;
	}
	
	
	public boolean canResolve(XStreamRow row) throws XStreamQueryException, XStreamResolveException { 
		for (XStreamRowCriteria xStreamRowCriteria : criterias) {
			if(!xStreamRowCriteria.canResolve(row)) { 
				return false;
			}
		}
		return true;
	}
	
	
	public boolean test(XStreamRow t) throws XStreamQueryException, XStreamResolveException {
		for (XStreamRowCriteria xStreamRowCriteria : criterias) {
			if(!xStreamRowCriteria.canResolve(t)) { 
				return false;
			}	
		}
		// now we assume they can all resolve 
		for (XStreamRowCriteria xStreamRowCriteria : criterias) {
			if(!xStreamRowCriteria.test(t)) { 
				return false; 
			}
			
		}
		return true;
	}

	
}
