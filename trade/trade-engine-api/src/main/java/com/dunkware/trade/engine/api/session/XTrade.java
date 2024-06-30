package com.dunkware.trade.engine.api.session;

import com.dunkware.trade.engine.api.session.bean.XTradeBean;

public interface XTrade {

	XTradeState getState();
	
	XTradeBean getBean();
	
	
}
