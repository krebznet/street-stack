package com.dunkware.trade.broker.tws;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.broker.api.Account;
import com.dunkware.trade.broker.api.Broker;
import com.dunkware.trade.broker.api.BrokerBean;
import com.dunkware.trade.broker.api.BrokerException;
import com.dunkware.trade.broker.api.BrokerStatus;
import com.dunkware.trade.broker.api.event.EBrokerBeanUpdate;
import com.dunkware.trade.broker.api.event.EBrokerDisconnected;
import com.dunkware.trade.broker.api.event.EBrokerException;
import com.dunkware.trade.broker.api.event.EBrokerInitialized;
import com.dunkware.trade.broker.tws.connector.TwsConnectorService;
import com.dunkware.trade.broker.tws.connector.TwsConnectorSocket;
import com.dunkware.trade.broker.tws.connector.TwsSocketReader;
import com.dunkware.trade.broker.tws.instrument.TwsInstruments;
import com.dunkware.utils.core.concurrent.DunkExecutor;
import com.dunkware.utils.core.concurrent.DunkExecutorCountLatch;
import com.dunkware.utils.core.events.DunkEventNode;
import com.ib.client.ContractDetails;
import com.ib.client.EClientSocket;



public class TwsBroker implements Broker , TwsSocketReader {

	public static AtomicInteger CLIENTID = new AtomicInteger(1); 
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private TwsBrokerType type;

	private List<Account> accounts = new ArrayList<Account>();

	private volatile int nextOrderId;

	private ZonedDateTime connectionTime = null;

	private DunkExecutorCountLatch connectLatch = new DunkExecutorCountLatch();

	private DunkEventNode eventNode;

	private TwsInstruments instruments;

	private String exception = null;

	private BrokerStatus status = BrokerStatus.Pending;

	private DunkExecutor exector;

	private BrokerBean bean = null;

	private TwsConnectorSocket _connectorSocket = null;

	private Map<String, ContractDetails> _contractDetailsMap = new HashMap<String, ContractDetails>();
	private Semaphore _contractDetailsSemaphore = new Semaphore(1);

	private List<TwsConnectorService> _connectorServices = Collections
			.synchronizedList(new ArrayList<TwsConnectorService>());

	private int _nextValidIdReturnedFromServer = 0;
	private AtomicInteger _nextValidIdFactory = new AtomicInteger(0);

	private volatile boolean hasConnected = false;
	private volatile boolean pendingConnect = true;
	private volatile boolean pendingingConnectNextId = true;
	private volatile boolean pendingConnectAccounts = true;


	private TwsBrokerType brokerType; 
	private boolean closed = false;


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
	
	
	public TwsBroker() { 
		bean = new BrokerBean();
		this.status = BrokerStatus.Pending;
		bean.setStatus(status.name());
	}
	
	@Override
	public void connect(Object brokerType, DunkEventNode eventNode, DunkExecutor executor)  {
		this.type =  (TwsBrokerType)brokerType;
		
		this.eventNode = eventNode; 
		this.exector = executor; 
		this.status = BrokerStatus.Connecting;
		
		try {
			this.exector = executor;
			this.eventNode = eventNode;
			this.eventNode.adDunkEventHandler(this);
			connectLatch.increment();
			this.type = type; 
			this.type.setConnectionId(CLIENTID.incrementAndGet());
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

	@Override
	public BrokerStatus getStatus() {
		return status; 
	}

	@Override
	public DunkEventNode getEventNode() {
		return eventNode; 
	}


	public void setStatus(BrokerStatus status) {
		this.status = status;
		bean.setStatus(status.name());
		notifyBeanUpdate();
	}
	
	@Override
	public List<Account> getAccounts() {
		return accounts; 
	}

	@Override
	public DunkExecutor getExecutor() {
		return exector;
	}

	

	@Override
	public String getException() {
		return exception;
	}

	

	@Override
	public String getIdentifier() {
		return type.getIdentifier();
	}

	@Override
	public void disconnect()   {
		
		EBrokerDisconnected event = new EBrokerDisconnected(this);
		getEventNode().event(event);
	}

	@Override
	public Account getAccount(String identifier) throws BrokerException  {
		for (Account dAccount : accounts) {
			if (dAccount.getIdentifier().equals(identifier)) {
				return dAccount;
			}
		}
		throw new BrokerException("Tws Account " + identifier + " not found");
		
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
		closed = true;
		if (logger.isInfoEnabled()) {
			logger.info("Connection Closed Event");
		}
	}

	@Override
	public void error(int id, int errorCode, String errorMsg, String advancedOrderRejectJson) {
		// okay so -- if e cannot connect then what do we do 
		
		logger.debug("error " + id + " " + errorCode + " " + errorMsg + " " + advancedOrderRejectJson);
		_connectorSocket.getClientSocket().eDisconnect();
		// TODO Auto-generated method stub
		// EClientErrors.NOT_CONNECTED.c

		// TwsSocketReader.super.error(id, errorCode, errorMsg,
		// advancedOrderRejectJson);
	}

	/**
	 * EEEEREEADER EVENTS 
	 */
	

	@Override
	public void nextValidId(int orderId) {
		if (logger.isTraceEnabled()) {
			logger.trace("{} NexValidId() {}", type.getIdentifier(), orderId);
		}
		this.nextOrderId = orderId;
		if (pendingConnect) {
			pendingingConnectNextId = false;
			if (pendingConnectAccounts == false) {
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
			if (hasConnected == false) {
				TwsAccount account = new TwsAccount(this, string);
			//	account.st
				this.accounts.add(account);
			}
			if (pendingConnect) {
				pendingConnectAccounts = false;
				if (pendingingConnectNextId == false) {
					hasConnected = true;
					pendingConnect = false;
					setStatus(BrokerStatus.Connected);
					eventNode.event(new EBrokerInitialized(this));
				}
			}

		}

	}

	
	@Override
	public void connectAck() {
		Runnable runner = new Runnable() {
			
			@Override
			public void run() {
				try {
					pendingConnect = true;
				//	getClientSocket().reqManagedAccts();
				//	getClientSocket().reqIds(0);
					
					logger.info("connect act");

				} catch (Exception e) {
					logger.error("Fuck " + e.toString());
				}
			}
		};
		exector.execute(runner);

	}

	


}
