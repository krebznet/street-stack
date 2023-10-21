package com.dunkware.trade.sdk.core.runtime.trade.impl;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.trade.EntrySpec;
import com.dunkware.trade.sdk.core.model.trade.EntryStatus;
import com.dunkware.trade.sdk.core.model.trade.EntryType;
import com.dunkware.trade.sdk.core.runtime.trade.Trade;
import com.dunkware.trade.sdk.core.runtime.trade.TradeEntry;

public abstract class TradeEntryImpl implements TradeEntry {

	private EntrySpec spec = new EntrySpec();
	private Trade trade; 
	private EntryType type; 
	
	@Override
	public EntrySpec getSpec() {
		return spec; 
	}

	@Override
	public void init(EntryType type) throws Exception {
		spec = new EntrySpec();
		spec.setType(type);
		this.type = type;
		doInit(type);
	}

	@Override
	public void start(Trade trade) throws Exception {
		this.trade = trade;
		this.doStart(trade);
	}

	@Override
	public void cancel() throws Exception {
		this.doCancel();
	}

	@Override
	public EntryStatus getStatus() {
		return spec.getStatus();
	}

	@Override
	public Trade getTrade() {
		return trade;
	}

	@Override
	public DEventNode getEventNode() {
		return trade.getEventNode();
	}
	
	/**
	 * Sets the status and sends notification through event node
	 * @param status
	 */
	protected void setStatus(EntryStatus status) { 
		if(spec.getStatus() != status) { 
			this.spec.setStatus(status);
		
		}
	}
	
	
	protected abstract void doInit(EntryType type) throws Exception;

	protected abstract void doStart(Trade trade) throws Exception;
	
	protected abstract void doCancel() throws Exception;

}
