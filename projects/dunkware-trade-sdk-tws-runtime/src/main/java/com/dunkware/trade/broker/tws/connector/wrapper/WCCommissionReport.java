/**
 * 
 */
package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.CommissionReport;
import com.ib.client.EWrapper;

/**
 * @author Duncan Krebs
 * @date Aug 21, 2015
 * @category Comcast M9
 */
public class WCCommissionReport implements EWrapperCall  {

	private CommissionReport _report; 
	
	public WCCommissionReport(CommissionReport report) {
		_report = report; 
	}

	@Override
	public void callMethod(EWrapper wrapper) {
		wrapper.commissionReport(_report);
		
	}
	
	
}
