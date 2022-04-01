/**
 * 
 */
package com.dunkware.xstream.core.xclass.elements;

import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.api.XObjectVar;
import com.dunkware.xstream.core.annotations.AXObjectElement;
import com.dunkware.xstream.core.xclass.impl.XObjectElementImpl;
import com.dunkware.xstream.core.xclass.impl.XObjectVarNullImpl;
import com.dunkware.xstream.xScript.XVarSetterType;

/**
 * @author Duncan Krebs
 * @date Oct 26, 2015
 * @category M10-Comcast
 */
@AXObjectElement(type = XVarSetterType.class)
public class XVarSetterElement extends XObjectElementImpl {

	XVarSetterType _type;
	
	
	@Override
	public void init()  {
		_type = (XVarSetterType)getType();
	}

	
	@Override
	public void dispose()  {
		
		
	}

	
	@Override
	public void execute()  {
		// get the value; 
		XObjectVar var = getXObject().getStack().getVar(_type.getVar().getName());
		if(_type.getExp() != null) { 
			XObjectExpression expression = getXObject().getContext().createXExpression(_type.getExp());
			if(expression.canExecute()) { 
				Object value = expression.execute();
				if(value == null) { 
					getXObject().getStack().setVarValue(var.getName(), new XObjectVarNullImpl());
				} else { 
					getXObject().getStack().setVarValue(var.getName(), value);		
				}
			} else { 
				// cannot execute what do we do?  -- nada 
			}
			expression.dispose();
		}
	}

	
}
