package com.dunkware.trade.sdk.lib.runtime.exit.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderFilled;
import com.dunkware.trade.sdk.core.runtime.trade.Trade;
import com.dunkware.trade.sdk.core.runtime.trade.event.ETradeSpecUpdate;
import com.dunkware.trade.sdk.lib.model.exit.SmartExitRuleType;
import com.dunkware.trade.sdk.lib.model.exit.rules.SmartExitGainLossAmount;
import com.dunkware.trade.sdk.lib.runtime.exit.SmartExit;
import com.dunkware.trade.sdk.lib.runtime.exit.SmartExitRule;

public class GainLossRule extends SmartExitRule {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private SmartExitGainLossAmount type; 
	private SmartExit exit; 
	
	private boolean triggered = false;
	private Order exitOrder; 
	

	@Override
	public void init(SmartExitRuleType type, SmartExit exit) throws Exception {
		this.type = (SmartExitGainLossAmount)type;
		this.exit = exit;
		
	}

	@Override
	public void start() {
		exit.getTrade().getEventNode().addEventHandler(this);
	}

	@Override
	public void dispose() {
		exit.getTrade().getEventNode().removeEventHandler(this);
	}

	@Override
	public void lockRequested() {
		// we don't care already selling
	}
	
	@Override
	public Trade getTrade() {
		return exit.getTrade();
	}

	@Override
	public void lockAcquired() {
		try {
			OrderType orderType = createExitOrder();
			this.exitOrder = getTrade().getContext().createOrder(orderType);
			exitOrder.getEventNode().addEventHandler(this);
			exitOrder.send();
		} catch (Exception e) {
			exit.exception("GainLoss Exit Order Create/Send Exception " + e.toString());
		}

		
	}
	
	@ADEventMethod()
	public void orderFilled(EOrderFilled filled) { 
		exit.completed();
	}

	@ADEventMethod()
	public void tradeSpecUpdate(ETradeSpecUpdate update) {
		double upl = update.getTrade().getSpec().getUnrealizedPL();
		if(logger.isTraceEnabled()) { 
			logger.trace("GainLoss UPL {} On Symbol {}",upl,getTrade().getSpec().getType().getTicker().getSymbol());
		}
		if(type.getTrigger() < 0.0) { 
			if(upl < type.getTrigger() || upl == type.getTrigger()) { 
				if(!triggered) { 
					exit.requestLock(GainLossRule.this);
					triggered = true;
				}

			}
		}
		if(type.getTrigger() > 0.0) { 
			if(upl > type.getTrigger() || upl == type.getTrigger()) { 
				if(!triggered) { 
					exit.requestLock(GainLossRule.this);
					triggered = true;
				}

			}
		}
	}
	
	

	
}
