package com.dunkware.trade.broker.tws;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.broker.BrokerAccountSpec;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.runtime.broker.Broker;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.sdk.core.runtime.order.OrderException;

public class TwsAccount implements BrokerAccount {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private List<TwsAccountOrder> orders = new ArrayList<TwsAccountOrder>();
	private Semaphore orderLock = new Semaphore(1);
	
	private TwsBroker broker;
	private String id; 
	
	private DEventNode eventNode; 
	
	public TwsAccount(TwsBroker broker, String id) { 
		this.id = id; 
		this.broker = broker;	
		eventNode = broker.getEventNode().createChild("accounts/" + id);
	}

	@Override
	public BrokerAccountSpec getSpec() {
		return new BrokerAccountSpec();
	}
	
	

	@Override
	public Broker getBroker() {
		return broker;
	}

	@Override
	public Order createOrder(OrderType type) throws OrderException {
		TwsAccountOrder order = new TwsAccountOrder(this, type);
		try {
			orderLock.acquire();
			orders.add(order);
		} catch (Exception e) {
		} finally { 
			orderLock.release();
		}
		return order;
	}

	@Override
	public String getIdentifier() {
		return id;
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}
	
	
	

}
