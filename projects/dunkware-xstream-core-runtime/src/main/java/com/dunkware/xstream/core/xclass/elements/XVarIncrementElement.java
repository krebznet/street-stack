/**
 * 
 */
package com.dunkware.xstream.core.xclass.elements;

import com.dunkware.xstream.api.XObjectVar;
import com.dunkware.xstream.api.XObjectVarNull;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.core.annotations.AXObjectElement;
import com.dunkware.xstream.core.xclass.impl.XObjectElementImpl;
import com.dunkware.xstream.xScript.XVarIncrementType;

/**
 * @author Duncan Krebs
 * @date Oct 29, 2015
 * @category M10-Comcast
 */
@AXObjectElement(type =  XVarIncrementType.class)
public class XVarIncrementElement extends XObjectElementImpl {

	private XVarIncrementType _type; 
	
	/* (non-Javadoc)
	 * @see com.kntrade.sdk.xclass.api.runtime.XObjectElement#init()
	 */
	@Override
	public void init()  {
		_type = (XVarIncrementType)getType();
	}

	/* (non-Javadoc)
	 * @see com.kntrade.sdk.xclass.api.runtime.XObjectElement#dispose()
	 */
	@Override
	public void dispose()  {
		
		
	}

	@Override
	public void execute()  {
		try {
			XObjectVar var = getXObject().getStack().getVar(_type.getVar().getName());
			Object value = var.getValue();
			if (value instanceof Integer) {
				Integer intValue = (Integer) value;
				int newValue = intValue + 1;
				getXObject().getStack().setVarValue(var.getName(), newValue);
				return;
			}
			if (value instanceof Double) {
				Double intValue = (Double) value;
				double newValue = intValue + 1;
				getXObject().getStack().setVarValue(var.getName(), newValue);
				return;
				
			}
			if (value instanceof XObjectVarNull) {
				getXObject().getStack().setVarValue(var.getName(), 1);
				return;
			}
			throw new XStreamRuntimeException("Cannot handle var increment value type " + var.getValue().getClass().getName());
			
		} catch (Exception e) {
			throw new XStreamRuntimeException("XVarIncrement element exception " + e.toString());
		}
		
	}
	
	

}
