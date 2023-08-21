/**
 * 
 */
package com.dunkware.xstream.core.xclass.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectContext;
import com.dunkware.xstream.api.XObjectElement;
import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.api.XObjectService;
import com.dunkware.xstream.api.XObjectWorker;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.xScript.XClassElementType;
import com.dunkware.xstream.xScript.XClassType;
import com.dunkware.xstream.xScript.XEqualityType;
import com.dunkware.xstream.xScript.XExpressionType;

/**
 * @author Duncan Krebs
 * @date Oct 26, 2015
 * @category M10-Comcast
 */
public class XObjectContextImpl implements XObjectContext {
	
	private XObject _xObject;
	private List<XObjectWorker> _workers = new ArrayList<XObjectWorker>();
	private Map<String,Object> _attributes = new HashMap<String,Object>();
	private XStreamEntity _XStreamRow; 
	private XStream _xStream; 
	private XClassType _xClassType;
	private AtomicBoolean _disposed = new AtomicBoolean(false);
	private XObjectService _xObjectService; 
	
	public XObjectContextImpl(XObjectService xObjectService, XClassType type, XStream xStream, XStreamEntity row) {
		_xObjectService = xObjectService;
		_XStreamRow = row;
		_xClassType = type;
		_xStream = xStream;
	}
	
	@Override
	public XObject getXObject() {
		return _xObject;
	}

	@Override
	public void startWorker(XObjectWorker worker) {
		worker.startWorker(_xObject);
		_workers.add(worker);
		
	}

	@Override
	public Object getAttribute(String attribute) {
		return _attributes.get(attribute);
	}

	@Override
	public void setAttribute(String name, Object value) {
		_attributes.put(name, value);
	}

	
	@Override
	public void startContext() {
		_xObject = new XObjectImpl(this,_xClassType,_xStream,_XStreamRow);
		_xObject.start(this);
	}

	@Override
	public void disposeContext() {
		for (XObjectWorker xObjectWorker : _workers) {
			xObjectWorker.disposeWorker(_xObject);
		}
		_xObject.dispose();
		_disposed.set(true);
		
	}

	@Override
	public XClassType getXClassType() {
		return _xClassType;
	}

	
	@Override
	public void disposeWorker(XObjectWorker worker) {
		worker.disposeWorker(_xObject);
		_workers.remove(worker);
		
	}

	
	@Override
	public XObjectExpression createXExpression(XExpressionType type)
			{
		if (type instanceof XEqualityType) {
			XEqualityType stophere = (XEqualityType) type;
			
		}
		XObjectExpression exp = _xObjectService.createExpression(type);
		exp.init(type, _xObject);
		return exp;
	}

	
	@Override
	public XObjectElement createElement(XClassElementType type)
			{
		return _xObjectService.createElement(type);
	}

	@Override
	public boolean isDisposed() {
		return _disposed.get();
	}

	
}
