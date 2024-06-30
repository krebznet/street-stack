package com.dunkware.trade.broker.api.event;

import com.dunkware.trade.broker.api.Account;

public class EBrokerAccountEvent extends EBrokerEvent {

	private Account account; 
	
	public EBrokerAccountEvent(Account account) {
		super(account.getBroker());
	}

	public Account getAccount() {
		return account;
	}
	
	

	
	
}
