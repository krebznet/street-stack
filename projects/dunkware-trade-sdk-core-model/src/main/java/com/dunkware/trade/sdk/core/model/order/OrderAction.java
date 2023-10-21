package com.dunkware.trade.sdk.core.model.order;

public enum OrderAction {


	BUY("Buy"),
	SELL("Sell"),
	None( ""),
	SSHORT("SellShort");
	
	private String m_apiString;

	private OrderAction( String apiString) {
		m_apiString = apiString;
	}

	public String Value() {
		return m_apiString;
	}
	
	public static OrderAction get(String apiString) {
		if (apiString != null && apiString.length() > 0 && !apiString.equals( "None") ) {
			for (OrderAction type : values() ) {
				if (type.m_apiString.equals( apiString) ) {
					return type;
				}
			}
		}
		return None;
	}
}
