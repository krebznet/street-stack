package com.dunkware.trade.service.beach.server.runtime.core;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.trade.ExitSpec;
import com.dunkware.trade.sdk.core.model.trade.ExitType;
import com.dunkware.trade.sdk.core.runtime.trade.Trade;
import com.dunkware.trade.sdk.core.runtime.trade.TradeExit;
import com.dunkware.trade.service.beach.server.repository.BeachExitDO;
import com.dunkware.trade.service.beach.server.repository.BeachTradeRepo;
import com.dunkware.trade.service.beach.server.runtime.BeachExit;

public class BeachExitImpl implements BeachExit {

	
	@Autowired
	private BeachTradeRepo tradeRepo;
	
	private BeachExitDO ent; 
	private TradeExit exit;
	
	// exit status 
	public void init(BeachExitDO ent,TradeExit exit) {
		this.ent = ent;
		this.exit = exit; 
	
	}
	
	@Override
	public void init(ExitType type) throws Exception {
		exit.init(type);
	}

	@Override
	public void start(Trade trade) {
		exit.start(trade);
	}

	@Override
	public void cancel() throws Exception {
		exit.cancel();
	}

	@Override
	public ExitSpec getSpec() {
		return exit.getSpec();
	}

	@Override
	public Trade getTrade() {
		return exit.getTrade();
	}

	@Override
	public BeachExitDO getEntity() {
		return ent;
	}

	@Override
	public DEventNode getEventNode() {
		return exit.getEventNode();
	}

	
}
