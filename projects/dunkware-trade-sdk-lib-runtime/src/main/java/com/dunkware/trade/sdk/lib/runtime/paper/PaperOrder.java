package com.dunkware.trade.sdk.lib.runtime.paper;

import javax.swing.border.EtchedBorder;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.dtime.DZonedDateTime;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.order.OrderKind;
import com.dunkware.trade.sdk.core.model.order.OrderSpec;
import com.dunkware.trade.sdk.core.model.order.OrderStatus;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.sdk.core.runtime.order.OrderException;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderFilled;
import com.dunkware.trade.sdk.core.runtime.order.event.EOrderUpdate;
import com.dunkware.trade.tick.api.instrument.Instrument;

public class PaperOrder implements Order {

	OrderSpec orderSpec = new OrderSpec();
	private DEventNode eventNode;

	private OrderType type;
	private PaperAccount account;

	private Thread controller;
	
	private Instrument instrument; 

	public PaperOrder(OrderType type, PaperAccount account) throws Exception {
		this.type = type;
		this.account = account;
		try {
			this.instrument = account.getBroker().acquireInstrument(type.getTicker());	
		} catch (Exception e) {
			throw e;
			// TODO: handle exception
		}
		
		eventNode = account.getEventNode().createChild("order/fuck");
		orderSpec.setFilled(0);
		orderSpec.setStatus(OrderStatus.Created);
		orderSpec.setSize(500);
		

	}

	@Override
	public OrderStatus getStatus() {
		return orderSpec.getStatus();
	}

	@Override
	public void send() throws OrderException {
		if (type.getKind() == OrderKind.MKT) {
			Thread market = new Thread() {

				public void run() {
					try {
						Thread.sleep(100);
						orderSpec.setStatus(OrderStatus.Submitted);
						EOrderUpdate update = new EOrderUpdate(PaperOrder.this);
						eventNode.event(update);
						Thread.sleep(5000);
						orderSpec.setStatus(OrderStatus.Filled);
						orderSpec.setAvgFillPrice(instrument.getLast() + 1.4);
						orderSpec.setFilled(500);
						orderSpec.setFillDateTime(DZonedDateTime.now(DTimeZone.NewYork));
						eventNode.event(update);
						EOrderFilled filled = new EOrderFilled(PaperOrder.this);
						eventNode.event(filled);
					} catch (Exception e) {
						System.err.println(e.toString());
					}
				}

			};
			
			market.run();
			
		}
		if (type.getKind() == OrderKind.LIT) {

		}
		if (type.getKind() == OrderKind.STP) {

		}

	}

	@Override
	public void cancel() throws OrderException {
		// just check status and if good cancel in 5 seconds later.

	}

	@Override
	public OrderSpec getSpec() {
		return orderSpec;
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

}
