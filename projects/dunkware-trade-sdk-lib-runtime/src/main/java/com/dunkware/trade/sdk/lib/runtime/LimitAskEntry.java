package com.dunkware.trade.sdk.lib.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.trade.sdk.core.model.order.OrderAction;
import com.dunkware.trade.sdk.core.model.order.OrderKind;
import com.dunkware.trade.sdk.core.model.order.OrderStatus;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.model.trade.EntrySpec;
import com.dunkware.trade.sdk.core.model.trade.EntryType;
import com.dunkware.trade.sdk.core.model.trade.TradeSide;
import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.sdk.core.runtime.order.builder.OrderTypeBuilder;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderException;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderFilled;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderUpdate;
import com.dunkware.trade.sdk.core.runtime.trade.Trade;
import com.dunkware.trade.sdk.core.runtime.trade.anot.ATradeEntry;
import com.dunkware.trade.sdk.core.runtime.trade.event.ETradeEntryCompleted;
import com.dunkware.trade.sdk.core.runtime.trade.event.ETradeEntryException;
import com.dunkware.trade.sdk.core.runtime.trade.event.ETradeEntryUpdate;
import com.dunkware.trade.sdk.core.runtime.trade.impl.TradeEntryImpl;
import com.dunkware.trade.sdk.lib.model.LimitAskEntryType;
import com.dunkware.trade.sdk.lib.model.MarketEntryType;

@ATradeEntry(type = LimitAskEntryType.class)
public class LimitAskEntry  extends TradeEntryImpl  {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private MarketEntryType type;
	
	private Order order; 
	
	private EntrySpec spec = new EntrySpec();
	@Override
	protected void doInit(EntryType type) throws Exception {
		this.type = (MarketEntryType)this.type;
		spec.setType(type);
		
	}

	@Override
	protected void doStart(Trade trade) throws Exception {
		
		OrderTypeBuilder builder = OrderTypeBuilder.newInstance()
		.size(trade.getSpec().getAllocatedSize())
		.kind(OrderKind.LMT)
		.outsideRth(true)
		.limit(trade.getInstrument().getAskPrice())
		.ticker(trade.getType().getTicker());
	
		if(trade.getType().getSide() == TradeSide.LONG) { 
			builder.action(OrderAction.BUY);
		} else { 
			if(trade.getType().getSide() == TradeSide.SHORT) {
				builder.action(OrderAction.SSHORT);
			} else { 
				throw new Exception("Invalid Trade Side " + trade.getType().getSide());
			}
		}
		OrderType orderType = builder.build();
		
	    // create orders right!? where the fuck lol 
		order = trade.getContext().createOrder(orderType);
		try {
			getSpec().setOpeningTime(DDateTime.now());
			getSpec().setAllocatedSize(trade.getSpec().getAllocatedSize());
			
			order.getEventNode().addEventHandler(this);
			order.send();			
		} catch (Exception e) {
			throw new Exception("Exception Sending Market Entry Order " + e.toString(),e);
		}
		// 

		
		// long | short right 
		//.action(trade.getType().geta)
	}

	@Override
	protected void doCancel() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@ADEventMethod()
	public void orderUpdate(EOrderUpdate update) {
		if(update.getOrder().getSpec().getStatus() == OrderStatus.Filled) { 
			// 
			
		}
	}
	
	@ADEventMethod()
	public void orderFilled(EOrderFilled filled) { 
		if(logger.isDebugEnabled()) { 
			logger.debug("Market Entry Order Filled ");
		}
		getSpec().setFilledSize(filled.getOrder().getSpec().getFilled());
		EntrySpec spec = getSpec();
		spec.setAvgFillPrice(filled.getOrder().getSpec().getAvgFillPrice());
		spec.setCommission(filled.getOrder().getSpec().getCommision());
		spec.setOpenTime(DDateTime.now());
		
		getSpec().setAvgFillPrice(filled.getOrder().getSpec().getAvgFillPrice());
		getSpec().setFilledSize(filled.getOrder().getSpec().getFilled());
		getSpec().setOpenTime(DDateTime.now());
		
		// send update 
		ETradeEntryUpdate update = new ETradeEntryUpdate(this);
		getEventNode().event(update);
		// send completed event 
		ETradeEntryCompleted completed = new ETradeEntryCompleted(this);
		getEventNode().event(completed);
		
	}
	
	@ADEventMethod()
	public void orderException(EOrderException exception) { 
		logger.info("Market Entry Exception Handling " + exception.getOrder().getSpec().getException());
		getSpec().setException(exception.getOrder().getSpec().getException());
		ETradeEntryException expt = new ETradeEntryException(this);
		getEventNode().event(expt);
		
		//getSpec().setException(exception.getOrder().getSpec().getException());
		//setAndNotifyException(exception.getOrder().getSpec().getException());
	}
	

	
}