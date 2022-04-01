/**
 * 
 */
package com.dunkware.xstream.core.xclass.elements;

import java.util.List;

import com.dunkware.xstream.api.XObjectElement;
import com.dunkware.xstream.api.XObjectFunction;
import com.dunkware.xstream.core.annotations.AXObjectElement;
import com.dunkware.xstream.core.xclass.impl.XObjectElementImpl;
import com.dunkware.xstream.xScript.XClassElementType;
import com.dunkware.xstream.xScript.XFunctionType;

/**
 * @author Duncan Krebs
 * @date Oct 24, 2015
 * @category M10-Comcast
 */
@AXObjectElement(type  = XFunctionType.class)
public class XFunctionElement extends XObjectElementImpl implements XObjectFunction {

	private XFunctionType _type;
	
	@Override
	public void init()  {
		_type = (XFunctionType)getType();
		XFunctionType myType = (XFunctionType)getType();
		for (XClassElementType element : myType.getElements()) {
			XObjectElement obj = createElement(element);
			obj.setParent(this);
			obj.setType(element);
			obj.setXObject(getXObject());
			obj.init();
			getChildren().add(obj);
		}
	}


	@Override
	public void dispose()  {
		// call dispose on our children. 
		for (XObjectElement child : getChildren()) {
			child.dispose();
		}
	}

	

	@Override
	public XFunctionType getFunctionType() {
		if(_type == null) { 
			_type = (XFunctionType)getType();
		}
		return _type;
	}


	@Override
	public void execute()  {
		// Don't need to do anything
		
	}


	@Override
	public Object invoke(List<Object> args)  {
		getXObject().getStack().pushFrame();
		try {
			
			for (XObjectElement child : getChildren()) {
				child.execute();
			}	
		} catch (Exception e) {
			if (e instanceof XFunctionReturnException) {
				XFunctionReturnException returnExpt = (XFunctionReturnException) e;
				return returnExpt.getReurnValue();
			}
			
		} finally { 
			getXObject().getStack().popFrame();
		}

		return null;
	}
	
	
	
	
	
	
	
	

}
