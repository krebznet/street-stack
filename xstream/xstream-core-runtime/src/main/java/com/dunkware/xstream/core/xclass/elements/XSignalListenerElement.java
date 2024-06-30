/**
 * 
 */
package com.dunkware.xstream.core.xclass.elements;

import com.dunkware.xstream.core.annotations.AXObjectElement;
import com.dunkware.xstream.core.xclass.impl.XObjectElementImpl;
import com.dunkware.xstream.core.xclass.workers.XSignalListenerWorker;
import com.dunkware.xstream.xScript.XSignalListenerType;

/**
 * @author Duncan Krebs
 * @date Oct 23, 2015
 * @category M10-Comcast
 */
@AXObjectElement(type = XSignalListenerType.class)
public class XSignalListenerElement extends XObjectElementImpl {
	
	XSignalListenerType _type;

	@Override
	public void init()  {
		_type = (XSignalListenerType)getType();
	}

	@Override
	public void dispose()  {
		
		
	}

	@Override
	public void execute()  {
		XSignalListenerWorker worker = new XSignalListenerWorker(_type);
		getXObject().getContext().startWorker(worker);
		
	}

	
}
