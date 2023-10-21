package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.trade.sdk.core.runtime.trade.TradeExit;
import com.dunkware.trade.service.beach.server.repository.BeachExitDO;

public interface BeachExit extends TradeExit {

	BeachExitDO getEntity();
}
