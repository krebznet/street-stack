package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.EWrapper;

public class WCManagedAccounts implements EWrapperCall {

	private String _accounts; 
	
	public WCManagedAccounts(String accounts) { 
		_accounts = accounts; 
	}

	@Override
	public void callMethod(EWrapper wrapper) {
		wrapper.managedAccounts(_accounts);
		
	}
	
	
}
