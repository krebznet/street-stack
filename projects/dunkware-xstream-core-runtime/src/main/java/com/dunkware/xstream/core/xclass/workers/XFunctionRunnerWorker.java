/**
 * 
 */
package com.dunkware.xstream.core.xclass.workers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectFunction;
import com.dunkware.xstream.api.XObjectWorker;
import com.dunkware.xstream.xScript.StreamTimeUnit;
import com.dunkware.xstream.xScript.XFunctionStartType;

/**
 * @author Duncan Krebs
 * @date Oct 24, 2015
 * @category M10-Comcast
 */
public class XFunctionRunnerWorker implements XObjectWorker {
	
	private XObject _xObject;
	private XFunctionStartType _startType; 
	private XObjectFunction _function = null;
	private Runner _runner; 
	
	private Logger _logger = LoggerFactory.getLogger(getClass());
	
	public XFunctionRunnerWorker(XObject xObject, XFunctionStartType startType) {
		_xObject = xObject;
		_startType = startType;
	
	}

	@Override
	public void startWorker(XObject object)  {
		_function = _xObject.getFunction(_startType.getFunction());
		_runner = new Runner();
		int interval = 1; 
		if(_startType.getTime().equals(StreamTimeUnit.SECOND)) { 
			interval = _startType.getInterval();
		}
		if(_startType.getTime().equals(StreamTimeUnit.MINUTE)) { 
			interval = _startType.getInterval() * 60;
		}
		if(_startType.getTime().equals(StreamTimeUnit.HOUR)) {
			interval = (_startType.getInterval() * 60) * 60 ;
		}
		_xObject.getStream().getClock().scheduleRunnable(_runner, interval);
	}

	@Override
	public void disposeWorker(XObject object)  {
		try {
			_xObject.getStream().getClock().unscheduleRunnable(_runner);
					
		} catch (Exception e) {
			_logger.error("Eroror unscheduling XFunctionRunnerWorker " + e.toString());
		}
	
	}

	
	private class Runner implements Runnable {

		@Override
		public void run() {
				try {
					_function.invoke(null);
				} catch (Exception e) {
					_logger.error(_xObject.getXClassType().getName() + " " + _startType.getFunction().getName()+ " threw exception in XFunctionRunner runnable " + e.toString());
				}
		} 
		
		
	}
	
	

}
