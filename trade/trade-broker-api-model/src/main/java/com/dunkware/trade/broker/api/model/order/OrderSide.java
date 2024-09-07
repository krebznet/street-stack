package com.dunkware.trade.broker.api.model.order;

public enum OrderSide {

	BUY("Buy"),
	SELL("Sell"),
	None( ""),
	SSHORT("SellShort");
	
	private String m_apiString;

	private OrderSide( String apiString) {
		m_apiString = apiString;
	}

	public String Value() {
		return m_apiString;
	}
	
	public static OrderSide get(String apiString) {
		if (apiString != null && apiString.length() > 0 && !apiString.equals( "None") ) {
			for (OrderSide type : values() ) {
				if (type.m_apiString.equals( apiString) ) {
					return type;
				}
			}
		}
		return None;
	}
}
