package com.dunkware.trade.sdk.lib.runtime.paper;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.broker.AccountBean;
import com.dunkware.trade.sdk.core.model.broker.AccountStatus;
import com.dunkware.trade.sdk.core.model.broker.BrokerAccountSpec;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.runtime.broker.Broker;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.sdk.core.runtime.order.OrderException;
import com.dunkware.trade.sdk.core.runtime.order.OrderPreview;

/**
 * Sends order filled after 30 seconds fine. 
 * 
 * @author duncankrebs
 *
 */
public class PaperAccount implements BrokerAccount {
	
	private String identifier; 
	private PaperBroker broker; 
	
	private DEventNode eventNode; 
	
	public PaperAccount(PaperBroker broker, String identifier) { 
		this.identifier = identifier;
		this.broker = broker; 
		this.eventNode = broker.getEventNode().createChild("/accounts/" + identifier);
		
	}

	

	@Override
	public AccountBean getBean() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public AccountStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}



	


	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Order createOrder(OrderType type) throws OrderException {
		try {
			PaperOrder order = new PaperOrder(type,this);			
			return order; 
		} catch (Exception e) {
			throw new OrderException(e.toString());
		}

		
	}

	@Override
	public OrderPreview createOrderPreview(OrderType type) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

	@Override
	public Broker getBroker() {
		return broker; 
	}

}
