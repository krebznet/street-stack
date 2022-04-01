/**
 * 
 */
package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.EWrapper;

/**
 * 
 *@author Duncan Krebs
 *@category Jive Software
 *@since Dec 19, 2013
 **/
public class WCAccountSummaryEnd implements EWrapperCall  {
	
	private int reqId; 
	
	public WCAccountSummaryEnd(int reqId) { 
		this.reqId  = reqId; 
	}

	@Override
	public void callMethod(EWrapper wrapper) {
		wrapper.accountSummaryEnd(reqId);
	}
	
	
	
	

}
