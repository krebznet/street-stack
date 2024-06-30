package com.dunkware.xstream.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.api.XStreamExpression;
import com.dunkware.xstream.api.XStreamExpressionListener;
import com.dunkware.xstream.api.XStreamEntityVar;

public abstract class XStreamExpressionImpl implements XStreamExpression  {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private List<XStreamExpressionListener> listeners = new ArrayList<XStreamExpressionListener>();
	private Semaphore listenerLock = new Semaphore(1);
	
	private Object value = null;
	
	@Override
	public void addExpressionListener(XStreamExpressionListener list) {
		try {
			listenerLock.acquire();
			listeners.add(list);
		} catch (Exception e) {
			logger.error("Exception acquire/add listener lock " + e.toString());
		} finally { 
			listenerLock.release();
		}
	}

	@Override
	public void removeExpressionListener(XStreamExpressionListener list) {
		try {
			listenerLock.acquire();
			listeners.remove(list);
		} catch (Exception e) {
			logger.error("Exception acquire/remove listener lock " + e.toString());
		} finally { 
			listenerLock.release();
		}
	}
	

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void containedVariables(List<XStreamEntityVar> varList) {
		
	}

	@Override
	public void containedExpressions(List<XStreamExpression> expList) {
		
	}
	

	@Override
	public boolean isInput() {
		return false;
	}

	@Override
	public boolean excludeDownStream() {
		return false;
	}
	
	/**
	 * Used for child classes to set value and have base class
	 * notify expression listeners. 
	 * @param value
	 */
	protected void setValue(Object value) { 
		this.value = value;
		try {
			listenerLock.acquire();
			for (XStreamExpressionListener expListener : listeners) {
				expListener.expressionUpdate(this);
			}
		} catch (Exception e) {
			logger.error("setValue() listener exception " + e.toString(),e);
		} finally { 
			listenerLock.release();
		}
	}

}

