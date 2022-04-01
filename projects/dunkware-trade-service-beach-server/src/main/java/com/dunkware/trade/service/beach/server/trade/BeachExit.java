package com.dunkware.trade.service.beach.server.trade;

import com.dunkware.trade.sdk.core.runtime.trade.TradeExit;
import com.dunkware.trade.service.beach.server.trade.entity.BeachExitDO;

public interface BeachExit extends TradeExit {

	BeachExitDO getEntity();
}
