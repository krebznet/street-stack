package com.dunkware.trade.broker.tws.connector;

import java.time.ZonedDateTime;
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

import com.dunkware.trade.broker.api.BrokerStatus;
import com.dunkware.trade.broker.tws.TwsBroker;
import com.dunkware.utils.core.events.DunkEventNode;
import com.dunkware.utils.core.time.DunkTimeZones;
import com.ib.client.Contract;
import com.ib.client.ContractDetails;

/**
 * Hang Out and Program and get this for a few days vacation Build a connector
 * that is extendible with services.
 * 
 * @author dkrebs
 * @since June-Junly 2013
 * @category Halliburton
 */
public class TwsConnector implements TwsSocketReader {

	private Logger _logger = LoggerFactory.getLogger(getClass());

	private TwsConnectorSocket _connectorSocket = null;

	private List<TwsConnectorService> _connectorServices = Collections
			.synchronizedList(new ArrayList<TwsConnectorService>());

	private int _nextValidIdReturnedFromServer = 0;
	private AtomicInteger _nextValidIdFactory = new AtomicInteger(0);

	private int connectionId;
	private String connectionHost; 
	private int connectionPort; 

	private String _managedAccounts = null;

	private Map<String, ContractDetails> _contractDetailsMap = new HashMap<String, ContractDetails>();
	private Semaphore _contractDetailsSemaphore = new Semaphore(1);
	private TimeUpdater _timeUpdater = null;
	private DunkEventNode eventNode;
	
	private ZonedDateTime connectTime = null;
	private ZonedDateTime disconnectTime = null;
	private TwsBroker broker;
	
	private BrokerStatus status = BrokerStatus.Pending;
	
	public TwsConnector() {
		_connectorSocket = new TwsConnectorSocket();
	}
	
	public void setEventNode(DunkEventNode eventNode) { 
		this.eventNode = eventNode;
		
	}
	
	public DunkEventNode getEventNode() { 
		return eventNode;
	}

	public void addConnectorService(TwsConnectorService service) {
		_connectorServices.add(service);
	}

	public void startConnector(TwsBroker broker, String connectorHost, int connectorPort, int connectionId)   {
		this.connectionId =connectionId;
		this.broker = broker;
		this.connectionHost = connectorHost;
		this.connectionPort = connectorPort;
		
		// add this we should get a error on callback?

		_connectorSocket.addSocketReader(this);
		try {
		//	_connectorSocket.connect(this, connectorHost, connectorPort, connectionId);	
			status = BrokerStatus.Connected;
			connectTime = ZonedDateTime.now(DunkTimeZones.zoneNewYork());
		
		} catch (Exception e) {
			status = BrokerStatus.ConnectLoop;
			
		}
		
		

	}

	/**
	 * This called whenever we are connected
	 * @author duncankrebs
	 *
	 */
	private class ConnectHandler implements Runnable, TwsSocketReader { 
		
		public void run() { 
			// we need to get next valid id 
			// 
			
		}
		
		
	}
	
	
	
	

	public void stopConnector() {
		_connectorSocket.disconnect();
	}

	public TwsConnectorService getService(Class clazz) {
		for (TwsConnectorService service : _connectorServices) {
			if (clazz.isInstance(service)) {
				return service;
			}
		}
		return null;
	}
	
	private class TimeUpdater extends Thread {

		public void run() {
			try {
				while (!interrupted()) {
					_connectorSocket.getClientSocket().reqCurrentTime();
					Thread.sleep(1000);

				}
			} catch (Exception e) {

			}

		}
	}

	public TwsConnectorSocket getConnectorSocket() {
		return _connectorSocket;
	}

	
	//------- E SOCKET READER
	@Override
	public void nextValidId(int orderId) {
		if (_logger.isDebugEnabled()) {
			_logger.error("nextValidId " + orderId);
		}
		_nextValidIdReturnedFromServer = orderId;
		_nextValidIdFactory.set(orderId + 500);
	}

	public synchronized int getNextOrderId() {
		return _nextValidIdFactory.incrementAndGet();
	}

	public ContractDetails getContractDetails(String symbol) throws Exception {
		boolean aquired = false;
		try {
			aquired = _contractDetailsSemaphore.tryAcquire(1, TimeUnit.SECONDS);
			ContractDetails details = _contractDetailsMap.get(symbol);
			if (details != null) {
				return details;
			}
		} catch (Exception e) {
			_logger.error("Error looking up contract details " + e.toString());
		} finally {
			if (aquired) {
				_contractDetailsSemaphore.release();
			}

		}
		try {
			Contract contract = new Contract();
			contract.symbol(symbol);
			contract.localSymbol(symbol);
			getConnectorSocket().getClientSocket().reqContractDetails(getNextOrderId(), contract);
			int loopcount = 0;
			while (true) {
				Thread.sleep(100);
				try {
					if (loopcount > 60000) {
						throw new Exception("3 second timeout on ContractDetails Lookup");
					}
					aquired = _contractDetailsSemaphore.tryAcquire(3, TimeUnit.SECONDS);
					ContractDetails details = _contractDetailsMap.get(symbol);
					if (details != null) {
						return details;
					}
					loopcount++;
					if (!aquired) {
						throw new Exception("Semaphore not aquired");
					}
				} catch (Exception e) {
					_logger.error("Error looping for contract details " + e.toString());
				} finally {
					if (aquired) {
						_contractDetailsSemaphore.release();
					}
				}

			}

		} catch (Exception e) {
			throw new Exception("Error getting contract details " + e.toString());
		}

	}

	@Override
	public void contractDetails(int reqId, ContractDetails contractDetails) {
		try {
			boolean aquired = _contractDetailsSemaphore.tryAcquire(3, TimeUnit.SECONDS);
			if (!aquired) {
				_logger.error("Contract Detail Semaphore not aquired in 3 seconds");
				return;
			}
			_contractDetailsMap.put(contractDetails.underSymbol(), contractDetails);
		} catch (Exception e) {
			_logger.error("error getting contract details semaphore " + e.toString());
		} finally {
			_contractDetailsSemaphore.release();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kntrade.tdk.broker.tws.runtime.connector.TwsSocketReader#
	 * contractDetailsEnd(int)
	 */
	@Override
	public void contractDetailsEnd(int reqId) {

	}

	@Override
	public void updateAccountTime(String timeStamp) {

	}

	@Override
	public void managedAccounts(String accountsList) {
		if (_managedAccounts == null) {
			_managedAccounts = accountsList;
			String[] accounts = accountsList.split(",");
			_connectorSocket.getClientSocket().reqAccountUpdates(true, accounts[0]);
		}
	}

	

}
