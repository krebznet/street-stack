/**
 * 
 */
package com.dunkware.xstream.core.xclass.expressions;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.xScript.XExpressionType;
import com.dunkware.xstream.xScript.XLastSignalTriggerType;

/**
 * @author Duncan Krebs
 * @date Nov 24, 2015
 * @category M10-Comcast
 */
public class XLastSignalTriggerExp implements XObjectExpression {

	private XLastSignalTriggerType _type;
	private XObject _object;
	
	@Override
	public void init(XExpressionType type, XObject object)
			 {
		_type = (XLastSignalTriggerType)type;
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
		 * if(signals.length == 0) { return new Integer(0); } XSignal lastSignal =
		 * signals[0]; int counter = 1; while(counter < signals.length) {
		 * if(signals[counter].getRuntimeCounter() > lastSignal.getRuntimeCounter()) {
		 * lastSignal = signals[counter]; } counter++; } return
		 * lastSignal.getRuntimeCounter();
		 */
		return 0;
	}

	
	@Override
	public void dispose() {
		
		
	}
	
	

}
