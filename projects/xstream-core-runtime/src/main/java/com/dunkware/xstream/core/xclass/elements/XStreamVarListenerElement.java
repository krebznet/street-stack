/**
 * 
 */
package com.dunkware.xstream.core.xclass.elements;

import com.dunkware.xstream.core.annotations.AXObjectElement;
import com.dunkware.xstream.core.xclass.impl.XObjectElementImpl;
import com.dunkware.xstream.core.xclass.workers.XVarListenerWorker;
import com.dunkware.xstream.xScript.XStreamVarListenerType;

/**
 * @author Duncan Krebs
 * @date Oct 23, 2015
 * @category M10-Comcast
 */
@AXObjectElement(type = XStreamVarListenerType.class)
public class XStreamVarListenerElement extends XObjectElementImpl {
	
	XStreamVarListenerType _type;

	@Override
	public void init()  {
		_type = (XStreamVarListenerType)getType();
	}

	@Override
	public void dispose()  {
		
		
	}

	@Override
	public void execute()  {
		XVarListenerWorker worker = new XVarListenerWorker(_type);
		getXObject().getContext().startWorker(worker);
		
	}

	
}
