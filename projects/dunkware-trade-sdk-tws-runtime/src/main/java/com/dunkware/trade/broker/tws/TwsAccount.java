package com.dunkware.trade.broker.tws;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.broker.tws.connector.TwsSocketReader;
import com.dunkware.trade.sdk.core.model.broker.AccountBean;
import com.dunkware.trade.sdk.core.model.broker.AccountStatus;
import com.dunkware.trade.sdk.core.model.broker.BrokerAccountSpec;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.runtime.broker.Broker;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.sdk.core.runtime.order.OrderException;
import com.dunkware.trade.sdk.core.runtime.order.OrderPreview;

public class TwsAccount implements BrokerAccount, TwsSocketReader {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private List<TwsAccountOrder> orders = new ArrayList<TwsAccountOrder>();
	private Semaphore orderLock = new Semaphore(1);

	private TwsBroker broker;
	private String id;

	private DEventNode eventNode;
	
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Broker getBroker() {
		return broker;
	}

	@Override
	public Order createOrder(OrderType type) throws OrderException  {
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

	@Override
	public OrderPreview createOrderPreview(OrderType type) throws Exception {
		TwsAccountOrder order = new TwsAccountOrder(this, type);
		return order.preview();
	}
	
	

}
