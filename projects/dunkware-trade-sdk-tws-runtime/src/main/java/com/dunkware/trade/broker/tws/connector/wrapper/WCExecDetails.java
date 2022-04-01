/**
 * 
 */
package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.Contract;
import com.ib.client.EWrapper;
import com.ib.client.Execution;

/**
 * @author Duncan Krebs
 * @date Aug 21, 2015
 * @category Comcast M9
 */
public class WCExecDetails implements EWrapperCall {

	private int reqId;
	private Contract contract;
	private Execution execution;
	
	public WCExecDetails(int reqId, Contract contract, Execution execution) {
		this.reqId = reqId;
		this.contract = contract;
		this.execution = execution;
	}

	@Override
	public void callMethod(EWrapper wrapper) {
		wrapper.execDetails(reqId, contract, execution);
		
	}
	
	
}
