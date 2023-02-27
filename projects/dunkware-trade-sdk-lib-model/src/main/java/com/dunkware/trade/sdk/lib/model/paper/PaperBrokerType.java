package com.dunkware.trade.sdk.lib.model.paper;

import com.dunkware.trade.sdk.core.model.broker.BrokerType;

public class PaperBrokerType implements BrokerType {

	private String identifier; 
	private int accounts; 
	
	@Override
	public String getIdentifier() {
		return identifier;
	}

	public int getAccounts() {
		return accounts;
	}

	public void setAccounts(int accounts) {
		this.accounts = accounts;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	

}
