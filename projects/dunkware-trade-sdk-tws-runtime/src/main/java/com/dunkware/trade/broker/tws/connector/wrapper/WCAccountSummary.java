package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.EWrapper;

/**
 * 
 * @author dkrebs
 *
 */
public class WCAccountSummary implements EWrapperCall {
	
	private int reqId; 
	private String account; 
	private String tag;
	private String currency; 
	private String value; 
	
	public WCAccountSummary(int reqId, String account, String tag,
	String value, String currency) {
		this.reqId = reqId;
		this.value = value; 
		this.account = account; 
		this.tag = tag; 
		this.currency = currency; 
	}

	@Override
	public void callMethod(EWrapper wrapper) {
		wrapper.accountSummary(reqId, account, tag, value, currency);
	}
	
	

}
