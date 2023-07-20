package com.dunkware.trade.broker.tws.connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.broker.tws.TwsBroker;
import com.dunkware.trade.sdk.core.model.broker.BrokerStatus;
import com.dunkware.trade.sdk.core.runtime.broker.event.EBrokerConnectFail;
import com.dunkware.trade.sdk.core.runtime.broker.event.EBrokerConnected;
import com.ib.client.Bar;
import com.ib.client.CommissionReport;
import com.ib.client.Contract;
import com.ib.client.ContractDescription;
import com.ib.client.ContractDetails;
import com.ib.client.Decimal;
import com.ib.client.DeltaNeutralContract;
import com.ib.client.DepthMktDataDescription;
import com.ib.client.EClientSocket;
import com.ib.client.EReaderSignal;
import com.ib.client.EWrapper;
import com.ib.client.Execution;
import com.ib.client.FamilyCode;
import com.ib.client.HistogramEntry;
import com.ib.client.HistoricalSession;
import com.ib.client.HistoricalTick;
import com.ib.client.HistoricalTickBidAsk;
import com.ib.client.HistoricalTickLast;
import com.ib.client.NewsProvider;
import com.ib.client.PriceIncrement;
import com.ib.client.SoftDollarTier;
import com.ib.client.TickAttrib;
import com.ib.client.TickAttribBidAsk;
import com.ib.client.TickAttribLast;
import com.ib.client.TickType;
import com.ib.client.TwsOrder;
import com.ib.client.TwsOrderState;

/**
 * Wrapper i think for multi-threading performance?
 * 
 * @author dkrebs
 * @since June 16, 2013
 * @category Halliburton
 */
public class TwsConnectorSocket implements EWrapper, EReaderSignal {

	private Logger _logger = LoggerFactory.getLogger(getClass());

	private EClientSocket m_client = null;

	private String _twsHost;

	private int _twsPort;

	private int _twsConnectionId;
	
	ExecutorService _pool;

	private List<TwsSocketReader> connectorHandlers = new ArrayList<TwsSocketReader>();

	private Semaphore _handlersSemaphore = new Semaphore(1);
	
	private TwsBroker broker = null;


	public TwsConnectorSocket() {
		_pool = Executors.newFixedThreadPool(10);


	}

	public void connect(TwsBroker broker, final String host,
			final int port, final int connectionId)  {
			this.broker = broker;
			m_client = new EClientSocket(this, this);
			_twsHost = host;
			_twsPort = port;
			_twsConnectionId = connectionId;
			m_client.eConnect(_twsHost, _twsPort, _twsConnectionId);
	}
	
	public boolean isConnected() { 
		return m_client.isConnected();
	}
	
	
	public void disconnect() {
		m_client.eDisconnect();
		
	}

	public EClientSocket getClientSocket() {
		return m_client;
	}

