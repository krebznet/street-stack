package com.dunkware.trade.broker.tws;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.broker.api.Account;
import com.dunkware.trade.broker.api.AccountBean;
import com.dunkware.trade.broker.api.AccountStatus;
import com.dunkware.trade.broker.api.Broker;
import com.dunkware.trade.broker.api.Order;
import com.dunkware.trade.broker.api.OrderException;
import com.dunkware.trade.broker.api.OrderPreview;
import com.dunkware.trade.broker.api.OrderSpec;
import com.dunkware.trade.broker.api.OrderType;
import com.dunkware.trade.broker.tws.connector.TwsSocketReader;
import com.dunkware.utils.core.events.DunkEventNode;

public class TwsAccount implements Account, TwsSocketReader {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private List<TwsAccountOrder> orders = new ArrayList<TwsAccountOrder>();
	private Semaphore orderLock = new Semaphore(1);

	private TwsBroker broker;
	private String id;

	private DunkEventNode eventNode;
	
	private AccountBean bean;

	private AccountStatus status = AccountStatus.Pending;
	
	public TwsAccount(TwsBroker broker, String id) {
		this.id = id;
		this.eventNode = broker.getEventNode().createChild(this);
		bean = new AccountBean();
		
		bean.setName(id);
		bean.setStatus(status.name());
		this.broker = broker;
		eventNode = broker.getEventNode().createChild(this);
	}
	
	public void setStatus(AccountStatus status)  { 
		this.status = status;
		bean.setStatus(status.name());
		
	}
	
	
	@Override
	public Order createOrder(OrderSpec spec) throws OrderException {
		TwsAccountOrder order = new TwsAccountOrder(this,spec);
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
	public OrderPreview createOrderPreview(OrderType type) throws OrderException {
		
		return null;
	}

	@Override
	public AccountBean getBean() {
		return bean; 
	}

	@Override
	public AccountStatus getStatus() {
		return status; 
	}

	@Override
	public void init() {
		broker.getClientSocket().reqAccountSummary(0, id, id);
	}

	@Override
	public void dispose() {
		
		
	}

	@Override
	public Broker getBroker() {
		return broker;
	}

	
	@Override
	public String getIdentifier() {
		return id;
	}



	@Override
	public DunkEventNode getEventNode() {
		return eventNode; 
	}

	

}
