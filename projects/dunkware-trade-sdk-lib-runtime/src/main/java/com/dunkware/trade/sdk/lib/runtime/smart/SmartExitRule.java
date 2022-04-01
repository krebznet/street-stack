package com.dunkware.trade.sdk.lib.runtime.smart;

import com.dunkware.trade.sdk.core.model.order.OrderAction;
import com.dunkware.trade.sdk.core.model.order.OrderKind;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.model.trade.TradeSide;
import com.dunkware.trade.sdk.core.model.trade.TradeType;
import com.dunkware.trade.sdk.core.runtime.order.builder.OrderTypeBuilder;
import com.dunkware.trade.sdk.core.runtime.trade.Trade;
import com.dunkware.trade.sdk.lib.model.smart.SmartExitRuleType;

public abstract class SmartExitRule {

	public abstract void init(SmartExitRuleType type, SmartExit exit) throws Exception;
	
	public abstract void start() ;
	
	public abstract void dispose(); 
	
	public abstract void lockRequested(); 
	
	public abstract void lockAcquired();
	
	public abstract Trade getTrade();

	/**
	 * Child classes can do this and this will initite a Market
	 * @throws Exception
	 */
	protected OrderType createExitOrder() throws Exception { 
		Trade trade = getTrade();
		TradeType type = getTrade().getType();
		OrderTypeBuilder builder = OrderTypeBuilder.newInstance();
		builder.ticker(type.getTicker());
		if(type.getSide() == TradeSide.LONG) { 
			builder.action(OrderAction.SELL);
		} else { 
			builder.action(OrderAction.BUY);
		}
		builder.kind(OrderKind.MKT);
		builder.size(trade.getSpec().getAllocatedSize());
		OrderType orderType = builder.build();
		return orderType;
	}
}
