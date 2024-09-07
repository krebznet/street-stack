package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Account;

public class EAccountEvent extends EBrokerEvent {

	private Account account; 
	
	public EAccountEvent(Account account) {
		super(account.getBroker());
	}

	public Account getAccount() {
		return account;
	}
	
	

	
	
}
