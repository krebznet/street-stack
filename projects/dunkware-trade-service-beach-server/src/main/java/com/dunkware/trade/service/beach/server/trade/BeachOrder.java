package com.dunkware.trade.service.beach.server.trade;

import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.service.beach.server.trade.entity.BeachOrderDO;

public interface BeachOrder extends Order {

	BeachAccount getAccount();
	
	BeachOrderDO getEntity();

}
