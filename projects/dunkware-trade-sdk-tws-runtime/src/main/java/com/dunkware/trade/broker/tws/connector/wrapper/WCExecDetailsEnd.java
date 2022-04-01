/**
 * 
 */
package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.EWrapper;

/**
 * @author Duncan Krebs
 * @date Aug 21, 2015
 * @category Comcast M9
 */
public class WCExecDetailsEnd implements EWrapperCall {

	private int reqId;

	public WCExecDetailsEnd(int reqI) {
		
	}

	@Override
	public void callMethod(EWrapper wrapper) {
		wrapper.execDetailsEnd(reqId);
		
	}
	
	
}
