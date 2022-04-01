package com.dunkware.trade.sdk.core.runtime.trade;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.trade.ExitSpec;
import com.dunkware.trade.sdk.core.model.trade.ExitType;

public interface TradeExit {

	void init(ExitType type) throws Exception;

	void start(Trade trade);

	void cancel() throws Exception;

	ExitSpec getSpec();

	Trade getTrade();

	DEventNode getEventNode();
}
