/**
 * 
 */
package com.dunkware.xstream.core.xclass.elements;

import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.core.annotations.AXObjectElement;
import com.dunkware.xstream.core.xclass.impl.XObjectElementImpl;
import com.dunkware.xstream.core.xclass.impl.XObjectVarImpl;
import com.dunkware.xstream.core.xclass.impl.XObjectVarNullImpl;
import com.dunkware.xstream.xScript.XVarType;

/**
 * @author Duncan Krebs
 * @date Oct 26, 2015
 * @category M10-Comcast
 */
@AXObjectElement(type = XVarType.class)
public class XVarElement extends XObjectElementImpl {

	XVarType _type;
	
	
	@Override
	public void init()  {
		_type = (XVarType)getType();
	}

	
	@Override
	public void dispose()  {
		
		
	}

	
	@Override
	public void execute()  {
		// get the value; 
		
		if(_type.getExp() != null) { 
			XObjectExpression expression = getXObject().getContext().createXExpression(_type.getExp());
			if(expression.canExecute()) { 
				Object value = expression.execute();
				XObjectVarImpl var = new XObjectVarImpl(_type, value);
				getXObject().getStack().setVar(var);
			} else { 
				XObjectVarImpl var = new XObjectVarImpl(_type, new XObjectVarNullImpl());
				getXObject().getStack().setVar(var);
			}
			expression.dispose();
		} else { 
			XObjectVarImpl var = new XObjectVarImpl(_type, new XObjectVarNullImpl());
			getXObject().getStack().setVar(var);
		}
	}

	
}
