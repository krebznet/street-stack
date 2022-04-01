/**
 * 
 */
package com.dunkware.xstream.core.xclass.workers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectFunction;
import com.dunkware.xstream.api.XObjectWorker;
import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.api.XStreamVarListener;
import com.dunkware.xstream.xScript.XStreamVarListenerType;

/**
 * @author Duncan Krebs
 * @date Oct 24, 2015
 * @category M10-Comcast
 */
public class XVarListenerWorker implements XObjectWorker, XStreamVarListener {
	
	private Logger _logger = LoggerFactory.getLogger(getClass());

	private XStreamVarListenerType _type;
	private XObject _xObject; 
	private XStreamVar _var;
	private XObjectFunction _function; 
	
	public XVarListenerWorker(XStreamVarListenerType type) {
		_type = type;
	}
	
	@Override
	public void startWorker(XObject object)  {
		_xObject = object; 
		_function = _xObject.getFunction(_type.getFunction().getName());
		_var = _xObject.getRow().getVar(_type.getColumn().getName());
		_var.addVarListener(this);
	}

	@Override
	public void disposeWorker(XObject object)  {
		_var.removeVarListener(this);
	}

	@Override
	public void varUpdate(XStreamVar var) {
		try {
			Runnable runner = new Runnable() { 
				
				public void run() { 
					try {
						_function.invoke(null);	
					} catch (Exception e) {
						_logger.error("Exception invoking function in var udpate runnable " + e.toString());
					}
							
				}
			};
			_xObject.getRow().getStream().getExecutor().execute(runner);
			
		} catch (Exception e) {
			_logger.error("Error exeucting function listener " + e.toString() ,e);
		}

	}
	
	
	
	

}
