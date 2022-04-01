package com.dunkware.trade.sdk.core.runtime.trade;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.trade.EntrySpec;
import com.dunkware.trade.sdk.core.model.trade.EntryStatus;
import com.dunkware.trade.sdk.core.model.trade.EntryType;

public interface TradeEntry {

	public EntrySpec getSpec();

	public void init(EntryType type) throws Exception;

	public void start(Trade trade) throws Exception;

	public void cancel() throws Exception;

	public EntryStatus getStatus();

	public Trade getTrade();

	public DEventNode getEventNode();
}
