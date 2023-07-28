package com.dunkware.xstream.core.search;

import com.dunkware.common.util.helpers.DNumberHelper;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.model.query.XStreamOperator;

public class XStreamSearchHelper {

	
	public static boolean testCondition(Number value, Number eval, XStreamOperator operator) throws XStreamQueryException {

		int compare = DNumberHelper.compare(value, eval);
		
		if(operator == XStreamOperator.GreaterThan) { 
			if(compare == 1) { 
				return true;
			}
			return false; 
		}
		if(operator == XStreamOperator.LessThan) { 
			if(compare == -1) { 
				return true;
			}
			return false; 
		}
		if(operator == XStreamOperator.Equal) { 
			if(compare == 0) { 
				return true;
			}
			return false; 
		}
		throw new XStreamQueryException("Test Operator not handled " + operator.name());
		
		
		
	}

}
