/**
 * 
 */
package com.dunkware.xstream.core.xclass.impl;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectContext;
import com.dunkware.xstream.api.XObjectElement;
import com.dunkware.xstream.api.XObjectFunction;
import com.dunkware.xstream.api.XObjectStack;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.xScript.XClassElementType;
import com.dunkware.xstream.xScript.XClassType;
import com.dunkware.xstream.xScript.XFunctionType;

/**
 * @author Duncan Krebs
 * @date Oct 23, 2015
 * @category M10-Comcast
 */
public class XObjectImpl implements XObject {

	private XStreamRow _row;
	private XClassType _xClassType;
	private XObjectContext _xObjectContext = null;
	private List<XObjectElement> _rootElements = new ArrayList<XObjectElement>();
	private List<XObjectFunction> _functions = new ArrayList<XObjectFunction>();
	private XStream _stream;
	private XObjectStackImpl _stack = new XObjectStackImpl();

	
	public XObjectImpl(XObjectContext context, XClassType xClassType, XStream stream, XStreamRow row)  {
		_row = row;
		_stream = stream;
		_xObjectContext = context;
		_xClassType = xClassType;
		
	}
	
	
	
	@Override
	public void start(XObjectContext context)  {
		_stack.pushFrame();
		for (XClassElementType element : _xClassType.getElements()) {
			try {
				if (element instanceof XClassElementType) {
					XClassElementType typeElement = (XClassElementType) element;
					XObjectElement objElement = _xObjectContext.createElement(typeElement);
					objElement.setType(typeElement);
					objElement.setXObject(this);
					objElement.init();
					if(!XFunctionType.class.isInstance(typeElement)) {
						objElement.execute();
					} else { 
						_functions.add((XObjectFunction)objElement);
					}
					_rootElements.add(objElement);		
				}
				
	
			} catch (Exception e) {
				throw new XStreamRuntimeException("Exception creating xobject " + e.toString());
			}
		}
		// look for init function
		if(functionExists("init")) {
			XObjectFunction function = getFunction("init");
			try {
				function.invoke(null);
			} catch (Exception e) {
				throw new XStreamRuntimeException("Error calling init funciton " + e.toString());
			}
		}
		
	}



	@Override
	public XObjectFunction[] getFunctions() {
		return _functions.toArray(new XObjectFunction[_functions.size()]);
	}
	
	
	@Override
	public boolean functionExists(String functionName) {
		try {
			getFunction(functionName);
			return true; 
		} catch (Exception e) {
			return false;
		}
	}



	@Override
	public XObjectFunction getFunction(String functionName) {
		for (XObjectFunction XObjectFunction : _functions) {
			if(XObjectFunction.getFunctionType().getName().equalsIgnoreCase(functionName)) {
				return XObjectFunction;
			}
		}
		throw new XStreamRuntimeException("Function " + functionName + " not found");
	
	}

	
	@Override
	public XObjectFunction getFunction(XFunctionType type) {
		for (XObjectFunction XObjectFunction : _functions) {
			if(XObjectFunction.getFunctionType().getName().equalsIgnoreCase(type.getName())) {
				return XObjectFunction;
			}
		}
		throw new XStreamRuntimeException("Function " + type.getName() + " not found");
	}

	@Override
	public XObjectContext getContext() {
		return _xObjectContext;
	}


	@Override
	public XStream getStream() {
		return _stream;
	}

	@Override
	public void dispose()  {
		for (XObjectElement element : _rootElements) {
			element.dispose();
		}
		
	}
	

	@Override
	public XClassType getXClassType() {
		return _xClassType;
	}



	@Override
	public XObjectStack getStack() {
		return _stack;
	}

	
	@Override
	public XStreamRow getRow() {
		return _row;
	}





	
}
