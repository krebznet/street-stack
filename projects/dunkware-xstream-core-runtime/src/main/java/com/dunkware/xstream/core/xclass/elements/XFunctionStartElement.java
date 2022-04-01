/**
 * 
 */
package com.dunkware.xstream.core.xclass.elements;

import com.dunkware.xstream.core.annotations.AXObjectElement;
import com.dunkware.xstream.core.xclass.impl.XObjectElementImpl;
import com.dunkware.xstream.core.xclass.workers.XFunctionRunnerWorker;
import com.dunkware.xstream.xScript.XFunctionStartType;

/**
 * @author Duncan Krebs
 * @date Oct 23, 2015
 * @category M10-Comcast
 */
@AXObjectElement(type = XFunctionStartType.class)
public class XFunctionStartElement extends XObjectElementImpl {

	private XFunctionStartType _type;
	
	@Override
	public void init()  {
		_type = (XFunctionStartType)getType();

	}

	@Override
	public void dispose() {
		
		
	}

	
	@Override
	public void execute()  {
		XFunctionRunnerWorker worker = new XFunctionRunnerWorker(getXObject(), _type);
		getXObject().getContext().startWorker(worker);
	}

	
}
