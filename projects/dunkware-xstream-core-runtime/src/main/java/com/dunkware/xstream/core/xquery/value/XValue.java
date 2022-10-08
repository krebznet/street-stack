package com.dunkware.xstream.core.xquery.value;

import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamRow;

public interface XValue {
	
	/**
	 * Returns  
	 * @param row
	 * @return
	 */
	void validatePossible(XStreamRow row) throws Exception;
	
	boolean isResolvable(XStreamRow row);

	Object validateResolvable(XStreamRow row) throws XStreamException;

}
