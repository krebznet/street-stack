package com.dunkware.trade.sdk.lib.runtime.smart.rules;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.calc.DCalc;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.trade.sdk.core.model.order.OrderAction;
import com.dunkware.trade.sdk.core.model.order.OrderKind;
import com.dunkware.trade.sdk.core.model.order.OrderStopTrigger;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.model.trade.TradeSide;
import com.dunkware.trade.sdk.core.model.trade.TradeType;
import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.sdk.core.runtime.order.builder.OrderTypeBuilder;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderCancelled;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderFilled;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderUpdate;
import com.dunkware.trade.sdk.core.runtime.trade.Trade;
import com.dunkware.trade.sdk.lib.model.smart.SmartExitRuleType;
import com.dunkware.trade.sdk.lib.model.smart.rules.TrailingStopRuleType;
import com.dunkware.trade.sdk.lib.runtime.smart.SmartExit;
import com.dunkware.trade.sdk.lib.runtime.smart.SmartExitRule;
import com.dunkware.trade.sdk.lib.runtime.util.TrailingHelper;

public class TrailingStopRule extends SmartExitRule {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private TrailingStopRuleType type; 
	private SmartExit exit; 
	
	private Order trailingOrder;
	private boolean cancellingOrder = false; 
	
	private boolean lockReleased = false; 
	
	
	@Override
	public void init(SmartExitRuleType type, SmartExit exit) throws Exception {
		this.type = (TrailingStopRuleType)type;
		this.exit = exit; 
		
	}

	@Override
	public void start() {
		exit.requestLock(this);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void lockRequested() {
		if(!cancellingOrder) { 
			try {
				trailingOrder.cancel();
				
			} catch (Exception e) {
				
			}
			
		}
	}

	@Override
	public void lockAcquired() {
		OrderTypeBuilder builder = OrderTypeBuilder.newInstance();
		builder.ticker(getTrade().getInstrument().getTicker());
		if(getTradeType().getSide() == TradeSide.LONG) { 
			builder.action(OrderAction.SELL);
		} else { 
			builder.action(OrderAction.BUY);
		}
		builder.kind(OrderKind.TRAIL_PERCENT);
		builder.ticker(exit.getTrade().getSpec().getType().getTicker());
		builder.size(getTrade().getSpec().getEntry().getFilledSize());
		builder.stopTrigger(OrderStopTrigger.Last);
		builder.trailingPercent(this.type.getTrail());
		if(type.getStop() != Double.MIN_VALUE) { 
			// calculate percent off from last price
			double lastPrice = getTrade().getInstrument().getLast();
			// minus percent 
			double stopPrice = TrailingHelper.subtractPercent(lastPrice, type.getStop());
			if(logger.isDebugEnabled()) { 
				logger.debug("Trailing Stop Calculated Stop Price " + stopPrice + " from last price " + lastPrice + " with stop percent " + type.getStop());
			}
			builder.trailingStopPrice(stopPrice);
		}

		OrderType orderType = null;
		try {
			orderType = builder.build();	
		} catch (Exception e) {
			exit.exception("Trailing Stop Rule Order Type Builder Exception " + e.toString());
			return;
		}
		try {
			trailingOrder = exit.getTrade().getContext().createOrder(orderType);
		} catch (Exception e) {
			exit.exception("Trailing Stop Order Create Exception " + e.toString());
			return;
		}
		try {
			trailingOrder.getEventNode().addEventHandler(this);
			trailingOrder.send();
		} catch (Exception e) {
			exit.exception("Internal Trailing Rule Exception On Order Send " + e.toString());
		}
		
	}
	
	@Override
	public Trade getTrade() {
		return exit.getTrade();
	}
	
	private TradeType getTradeType() { 
		return exit.getTrade().getType();
	}

	
	@ADEventMethod()
	public void orderUpdate(EOrderUpdate update) {
		
	}
	
	@ADEventMethod()
	public void orderFilled(EOrderFilled filled) { 
		exit.completed();
	}
	
	@ADEventMethod()
	public void orderCancelled(EOrderCancelled cancelled) { 
		if(!lockReleased && cancellingOrder) { 
			if(logger.isDebugEnabled()) { 
				logger.debug("Trailing Stop Rule Order Cancelled Releasing Lock ");
			}
			exit.releaseLock(TrailingStopRule.this);
		}
	}
	
}
