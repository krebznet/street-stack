/**
 * 
 */
package com.dunkware.xstream.core.xclass.workers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectFunction;
import com.dunkware.xstream.api.XObjectWorker;
import com.dunkware.xstream.xScript.XSignalListenerType;

/**
 * @author Duncan Krebs
 * @date Oct 24, 2015
 * @category M10-Comcast
 */
public class XSignalListenerWorker implements XObjectWorker { // implements XStreamRowListner
	
	private Logger _logger = LoggerFactory.getLogger(getClass());
	
	private XSignalListenerType _type;
	private XObject _xObject; 
	private XObjectFunction _xFunction; 
	public XSignalListenerWorker(XSignalListenerType type) {
		_type = type;
	}
	
	@Override
	public void startWorker(XObject object)  {
		_xObject = object;
		_xFunction = object.getFunction(_type.getFunction());
		//_xObject.getRow().addListener(this);
	}

	@Override
	public void disposeWorker(XObject object)  {
		//_xObject.getRow().removeListener(this);
	}

	/*
	 * @Override public void xRowEvent(XRowEvent event) { if(event.getType() ==
	 * XRowEvent.SIGNAL_TRIGGER) {
	 * if(event.getSignal().getType().getName().equals(_type.getSignalType().getName
	 * ())) { try { _xFunction.invoke(null); } catch (Exception e) {
	 * _logger.error("Error invoking function on signal listener " + e.toString());
	 * }
	 * 
	 * } } }
	 */
	
	
	

}
