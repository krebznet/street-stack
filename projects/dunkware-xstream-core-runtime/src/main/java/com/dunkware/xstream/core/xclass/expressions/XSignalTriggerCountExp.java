/**
 * 
 */
package com.dunkware.xstream.core.xclass.expressions;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.xScript.XExpressionType;
import com.dunkware.xstream.xScript.XSignalTriggerCountType;

/**
 * @author Duncan Krebs
 * @date Nov 24, 2015
 * @category M10-Comcast
 */
public class XSignalTriggerCountExp implements XObjectExpression {

	private XSignalTriggerCountType _type;
	private XObject _object;
	
	@Override
	public void init(XExpressionType type, XObject object)
			 {
		_type = (XSignalTriggerCountType)type;
		_object = object;
	}

	
	@Override
	public boolean canExecute()  {
		return true;
	}

	
	@Override
	public XExpressionType getType() {
		return _type;
	}

	
	@Override
	public Object execute()  {
		/*
		 * XSignal[] signals = _object.getRow().getSignals(_type.getSignal());
		 * if(signals.length == 0) { return new Integer(0); } if(_type.getLookback() <
		 * 0) { return new Integer(signals.length); } int count = 0; int
		 * currentRuntimeCounter = _object.getRow().getStream().getRuntimeCounter(); for
		 * (XSignal xSignal : signals) { int diff = currentRuntimeCounter -
		 * xSignal.getRuntimeCounter(); if(diff + 1< _type.getLookback()) { count++; } }
		 * return new Integer(count);
		 */
		return 0;
	}

	@Override
	public void dispose() {
		
		
	}
	
	

}
