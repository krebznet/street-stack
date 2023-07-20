package com.dunkware.trade.broker.tws;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.concurrency.ReusableCountDownLatch;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.dtime.DZonedDateTime;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.helpers.DDateTimeHelper;
import com.dunkware.trade.broker.tws.connector.TwsConnector;
import com.dunkware.trade.broker.tws.connector.TwsConnectorService;
import com.dunkware.trade.broker.tws.connector.TwsConnectorSocket;
import com.dunkware.trade.broker.tws.connector.TwsSocketReader;
import com.dunkware.trade.broker.tws.instrument.TwsInstruments;
import com.dunkware.trade.sdk.core.model.broker.BrokerBean;
import com.dunkware.trade.sdk.core.model.broker.BrokerSpec;
import com.dunkware.trade.sdk.core.model.broker.BrokerStatus;
import com.dunkware.trade.sdk.core.model.broker.BrokerType;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.sdk.core.runtime.broker.anot.ABroker;
import com.dunkware.trade.sdk.core.runtime.broker.event.EBrokerBeanUpdate;
import com.dunkware.trade.sdk.core.runtime.broker.event.EBrokerDisconnected;
import com.dunkware.trade.sdk.core.runtime.broker.event.EBrokerException;
import com.dunkware.trade.sdk.core.runtime.broker.event.EBrokerInitialized;
import com.dunkware.trade.sdk.core.runtime.broker.impl.BrokerImpl;
import com.dunkware.trade.tick.api.instrument.Instrument;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.ib.client.ContractDetails;
import com.ib.client.EClientErrors;
import com.ib.client.EClientSocket;

@ABroker(type = TwsBrokerType.class)
public class TwsBroker extends BrokerImpl implements TwsSocketReader {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private TwsBrokerType type;

	private List<BrokerAccount> accounts = new ArrayList<BrokerAccount>();

	private volatile int nextOrderId;

	private DZonedDateTime connectionTime = null;

	private ReusableCountDownLatch connectLatch = new ReusableCountDownLatch();

	private DEventNode eventNode;

	private TwsInstruments instruments;

	private String exception = null;

	private BrokerStatus status = BrokerStatus.Pending;

	private DExecutor exector;

	private BrokerBean bean = null;

	private TwsConnectorSocket _connectorSocket = null;

	private Map<String, ContractDetails> _contractDetailsMap = new HashMap<String, ContractDetails>();
	private Semaphore _contractDetailsSemaphore = new Semaphore(1);

	private List<TwsConnectorService> _connectorServices = Collections
			.synchronizedList(new ArrayList<TwsConnectorService>());

	private int _nextValidIdReturnedFromServer = 0;
	private AtomicInteger _nextValidIdFactory = new AtomicInteger(0);

	private boolean hasConnected = false;
	private boolean pendingConnect = true;
	private boolean pendingingConnectNextId = false;
	private boolean pendingConnectAccounts = false;
	
	private boolean connectionClosed = false;

	public TwsBroker() {
		bean = new BrokerBean();
		bean.setStatus(BrokerStatus.Pending.name());
	}

	public BrokerBean getBrokerBean() {
		return bean;
	}
	
	public void addSocketReader(TwsSocketReader reader) { 
		_connectorSocket.addSocketReader(reader);
	}
	
	public void removeSocketReader(TwsSocketReader reader) { 
		_connectorSocket.removeSocketReader(reader);
	}
	
	public EClientSocket getClientSocket() { 
		return _connectorSocket.getClientSocket();
	}

	@Override
	public void connect(BrokerType type, DEventNode eventNode, DExecutor executor) {
		try {
			this.exector = executor;
			this.eventNode = eventNode;
			connectLatch.increment();
			this.type = (TwsBrokerType) type;
		} catch (Exception e) {
			exception = e.toString();
			status = BrokerStatus.Exception;
			bean.setStatus(status.name());
			notifyBeanUpdate();
			eventNode.event(new EBrokerException(this, "Cast Type " + e.toString()));
			return;
		}
		_connectorSocket = new TwsConnectorSocket();
		_connectorSocket.addSocketReader(this);
		setStatus(BrokerStatus.Connecting);
		pendingConnect = true;
		pendingConnectAccounts = true;
		pendingingConnectNextId = true;
		_connectorSocket.connect(this, this.type.getHost(), this.type.getPort(), this.type.getConnectionId());

	}

	public void setStatus(BrokerStatus status) {
		this.status = status;
		bean.setStatus(status.name());
		notifyBeanUpdate();
	}

	@Override
	public DExecutor getExecutor() {
		return exector;
	}

	@Override
	public String getException() {
		return exception;
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
			if (dAccount.getIdentifier().equals(identifier)) {
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
	protected Instrument subscribe(TradeTickerSpec ticker) throws Exception {
		return instruments.subscribe(ticker);
	}

	@Override
	protected void unsubscribe(Instrument instrument) {
		instruments.unsubsribe(instrument);
	}

	@Override
	public void nextValidId(int orderId) {
		if (logger.isTraceEnabled()) {
			logger.trace("{} NexValidId() {}", type.getIdentifier(), orderId);
		}
		this.nextOrderId = orderId;
		if (pendingConnect) {
			pendingingConnectNextId = false;
			if (pendingConnectAccounts = false) {
				hasConnected = true;
				pendingConnect = false;
				setStatus(BrokerStatus.Connected);
				eventNode.event(new EBrokerInitialized(this));
			}
		}

	}

	@Override
	public void managedAccounts(String accountsList) {
		if (logger.isDebugEnabled()) {
			logger.debug("{} Recieved Managed Accounts {}", type.getIdentifier(), accountsList);
		}
		String accounts[] = accountsList.split(",");
		for (String string : accounts) {
			if (logger.isDebugEnabled()) {
				logger.debug("{} Creating Account {}", type.getIdentifier(), string);
			}
			if(hasConnected == false) { 
				TwsAccount account = new TwsAccount(this, string);
				this.accounts.add(account);	
			} 
			if (pendingConnect) {
				pendingConnectAccounts = false;
				if (pendingingConnectNextId = false) {
					hasConnected = true;
					pendingConnect = false;
					setStatus(BrokerStatus.Connected);
					eventNode.event(new EBrokerInitialized(this));
				}
			}
		
		}
		
		

	}

	public int getNextOrderId() {
		nextOrderId++;
		return nextOrderId;
	}

	private void notifyBeanUpdate() {
		eventNode.event(new EBrokerBeanUpdate(this));
	}

	public boolean isConnected() {
		if (status == BrokerStatus.Connected) {
			return true;
		}
		return false;
	}

	/**
	 * Keeps trying to connect every few seconds while not connected.
	 * 
	 * @author duncankrebs
	 *
	 */
	private class ConnectLoop extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					Thread.sleep(4000);
					if (_connectorSocket.getClientSocket().isConnected() == false) {
					
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}
	}


	/**
	 * Event Handler Methods
	 */

	/**
	 * ESocketReader Methods
	 */

	@Override
	public void error(Exception e) {
		// not sure;
	}

	@Override
	public void connectionClosed() {
		connectionClosed = true;
		if(logger.isInfoEnabled()) { 
			logger.info("Connection Closed Event");
		}
	}
	
	
	
	

	@Override
	public void error(int id, int errorCode, String errorMsg, String advancedOrderRejectJson) {
		 
		
		// TODO Auto-generated method stub
		//EClientErrors.NOT_CONNECTED.c
		
		//TwsSocketReader.super.error(id, errorCode, errorMsg, advancedOrderRejectJson);
	}

	@Override
	public void connectAck() {
		logger.info("connect act");
		
	}

}
