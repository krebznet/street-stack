package com.dunkware.street.broker.lib.api.events;

import com.dunkware.street.broker.lib.api.StreetBrokerAccount;
import com.dunkware.utils.core.events.DunkEvent;

public class EStreetBrokerAccountEvent extends DunkEvent {

	private StreetBrokerAccount account;

	public EStreetBrokerAccountEvent(StreetBrokerAccount account) {
		this.account = account;
	}

	public StreetBrokerAccount getAccount() {
		return account;
	}

}
