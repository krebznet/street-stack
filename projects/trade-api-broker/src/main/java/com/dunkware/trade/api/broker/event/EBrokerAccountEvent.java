package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.Account;

public class EBrokerAccountEvent extends EBrokerEvent {

	private Account account; 
	
	public EBrokerAccountEvent(Account account) {
		super(account.getBroker());
	}

	public Account getAccount() {
		return account;
	}
	
	

	
	
}
