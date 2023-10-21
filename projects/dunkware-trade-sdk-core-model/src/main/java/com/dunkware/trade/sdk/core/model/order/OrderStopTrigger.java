package com.dunkware.trade.sdk.core.model.order;

public enum OrderStopTrigger {

	Default( 0), DoubleBidAsk( 1), Last( 2), DoubleLast( 3), BidAsk( 4), LastOrBidAsk( 7), Midpoint( 8);

	int m_val;

	public int val() { return m_val; }

	private OrderStopTrigger( int val) {
		m_val = val;
	}

	public static OrderStopTrigger get( int val) {
		for (OrderStopTrigger m : values() ) {
			if (m.m_val == val) {
				return m;
			}
		}
		return null;
	}

}
