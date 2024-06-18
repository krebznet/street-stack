package com.dunkware.xstream.core.search;

import com.dunkware.utils.core.helpers.DunkNumber;
import com.dunkware.xstream.api.XStreamQueryException;
import com.dunkware.xstream.model.entity.query.type.XStreamOperator;

public class XStreamSearchHelper {

	
	public static boolean testCondition(Number value, Number eval, XStreamOperator operator) throws XStreamQueryException {

		int compare = DunkNumber.compare(value, eval);
		
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
