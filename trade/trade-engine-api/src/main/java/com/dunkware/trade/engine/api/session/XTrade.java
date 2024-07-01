package com.dunkware.trade.engine.api.session;

import com.dunkware.trade.engine.api.session.bean.XTradeBean;
import com.dunkware.utils.core.events.DunkEventNode;

public interface XTrade {

	XTradeState getState();
	
	XTradeBean getBean();
	
	DunkEventNode getEventNode();
	
	
	
}