	public void addSocketReader(final TwsSocketReader reader) {
		Thread runner = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					
					_handlersSemaphore.acquire();
					try {
						if(_logger.isDebugEnabled()) {
							_logger.debug("Adding Socket Reader " + reader.getClass().getName());
						}
						connectorHandlers.add(reader);
					} catch (Exception e) {
						_logger.error("add socket reader issues " + e.toString());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					_handlersSemaphore.release();
				}
				
			}
		});
		runner.start();
	

	}

	public void removeSocketReader(final TwsSocketReader reader) {
		Thread runner = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					connectorHandlers.remove(reader);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					_handlersSemaphore.release();
				}
			}
		});
		runner.start();
		
		
		
		
	}
	
	

	@Override
	public void issueSignal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waitForSignal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(final Exception e) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("error(" + "," + e.getClass().getName() + " "
					+ e.toString());
		}
		_logger.error("Exception Error " + e.toString());
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.error(e);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void error(final String str) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("error(" + "," + str + "," + ")");
		}
		_logger.error("Error String" + str);
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.error(str);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});

	}

	
	@Override
	public void positionEnd() {
		if (_logger.isDebugEnabled()) {
			_logger.debug("positionEnd()");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.positionEnd();
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});

	}

	@Override
	public void accountSummary(final int reqId, final String account,
			final String tag, final String value, final String currency) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("accountSummary(" + "," + reqId + "," + account + ","
					+ tag + "," + value + "," + currency + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.accountSummary(reqId, account, tag, value,
								currency);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});

	}

	@Override
	public void accountSummaryEnd(final int reqId) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("accountSummaryEnd()");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.accountSummaryEnd(reqId);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	

	@Override
	public void connectionClosed() {
		for (TwsSocketReader handler : connectorHandlers) {
			handler.connectionClosed();
		}

	}

	@Override
	public void nextValidId(final int orderId) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("nextValidId(" + "," + orderId + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.nextValidId(orderId);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	
	

	public void tickGeneric(final int tickerId, final int tickType,
			final double value) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("tickGeneric(" + tickerId + "." + tickType + ","
					+ value + ")" + " " + TickType.getField(tickType));
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.tickGeneric(tickerId, tickType, value);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});

	}

	@Override
	public void tickString(final int tickerId, final int tickType,
			final String value) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("tickString(" + tickerId + "," + tickType + ","
					+ value + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.tickString(tickerId, tickType, value);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ib.client.EWrapper#tickEFP(int, int, final double,
	 * java.lang.final String, final double, int, java.lang.final String, final
	 * double, final double)
	 */
	@Override
	public void tickEFP(final int tickerId, final int tickType,
			final double basisPoints, final String formattedBasisPoints,
			final double impliedFuture, final int holdDays,
			final String futureExpiry, final double dividendImpact,
			final double dividendsToExpiry) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("tickEFP(" + "," + tickerId + "," + tickerId + ","
					+ basisPoints + "," + formattedBasisPoints + ","
					+ impliedFuture + "," + holdDays + "," + futureExpiry + ","
					+ dividendImpact + "," + dividendsToExpiry + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.tickEFP(tickerId, tickType, basisPoints,
								formattedBasisPoints, impliedFuture, holdDays,
								futureExpiry, dividendImpact, dividendsToExpiry);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});

	}

	

	@Override
	public void openOrder(final int orderId, final Contract contract,
			final TwsOrder order, final TwsOrderState orderState) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("openOrder(" + orderId + "," + contract.symbol()
					+ "," + order.orderId() + "," + orderState.toString() + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.openOrder(orderId, contract, order, orderState);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});

	}

	@Override
	public void openOrderEnd() {
		if (_logger.isDebugEnabled()) {
			_logger.debug("openOrderEnd()");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.openOrderEnd();
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});

	}

	@Override
	public void updateAccountValue(final String key, final String value,
			final String currency, final String accountName) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("updateAccountValue(" + key + "," + value + ","
					+ currency + "," + accountName + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.updateAccountValue(key, value, currency,
								accountName);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	

	@Override
	public void updateAccountTime(final String timeStamp) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("updateAccountTime(" + timeStamp + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.updateAccountTime(timeStamp);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void accountDownloadEnd(final String accountName) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("accountDownloadEnd(" + accountName + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {

					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void contractDetails(final int reqId,
			final ContractDetails contractDetails) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("contractDetails(" + reqId + ","
					+ contractDetails.underSymbol() + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.contractDetails(reqId, contractDetails);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});

	}

	@Override
	public void bondContractDetails(final int reqId,
			final ContractDetails contractDetails) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("bondContractDetails(" + reqId + ","
					+ contractDetails.underSymbol() + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.bondContractDetails(reqId, contractDetails);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void contractDetailsEnd(final int reqId) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("contractDetailsEnd()");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.contractDetailsEnd(reqId);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void execDetails(final int reqId, final Contract contract,
			final Execution execution) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("execDetails(" + reqId + "," + contract.symbol()
					+ "," + execution.orderId() + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.execDetails(reqId, contract, execution);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void execDetailsEnd(final int reqId) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("execDetailsEnd(" + reqId + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.execDetailsEnd(reqId);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	

	@Override
	public void updateNewsBulletin(final int msgId, final int msgType,
			final String message, final String origExchange) {
		
		if (_logger.isDebugEnabled()) {
			_logger.debug("updateNewsBulletin()");
			
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.updateNewsBulletin(msgId, msgType, message,
								origExchange);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void managedAccounts(final String accountsList) {
		if (_logger.isDebugEnabled()) {

			_logger.debug("managedAccounts(" + accountsList +")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.managedAccounts(accountsList);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void receiveFA(final int faDataType, final String xml) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("recieveFA(" + faDataType + "," + xml + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.receiveFA(faDataType, xml);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});

	}

	
	@Override
	public void scannerParameters(final String xml) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("scannerParameters(" + xml + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.scannerParameters(xml);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});

	}

	@Override
	public void scannerData(final int reqId, final int rank,
			final ContractDetails contractDetails, final String distance,
			final String benchmark, final String projection,
			final String legsStr) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("scannerData(" + reqId + "," +  rank + "," +  contractDetails.underSymbol() + "," +  distance + "," +  benchmark + "," +  projection + "," +  legsStr + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.scannerData(reqId, rank, contractDetails,
								distance, benchmark, projection, legsStr);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void scannerDataEnd(final int reqId) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("scannerDataEnd(" +  reqId + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.scannerDataEnd(reqId);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

		

	@Override
	public void currentTime(final long time) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("currentTime(" + time + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.currentTime(time);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void fundamentalData(final int reqId, final String data) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("fundamentalData(" + reqId + "," +  data + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.fundamentalData(reqId, data);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});

	}




	@Override
	public void tickSnapshotEnd(final int reqId) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("tickSnapshotEnd(" + reqId + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.tickSnapshotEnd(reqId);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});

	}

	@Override
	public void marketDataType(final int reqId, final int marketDataType) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("marketDataType(" + reqId + "," +  marketDataType + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.marketDataType(reqId, marketDataType);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});

	}

	@Override
	public void commissionReport(final CommissionReport commissionReport) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("comissionReport(" + "comission=" + commissionReport.commission() + "," +  commissionReport.execId() + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.commissionReport(commissionReport);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void tickPrice(int tickerId, int field, double price, TickAttrib attrib) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.tickPrice(tickerId, field, price,attrib);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void tickSize(int tickerId, int field, Decimal size) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.tickSize(tickerId, field, size);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void tickOptionComputation(int tickerId, int field, int tickAttrib, double impliedVol, double delta,
			double optPrice, double pvDividend, double gamma, double vega, double theta, double undPrice) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.tickOptionComputation(tickerId, field, tickAttrib, impliedVol, delta, optPrice, pvDividend, gamma, vega, theta, undPrice);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void orderStatus(int orderId, String status, Decimal filled, Decimal remaining, double avgFillPrice,
			int permId, int parentId, double lastFillPrice, int clientId, String whyHeld, double mktCapPrice) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.orderStatus(orderId, status, filled, remaining, avgFillPrice, permId, parentId, lastFillPrice, clientId, whyHeld, mktCapPrice);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void updatePortfolio(Contract contract, Decimal position, double marketPrice, double marketValue,
			double averageCost, double unrealizedPNL, double realizedPNL, String accountName) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.updatePortfolio(contract, position, marketPrice, marketValue, averageCost, unrealizedPNL, realizedPNL, accountName);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void updateMktDepth(int tickerId, int position, int operation, int side, double price, Decimal size) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.updateMktDepth(tickerId, position, operation, side, price, size);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void updateMktDepthL2(int tickerId, int position, String marketMaker, int operation, int side, double price,
			Decimal size, boolean isSmartDepth) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.updateMktDepthL2(tickerId, position, marketMaker, operation, side, price, size, isSmartDepth);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void historicalData(int reqId, Bar bar) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.historicalDataEnd(reqId, _twsHost, _twsHost);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void realtimeBar(int reqId, long time, double open, double high, double low, double close, Decimal volume,
			Decimal wap, int count) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.realtimeBar(reqId, time, open, high, low, close, volume, wap, count);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void deltaNeutralValidation(int reqId, DeltaNeutralContract deltaNeutralContract) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.deltaNeutralValidation(reqId, deltaNeutralContract);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void position(String account, Contract contract, Decimal pos, double avgCost) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.position(account, contract, pos, avgCost);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void verifyMessageAPI(String apiData) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.verifyMessageAPI(apiData);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void verifyCompleted(boolean isSuccessful, String errorText) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.verifyCompleted(isSuccessful, errorText);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void verifyAndAuthMessageAPI(String apiData, String xyzChallenge) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.verifyAndAuthMessageAPI(apiData, xyzChallenge);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void verifyAndAuthCompleted(boolean isSuccessful, String errorText) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.verifyAndAuthMessageAPI(errorText, errorText);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void displayGroupList(int reqId, String groups) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.displayGroupList(reqId, groups);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void displayGroupUpdated(int reqId, String contractInfo) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.displayGroupUpdated(reqId, contractInfo);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void error(int id, int errorCode, String errorMsg, String advancedOrderRejectJson) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.error(id, errorCode, errorMsg, advancedOrderRejectJson);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void connectAck() {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.connectAck();
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void positionMulti(int reqId, String account, String modelCode, Contract contract, Decimal pos,
			double avgCost) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.positionMulti(reqId, account, modelCode, contract, pos, avgCost);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void positionMultiEnd(int reqId) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.positionMultiEnd(reqId);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void accountUpdateMulti(int reqId, String account, String modelCode, String key, String value,
			String currency) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.accountUpdateMulti(reqId, account, modelCode, key, value, currency);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void accountUpdateMultiEnd(int reqId) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.accountUpdateMultiEnd(reqId);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void securityDefinitionOptionalParameter(int reqId, String exchange, int underlyingConId,
			String tradingClass, String multiplier, Set<String> expirations, Set<Double> strikes) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.securityDefinitionOptionalParameter(reqId, exchange, underlyingConId, tradingClass, multiplier, expirations, strikes);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void securityDefinitionOptionalParameterEnd(int reqId) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.securityDefinitionOptionalParameterEnd(reqId);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void softDollarTiers(int reqId, SoftDollarTier[] tiers) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.softDollarTiers(reqId, tiers);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void familyCodes(FamilyCode[] familyCodes) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.familyCodes(familyCodes);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void symbolSamples(int reqId, ContractDescription[] contractDescriptions) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.symbolSamples(reqId, contractDescriptions);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void historicalDataEnd(int reqId, String startDateStr, String endDateStr) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.historicalDataEnd(reqId, startDateStr, endDateStr);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void mktDepthExchanges(DepthMktDataDescription[] depthMktDataDescriptions) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.mktDepthExchanges(depthMktDataDescriptions);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void tickNews(int tickerId, long timeStamp, String providerCode, String articleId, String headline,
			String extraData) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.tickNews(tickerId, timeStamp, providerCode, articleId, headline, extraData);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void smartComponents(int reqId, Map<Integer, Entry<String, Character>> theMap) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.smartComponents(reqId, theMap);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void tickReqParams(int tickerId, double minTick, String bboExchange, int snapshotPermissions) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.tickReqParams(tickerId, minTick, bboExchange, snapshotPermissions);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void newsProviders(NewsProvider[] newsProviders) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.newsProviders(newsProviders);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void newsArticle(int requestId, int articleType, String articleText) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.newsArticle(requestId, articleType, articleText);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void historicalNews(int requestId, String time, String providerCode, String articleId, String headline) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.historicalNews(requestId, time, providerCode, articleId, headline);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void historicalNewsEnd(int requestId, boolean hasMore) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.historicalNews(requestId, _twsHost, _twsHost, _twsHost, _twsHost);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void headTimestamp(int reqId, String headTimestamp) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.headTimestamp(reqId, headTimestamp);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void histogramData(int reqId, List<HistogramEntry> items) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.histogramData(reqId, items);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void historicalDataUpdate(int reqId, Bar bar) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.historicalDataUpdate(reqId, bar);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void rerouteMktDataReq(int reqId, int conId, String exchange) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.rerouteMktDataReq(reqId, conId, exchange);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void rerouteMktDepthReq(int reqId, int conId, String exchange) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.rerouteMktDataReq(reqId, conId, exchange);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});	
	}

	@Override
	public void marketRule(int marketRuleId, PriceIncrement[] priceIncrements) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.marketDataType(marketRuleId, marketRuleId);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void pnl(int reqId, double dailyPnL, double unrealizedPnL, double realizedPnL) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.pnl(reqId, dailyPnL, unrealizedPnL, realizedPnL);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void pnlSingle(int reqId, Decimal pos, double dailyPnL, double unrealizedPnL, double realizedPnL,
			double value) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.pnl(reqId, dailyPnL, unrealizedPnL, realizedPnL);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});	
	}
		


	@Override
	public void historicalTicks(int reqId, List<HistoricalTick> ticks, boolean done) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.historicalTicks(reqId, ticks, done);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});	
	}

	@Override
	public void historicalTicksBidAsk(int reqId, List<HistoricalTickBidAsk> ticks, boolean done) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.historicalTicksBidAsk(reqId, ticks, done);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});	
	}

	@Override
	public void historicalTicksLast(int reqId, List<HistoricalTickLast> ticks, boolean done) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.historicalTicksLast(reqId, ticks, done);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});	
	}

	@Override
	public void tickByTickAllLast(int reqId, int tickType, long time, double price, Decimal size,
			TickAttribLast tickAttribLast, String exchange, String specialConditions) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.tickByTickAllLast(reqId, tickType, time, price, size, tickAttribLast, exchange, specialConditions);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});	
	}

	@Override
	public void tickByTickBidAsk(int reqId, long time, double bidPrice, double askPrice, Decimal bidSize,
			Decimal askSize, TickAttribBidAsk tickAttribBidAsk) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.tickByTickBidAsk(reqId, time, bidPrice, askPrice, bidSize, askSize, tickAttribBidAsk);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void tickByTickMidPoint(int reqId, long time, double midPoint) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.tickByTickMidPoint(reqId, time, midPoint);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void orderBound(long orderId, int apiClientId, int apiOrderId) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.orderBound(orderId, apiClientId, apiOrderId);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void completedOrder(Contract contract, TwsOrder order, TwsOrderState orderState) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.completedOrder(contract, order, orderState);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});	
	}

	@Override
	public void completedOrdersEnd() {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.completedOrdersEnd();
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void replaceFAEnd(int reqId, String text) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.replaceFAEnd(reqId, text);;
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	}

	@Override
	public void wshMetaData(int reqId, String dataJson) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.wshMetaData(reqId, dataJson);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void wshEventData(int reqId, String dataJson) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.wshEventData(reqId, dataJson);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void historicalSchedule(int reqId, String startDateTime, String endDateTime, String timeZone,
			List<HistoricalSession> sessions) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.historicalSchedule(reqId, startDateTime, endDateTime, timeZone, sessions);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
		
	}

	@Override
	public void userInfo(int reqId, String whiteBrandingId) {
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.userInfo(reqId, whiteBrandingId);
					}
				} catch (Exception e) {
					_logger.error("Error invoking handler " + e.toString());

				} finally {
					_handlersSemaphore.release();
				}

			}
		});
	
	}

}
