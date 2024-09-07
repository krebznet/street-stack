package com.dunkware.trade.broker.api.model.order;

import java.beans.Transient;

public enum OrderStatus {
	Created, // Set when order is first created
	Sent, // Set when order is sent 
	ApiPending, // Broker Sets This When Problems 
	ApiCancelled, // ApiCancelled WTF
	PreSubmitted, // PreSubmitted when it sits on broker when exchange is closed
	PendingCancel, // User has requested cancel but not yet cancelled
	Cancelled,
	Submitted,
	Filled,
	Inactive,
	PendingSubmit,
	Unknown,
	Rejected,
	Exception;
	
	@Transient
	public boolean isActive() {
		return this == PreSubmitted || this == PendingCancel || this == Submitted || this == PendingSubmit;
	}
	
	@Transient
	public boolean canCancel() { 
		return this == PreSubmitted || this == Submitted || this == PendingSubmit;
	}

}
