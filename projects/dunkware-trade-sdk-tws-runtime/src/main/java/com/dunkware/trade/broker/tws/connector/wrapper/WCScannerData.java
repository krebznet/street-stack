/**
 * 
 */
package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.ContractDetails;
import com.ib.client.EWrapper;


/**
 *
 * @author dkrebs
 * @since Dec 27, 2013
 * @category Jive Software
 */
public class WCScannerData implements EWrapperCall {
	
	private int reqId;
	private int rank;
	private ContractDetails contractDetails;
	private String distance; 
	private String benchmark;
	private String projection;
	private String legsStr;

	public WCScannerData(int reqId, int rank,
			ContractDetails contractDetails, String distance, String benchmark,
			String projection, String legsStr) { 
		this.reqId = reqId;
		this.rank = rank; 
		this.contractDetails = contractDetails;
		this.distance = distance;
		this.benchmark = benchmark;
		this.projection = projection;
		this.legsStr = legsStr;
	}

	@Override
	public void callMethod(EWrapper wrapper) {
		wrapper.scannerData(reqId, rank, contractDetails, distance, benchmark, projection, legsStr);
	}
	
	
}
