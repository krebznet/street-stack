package com.dunkware.trade.broker.tws.connector;

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

import com.dunkware.common.util.helpers.DRandom;
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

	private int connectionId = DRandom.getRandom(1, 40000);

	private String _managedAccounts = null;

	private Map<String, ContractDetails> _contractDetailsMap = new HashMap<String, ContractDetails>();
	private Semaphore _contractDetailsSemaphore = new Semaphore(1);
	private TimeUpdater _timeUpdater = null;

	public TwsConnector() {
		_connectorSocket = new TwsConnectorSocket();
	}

	public void addConnectorService(TwsConnectorService service) {
		_connectorServices.add(service);
	}

	public void startConnector(String connectorHost, int connectorPort) throws Exception {

		// add this we should get a error on callback?

		_connectorSocket.addSocketReader(this);
		_connectorSocket.connect(this, connectorHost, connectorPort, connectionId);
		Thread delay = new Thread() {
			public void run() {
				try {
					Thread.sleep(1000);
					getConnectorSocket().getClientSocket().reqAccountSummary(_nextValidIdFactory.incrementAndGet(),
							"All", "AccountType");

					try {
						_timeUpdater = new TimeUpdater();
						_timeUpdater.start();

					} catch (Exception e) {
						throw new Exception("Error connecting to tws " + e.toString());

					}
				} catch (Exception e) {
					_logger.error("Uncaught exception " + e.toString());
				}
			}
		};
		delay.start();

		// init services
		for (TwsConnectorService service : _connectorServices) {
			service.initService(this);
		}
		for (TwsConnectorService service : _connectorServices) {
			service.startService();
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

	public TwsConnectorSocket getConnectorSocket() {
		return _connectorSocket;
	}

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
			contract.m_symbol = symbol;
			contract.m_localSymbol = symbol;
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
			_contractDetailsMap.put(contractDetails.m_summary.m_symbol, contractDetails);
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

}
