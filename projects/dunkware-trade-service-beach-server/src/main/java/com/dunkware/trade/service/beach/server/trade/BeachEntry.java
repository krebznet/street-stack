package com.dunkware.trade.service.beach.server.trade;

import com.dunkware.trade.sdk.core.runtime.trade.TradeEntry;
import com.dunkware.trade.service.beach.server.trade.entity.BeachEntryDO;

public interface BeachEntry extends TradeEntry  {

	BeachEntryDO getEntity();
	
}
