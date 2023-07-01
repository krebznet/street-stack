package com.dunkware.trade.broker.tws;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.concurrency.ReusableCountDownLatch;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.dtime.DZonedDateTime;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.trade.broker.tws.connector.TwsConnector;
import com.dunkware.trade.broker.tws.connector.TwsSocketReader;
import com.dunkware.trade.broker.tws.instrument.TwsInstruments;
import com.dunkware.trade.sdk.core.model.broker.BrokerSpec;
import com.dunkware.trade.sdk.core.model.broker.BrokerStatus;
import com.dunkware.trade.sdk.core.model.broker.BrokerType;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.sdk.core.runtime.broker.anot.ABroker;
import com.dunkware.trade.sdk.core.runtime.broker.event.EBrokerDisconnected;
import com.dunkware.trade.sdk.core.runtime.broker.impl.BrokerImpl;
import com.dunkware.trade.tick.api.instrument.Instrument;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

@ABroker(type = TwsBrokerType.class)
public class TwsBroker extends BrokerImpl implements TwsSocketReader  {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private TwsConnector connector;
	
	private TwsBrokerType type;
	
	private List<BrokerAccount> accounts = new ArrayList<BrokerAccount>();
	
	private volatile int nextOrderId; 
	
	private DZonedDateTime connectionTime = null;

	private ReusableCountDownLatch connectLatch = new ReusableCountDownLatch();

	private DEventNode eventNode; 
	
	private TwsInstruments instruments; 

	private DExecutor exector; 
	@Override
	public void connect(BrokerType type, DEventNode eventNode, DExecutor executor) throws Exception {
		try {
			this.exector = executor;
			this.eventNode = eventNode;
			 connectLatch.increment();
			connectLatch.increment();
			this.type = (TwsBrokerType)type;	
		} catch (Exception e) {
			throw new Exception("Cannot Cast To TwsBrokerBean " + e.toString());
		}
		connector = new TwsConnector();
		try {
			connector.getConnectorSocket().addSocketReader(this);
			connector.startConnector(this.type.getHost(), this.type.getPort());	
			connectionTime = DZonedDateTime.now(DTimeZone.NewYork);
		} catch (Exception e) {
			throw new Exception("Exception Connecting " + e.toString());
		}
		setStatus(BrokerStatus.Connected);
		try {
			if(!connectLatch.waitTillZero(30, TimeUnit.SECONDS)) { 
				throw new Exception("CountDownLatch Expired waiting for nextValidId and managedAccounts callback");
			} else { 
				// create instruments 
				instruments = new TwsInstruments(this);
				
				return;
			}
		} catch (InterruptedException e) {
			throw new Exception("Thread Interrupted in connect");
		}
		
	}
	

	@Override
	public DExecutor getExecutor() {
		return exector;
	}



	@Override
	public BrokerAccount[] getAccounts() {
		return accounts.toArray(new BrokerAccount[accounts.size()]);
	}

	@Override
	public String getIdentifier() {
		return type.getIdentifier();
	}


	@Override
	public void disconnect() throws Exception {
		EBrokerDisconnected event = new EBrokerDisconnected(this);
		getEventNode().event(event);
	}
	

	@Override
	public BrokerAccount getAccount(String identifier) throws Exception {
		for (BrokerAccount dAccount : accounts) {
			if(dAccount.getIdentifier().equals(identifier)) { 
				return dAccount;
			}
		}
		throw new Exception("Account " + identifier + " not found");
	}

	
	
	
	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}


	@Override
	public BrokerSpec getSpec() {
		BrokerSpec spec = new BrokerSpec();
		//spec.setConnectionTime(connectionTime);
		//spec.setIdentifer(type.getIdentifier());
		spec.setStatus(getStatus());
		return spec;
	}

	
	@Override
	protected Instrument subscribe(TradeTickerSpec ticker) throws Exception {
		return instruments.subscribe(ticker);
	}


	@Override
	protected void unsubscribe(Instrument instrument) {
		instruments.unsubsribe(instrument);
	}

	public TwsConnector getConnector() { 
		return connector;
	}
	
	@Override
	public void nextValidId(int orderId) {
		if(logger.isTraceEnabled()) { 
			logger.trace("{} NexValidId() {}",type.getIdentifier(),orderId);
		}
		this.nextOrderId = orderId;
		connectLatch.decrement();
	}
	
	
	@Override
	public void managedAccounts(String accountsList) {
		if(logger.isDebugEnabled()) { 
			logger.debug("{} Recieved Managed Accounts {}",type.getIdentifier(),accountsList);
		}
		String accounts[] = accountsList.split(",");
		for (String string : accounts) {
			if(logger.isDebugEnabled()) { 
				logger.debug("{} Creating Account {}",type.getIdentifier(),string);
			}
			TwsAccount account = new TwsAccount(this,string);
			this.accounts.add(account);
		}
		connectLatch.decrement();
		
	}
	
	public int getNextOrderId() { 
		
		nextOrderId++;
		return nextOrderId;
	}


}
