package com.dunkware.trade.broker.tws;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.api.broker.Broker;
import com.dunkware.trade.api.broker.BrokerAccount;
import com.dunkware.trade.api.broker.BrokerAccountStatus;
import com.dunkware.trade.api.broker.BrokerOrder;
import com.dunkware.trade.api.broker.BrokerOrderException;
import com.dunkware.trade.api.broker.BrokerOrderPreview;
import com.dunkware.trade.api.broker.BrokerOrderType;
import com.dunkware.trade.api.broker.model.BrokerAccountBean;
import com.dunkware.trade.broker.tws.connector.TwsSocketReader;
import com.dunkware.utils.core.events.DunkEventNode;

public class TwsAccount implements BrokerAccount, TwsSocketReader {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private List<TwsAccountOrder> orders = new ArrayList<TwsAccountOrder>();
	private Semaphore orderLock = new Semaphore(1);

	private TwsBroker broker;
	private String id;

	private DunkEventNode eventNode;
	
	private BrokerAccountBean bean;

	private BrokerAccountStatus status = BrokerAccountStatus.Pending;
	
	public TwsAccount(TwsBroker broker, String id) {
		this.id = id;
		this.eventNode = broker.getEventNode().createChild(this);
		bean = new BrokerAccountBean();
		
		bean.setName(id);
		bean.setStatus(status.name());
		this.broker = broker;
		eventNode = broker.getEventNode().createChild(this);
	}
	
	public void setStatus(BrokerAccountStatus status)  { 
		this.status = status;
		bean.setStatus(status.name());
		
	}
	
	
	@Override
	public BrokerOrder createOrder(BrokerOrderType type) throws BrokerOrderException {
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
	public BrokerOrderPreview createOrderPreview(BrokerOrderType type) throws BrokerOrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BrokerAccountBean getBean() {
		return bean; 
	}

	@Override
	public BrokerAccountStatus getStatus() {
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
	public String getIdentifier() {
		return id;
	}



	@Override
	public DunkEventNode getEventNode() {
		return eventNode; 
	}

	

}
