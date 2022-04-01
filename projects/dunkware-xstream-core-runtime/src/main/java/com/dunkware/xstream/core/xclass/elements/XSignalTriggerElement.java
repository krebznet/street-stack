/**
 * 
 */
package com.dunkware.xstream.core.xclass.elements;

import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.core.annotations.AXObjectElement;
import com.dunkware.xstream.core.xclass.impl.XObjectElementImpl;
import com.dunkware.xstream.xScript.XSignalTriggerType;

/**
 * @author Duncan Krebs
 * @date Oct 23, 2015
 * @category M10-Comcast
 */
@AXObjectElement(type = XSignalTriggerType.class)
public class XSignalTriggerElement extends XObjectElementImpl {

	private XSignalTriggerType _type;
	
	@Override
	public void init()  {
		_type = (XSignalTriggerType)getType();
		
	}

	/* (non-Javadoc)
	 * @see com.kntrade.sdk.xclass.api.runtime.XObjectElement#dispose()
	 */
	@Override
	public void dispose() {
		
		
	}

	
	@Override
	public void execute()  {
		XStreamRow row = getXObject().getRow();
		row.signal(_type.getSignal());
	}

	
}
