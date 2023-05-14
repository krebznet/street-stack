package com.dunkware.trade.service.beach.server.runtime.core.entry;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.stopwatch.DTimer;
import com.dunkware.trade.sdk.core.model.order.OrderAction;
import com.dunkware.trade.sdk.core.model.order.OrderKind;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderException;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderFilled;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderUpdate;
import com.dunkware.trade.service.beach.protocol.play.Play;
import com.dunkware.trade.service.beach.server.runtime.BeachOrder;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;
import com.dunkware.trade.service.beach.server.runtime.core.BeachTradeEntry;
import com.dunkware.trade.service.beach.server.runtime.core.events.EBeachTradeEntryComplete;
import com.dunkware.trade.service.beach.server.runtime.core.events.EBeachTradeEntryException;

public class BeachTradeMarketEntry implements BeachTradeEntry {

	
	private DTimer timeoutTimer; 

	private BeachTrade trade; 
	private DEventNode eventNode;
	
	private BeachOrder order; 
	
	private Play model; 
	
	private double commission; 
	private int filled; 
	private String exception;
	
	@Override
	public void start(BeachTrade trade) {
		this.trade = trade;
		model = trade.getPlay().getModel();
		eventNode = trade.getEventNode().createChild("/entry");
		// create an order
		OrderType orderType = new OrderType();
		if(model.getSide().equalsIgnoreCase("LONG")) {
			orderType.setAction(OrderAction.BUY);
		}
		orderType.setKind(OrderKind.MKT);
		orderType.setTicker(trade.getTickerSpec());
		orderType.setSize(trade.getTradeSpec().getSize());
		//orderType.setSize(trade.getsp)
		try {
			order = trade.entryOrder(orderType,"Market Entry");
			order.getEventNode().addEventHandler(this);
		} catch (Exception e) {
			// OKAY ISSUE HERE: 
		}
		try {
			order.send();	
		} catch (Exception e) {
			// same as last exception. 
		}
		
	}
	
	@Override
	public DEventNode getEventNode() {
		return eventNode; 
	}

	@Override
	public double getCommission() {
		return commission;
	}

	@Override
	public int getFilledSize() {
		return filled;
	}

	/******** EVENT HANDLER METHODS ON THE ORDER ***/ 
	
	/**
	 * This is coming form BeachOrder no need to persist. 
	 * notify trade that the entry is completed. 
	 * @param event
	 */
	public void orderFilled(EOrderFilled event) { 
		this.commission = event.getOrder().getSpec().getCommision();
		this.filled = event.getOrder().getSpec().getFilled();
		eventNode.event(new EBeachTradeEntryComplete(this.trade, this));
	}
	
	/**
	 * Okay Exception send it up to the trade 
	 * @param event
	 */
	public void orderException(EOrderException event) { 
		this.exception = event.getOrder().getSpec().getException();
		eventNode.event(new EBeachTradeEntryException(trade, exception));
	}
	
	/**
	 * Not sure what we need to do here? send up the event? 
	 * @param update
	 */
	public void orderUpdate(EOrderUpdate update) { 
		
	}


	
}
