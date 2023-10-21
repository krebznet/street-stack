package com.dunkware.trade.sdk.core.runtime.broker.event;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;

public class EAccountEvent extends DEvent {

	private BrokerAccount account;

	public EAccountEvent(BrokerAccount account) {
		this.account = account;
	}

	public BrokerAccount getAccount() {
		return account;
	}

}
