package com.dunkware.trade.api.broker.model;

public enum BrokerOrderAction {

	BUY("Buy"),
	SELL("Sell"),
	None( ""),
	SSHORT("SellShort");
	
	private String m_apiString;

	private BrokerOrderAction( String apiString) {
		m_apiString = apiString;
	}

	public String Value() {
		return m_apiString;
	}
	
	public static BrokerOrderAction get(String apiString) {
		if (apiString != null && apiString.length() > 0 && !apiString.equals( "None") ) {
			for (BrokerOrderAction type : values() ) {
				if (type.m_apiString.equals( apiString) ) {
					return type;
				}
			}
		}
		return None;
	}
}
