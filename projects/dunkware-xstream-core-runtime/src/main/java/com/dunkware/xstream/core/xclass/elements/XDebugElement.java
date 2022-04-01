/**
 * 
 */
package com.dunkware.xstream.core.xclass.elements;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.core.annotations.AXObjectElement;
import com.dunkware.xstream.core.xclass.impl.XObjectElementImpl;
import com.dunkware.xstream.xScript.XClassElementType;
import com.dunkware.xstream.xScript.XDebugType;
import com.dunkware.xstream.xScript.XExpressionType;

/**
 * @author Duncan Krebs
 * @date Oct 30, 2015
 * @category M10-Comcast
 */
@AXObjectElement(type = XDebugType.class)
public class XDebugElement extends XObjectElementImpl {

	XDebugType _type;
	private List<XObjectExpression> _expressions = new ArrayList<XObjectExpression>();
	StringBuilder _builder = new StringBuilder();
	
	@Override
	public void setType(XClassElementType type) {
		_type = (XDebugType)type;
		
	}

	@Override
	public void init()  {
		for (XExpressionType type : _type.getArgs()) {
			XObjectExpression exp = getXObject().getContext().createXExpression(type);
			_expressions.add(exp);
		}
		
	}


	@Override
	public void dispose()  {
		for (XObjectExpression xObjectExpression : _expressions) {
			xObjectExpression.dispose();
		}
	}

	
	@Override
	public void execute()  {
		_builder.setLength(0);
		for (XObjectExpression xObjectExpression : _expressions) {
			if(xObjectExpression.canExecute()) {
				_builder.append(xObjectExpression.execute());
				_builder.append(" ");
			}
		}
		System.err.println(_builder.toString());
	}

	

	

	
}
