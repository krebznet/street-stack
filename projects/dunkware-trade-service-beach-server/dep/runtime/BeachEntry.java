package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.trade.sdk.core.runtime.trade.TradeEntry;
import com.dunkware.trade.service.beach.server.repository.BeachEntryDO;

public interface BeachEntry extends TradeEntry  {

	BeachEntryDO getEntity();
	
}
