package com.dunkware.xstream.core.xclass;

import java.util.List;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectElement;
import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.api.XObjectService;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamListener;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.api.XStreamService;
import com.dunkware.xstream.core.annotations.AXStreamService;
import com.dunkware.xstream.core.xclass.impl.XObjectContextImpl;
import com.dunkware.xstream.core.xclass.impl.XObjectImpl;
import com.dunkware.xstream.xScript.XClassElementType;
import com.dunkware.xstream.xScript.XClassType;
import com.dunkware.xstream.xScript.XExpressionType;

@AXStreamService(profiles = "xclass")
public class XObjectServiceImpl implements XStreamService, XStreamListener, XObjectService  {

	private XStream stream; 
	private List<XClassType> classTypes;
	
	@Override
	public void init(XStream stream) throws XStreamException {
		this.stream = stream;
	}

	@Override
	public void preStart() throws XStreamException {
		
		
	}

	@Override
	public void start() throws XStreamException {
		stream.addStreamListener(this);
		classTypes = stream.getInput().getScript().getClasses();
	}

	@Override
	public void preDispose() {
		stream.removeStreamListener(this);
	}

	@Override
	public void dispose() {
		
		
	}

	@Override
	public void rowInsert(XStreamRow row) {
		for (XClassType xclass : classTypes) {
			XObject object = createXObject(xclass, row);
		
		}
	}
	

	@Override
	public List<XClassType> getClassTypes() {
		return classTypes;
	}

	@Override
	public XClassType getClassType(String name) {
		for (XClassType xClassType : classTypes) {
			if(xClassType.getName().equals(name)) { 
				return xClassType;
			}
		}
		throw new XStreamRuntimeException("XClass Type " + name + " not found");
	}

	@Override
	public XObject createXObject(XClassType clazz, XStreamRow row) {
		XObjectContextImpl context = new XObjectContextImpl(this, clazz, stream, row);
		context.startContext();
		return context.getXObject();
		
	}

	@Override
	public XObjectExpression createExpression(XExpressionType type) {
		return stream.getInput().getRegistry().createObjectExpression(type);
	}

	@Override
	public XObjectElement createElement(XClassElementType type) {
		return stream.getInput().getRegistry().createObjectElement(type);
	}

	
	
	
	
	

	
	
	
}
