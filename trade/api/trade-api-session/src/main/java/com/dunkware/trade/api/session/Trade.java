package com.dunkware.trade.api.session;

import com.dunkware.trade.api.session.model.TradeBean;

public interface Trade {
	
	public TradeStatus getStatus();
	
	public TradeBean getBean();
	
	public void open();
	
	

}
