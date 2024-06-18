/**
 * 
 */
package com.dunkware.xstream.core.xclass.elements;

import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.core.annotations.AXObjectElement;
import com.dunkware.xstream.core.xclass.impl.XObjectElementImpl;
import com.dunkware.xstream.xScript.XSetVarType;

/**
 * @author Duncan Krebs
 * @date Nov 2, 2015
 * @category M10-Comcast
 */
@AXObjectElement(type = XSetVarType.class)
public class XSetVarElement extends XObjectElementImpl {

	private XSetVarType _type;
	private XObjectExpression _valueExpression;
	
	@Override
	public void init()  {
		_type = (XSetVarType)getType();
		_valueExpression = getXObject().getContext().createXExpression(_type.getValue());
		
	}

	
	@Override
	public void dispose()  {
		_valueExpression.dispose();
	
		
	}

	
	@Override
	public void execute()  {
		if(_valueExpression.canExecute() == false) {
			return;
		}
		Object value = _valueExpression.execute();
		
		try {
			getXObject().getRow().getVar(_type.getVar().getName()).setValue(value);
			
		} catch (Exception e) {
			throw new XStreamRuntimeException("Exception setting var " + e.toString());
		}
	
	}
	
	

}
