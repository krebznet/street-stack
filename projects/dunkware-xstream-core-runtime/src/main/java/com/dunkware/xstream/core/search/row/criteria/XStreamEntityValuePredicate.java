package com.dunkware.xstream.core.search.row.criteria;

import com.dunkware.common.util.helpers.DNumberHelper;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityQueryRun;
import com.dunkware.xstream.core.search.row.XStreamEntityQueryRunImpl;
import com.dunkware.xstream.core.search.row.value.XStreamEntityQueryValue;
import com.dunkware.xstream.model.entity.query.type.XStreamEntityCriteriaType;
import com.dunkware.xstream.model.entity.query.type.XStreamOperator;

public class XStreamEntityValuePredicate implements XStreamEntityPredicate {

	
	private XStreamEntityCriteriaType model;
	private XStreamEntityQueryValue value; 
	private XStreamOperator operator; 
	private Number operand; 
	
	private XStreamEntityQueryRunImpl queryRun;
	
	public void init(XStreamEntityCriteriaType model, XStreamEntityQueryValue value) {
		this.model = model;
		this.value = value;
		this.operator = model.getOperator();
		this.operand = model.getOperatorValue();
	}

	


	@Override
	public void setQueryRun(XStreamEntityQueryRun run) {
		queryRun = (XStreamEntityQueryRunImpl)run;
	}

	@Override
	public boolean isResolvable(XStreamEntity entity) {
		try {
			if(value.isResolvable(entity)) { 
				return true;
			}	
		} catch (Exception e) {
			queryRun.addException("Value Criteria is resolvable entity " + entity.getId() + " " + e.toString());
			return false;
		}
		
		return false;
	}





	@Override
	public XStreamEntityCriteriaType getModel() {
		return model;
	}



	@Override
	public boolean test(XStreamEntity t) {
		try {
			if(!value.isResolvable(t)) {
				queryRun.incrementResolvedCount();
				return false;
			}	
		} catch (Exception e) {
			queryRun.addException("Exception testing value is resolvable " + e.toString());
			return false; 
		}
		try {
			Number resolved = value.resolve(t);
			if(operator == XStreamOperator.GreaterThan) {
				if(DNumberHelper.isFirstGreater(resolved, operand)) { 
					return true; 
				}
				return false;
			}
			if(operator == XStreamOperator.LessThan) {
				if(DNumberHelper.isFirstGreater(operand,resolved)) { 
					return true; 
				}
				return false; 
			}

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			queryRun.addException("Value criteria sitting outside of greaer or less than wtf ");
			return false; 
		} catch (Exception e) {
			queryRun.addException("Exception outer value criteria " + e.toString());
			return false;
		}
		
		
		
	}



	
	

	

	
	
}
