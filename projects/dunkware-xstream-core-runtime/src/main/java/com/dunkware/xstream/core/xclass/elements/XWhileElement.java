/**
 * 
 */
package com.dunkware.xstream.core.xclass.elements;

import com.dunkware.xstream.api.XObjectElement;
import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.core.annotations.AXObjectElement;
import com.dunkware.xstream.core.xclass.impl.XObjectElementImpl;
import com.dunkware.xstream.xScript.XClassElementType;
import com.dunkware.xstream.xScript.XWhileType;

/**
 * @author Duncan Krebs
 * @date Nov 21, 2015
 * @category M10-Comcast
 */
@AXObjectElement(type = XWhileType.class)
public class XWhileElement extends XObjectElementImpl {

	XWhileType _type;
	XObjectExpression _whileExpression; 
	
	@Override
	public void init()  {
		_type = (XWhileType)getType();
		_whileExpression = getXObject().getContext().createXExpression(_type.getExpression());
		// create children elements; 
		// create children 
		for (XClassElementType type : _type.getElements()) {
					XObjectElement child = getXObject().getContext().createElement(type);
					child.setParent(this);
					child.setType(type);
					child.setXObject(getXObject());
					child.init();
					getChildren().add(child);
				}

		
	}

	
	@Override
	public void dispose()  {
		for (XObjectElement type : getChildren()) {
			type.dispose();
		}
		
	}

	/* (non-Javadoc)
	 * @see com.kntrade.sdk.xclass.api.runtime.XObjectElement#execute()
	 */
	@Override
	public void execute()  {
		if(_whileExpression.canExecute() == false) { 
			return;
		}
		Object retValue = _whileExpression.execute();
		boolean loop = false;
		if (retValue instanceof Boolean) {
			Boolean boolValue = (Boolean) retValue;
			loop = boolValue;
		} else { 
			return;
		}
		while(loop) { 
			for (XObjectElement element : getChildren()) {
				element.execute();
			}
			// reevaulate expression 
			retValue = _whileExpression.execute();
			if (retValue instanceof Boolean) {
				Boolean boolValue = (Boolean) retValue;
				if(boolValue) {
					continue;
				} else { 
					break;
				}
			}
		}
		
	}

	
}
