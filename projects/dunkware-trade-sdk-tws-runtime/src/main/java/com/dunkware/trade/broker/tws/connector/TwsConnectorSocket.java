package com.dunkware.trade.broker.tws.connector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ib.client.CommissionReport;
import com.ib.client.Contract;
import com.ib.client.ContractDetails;
import com.ib.client.EClientSocket;
import com.ib.client.EWrapper;
import com.ib.client.Execution;
import com.ib.client.TickType;
import com.ib.client.TwsOrder;
import com.ib.client.TwsOrderState;
import com.ib.client.UnderComp;

/**
 * Wrapper i think for multi-threading performance?
 * 
 * @author dkrebs
 * @since June 16, 2013
 * @category Halliburton
 */
public class TwsConnectorSocket implements EWrapper {

	private Logger _logger = LoggerFactory.getLogger(getClass());

	private EClientSocket m_client = new EClientSocket(this);

	private TwsConnector _twsConnector;

	private String _twsHost;

	private int _twsPort;

	private int _twsConnectionId;

	ExecutorService _pool;

	private List<TwsSocketReader> connectorHandlers = new ArrayList<TwsSocketReader>();

	private Semaphore _handlersSemaphore = new Semaphore(1);
	


	public TwsConnectorSocket() {
		_pool = Executors.newFixedThreadPool(10);


	}

	public void connect(TwsConnector twsConnector, final String host,
			final int port, final int connectionId) throws Exception {
		try {
			m_client = new EClientSocket(this);
			_twsConnector = twsConnector;
			_twsHost = host;
			_twsPort = port;
			_twsConnectionId = connectionId;

			m_client.eConnect(_twsHost, _twsPort, _twsConnectionId);
		} catch (Exception e) {
			throw e;
		}

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
	public void position(final String account, final Contract contract,
			final int pos, final double avgCost) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("position(" + "," + account + "," + contract.m_symbol
					+ "," + pos + "," + avgCost);
		}
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
	public void error(final int id, final int errorCode, final String errorMsg) {
		_logger.debug("error(" + "," + id + "," + errorCode + ","
				+ errorMsg + ")");
		if (_logger.isDebugEnabled()) {
			_logger.debug("error(" + "," + id + "," + errorCode + ","
					+ errorMsg + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.error(id, errorCode, errorMsg);
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

	@Override
	public void tickPrice(final int tickerId, final int field,
			final double price, final int canAutoExecute) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("tickPrice(" + "," + tickerId + "," + field + ","
					+ price + ") " + TickType.getField(field));
			
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.tickPrice(tickerId, field, price, canAutoExecute);
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
	public void tickSize(final int tickerId, final int field, final int size) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("tickSize(" + "," + tickerId + "," + field + ","
					+ size + ")" + " " + TickType.getField(field));
		}
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
	public void tickOptionComputation(final int tickerId, final int field,
			final double impliedVol, final double delta, final double optPrice,
			final double pvDividend, final double gamma, final double vega,
			final double theta, final double undPrice) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("tickOptionComputation(" + "," + tickerId + ","
					+ field + "," + impliedVol + "," + delta + "," + optPrice
					+ "," + pvDividend + "," + gamma + "," + vega + "," + theta
					+ "," + undPrice + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.tickOptionComputation(tickerId, field,
								impliedVol, delta, optPrice, pvDividend, gamma,
								vega, theta, undPrice);
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
	public void orderStatus(final int orderId, final String status,
			final int filled, final int remaining, final double avgFillPrice,
			final int permId, final int parentId, final double lastFillPrice,
			final int clientId, final String whyHeld) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("orderStatus(" + orderId + "," + status + ","
					+ filled + "," + remaining + "," + avgFillPrice + ","
					+ parentId + "," + parentId + "," + lastFillPrice + ","
					+ clientId + "," + whyHeld);
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.orderStatus(orderId, status, filled, remaining,
								avgFillPrice, permId, parentId, lastFillPrice,
								clientId, whyHeld);
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
			_logger.debug("openOrder(" + orderId + "," + contract.m_symbol
					+ "," + order.m_orderId + "," + orderState.toString() + ")");
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
	public void updatePortfolio(final Contract contract, final int position,
			final double marketPrice, final double marketValue,
			final double averageCost, final double unrealizedPNL,
			final double realizedPNL, final String accountName) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("updatePortfolio(" + contract.m_symbol + ","
					+ position + "," + marketPrice + "," + marketValue + ","
					+ averageCost + "," + unrealizedPNL + "," + realizedPNL
					+ "," + accountName + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.updatePortfolio(contract, position, marketPrice,
								marketValue, averageCost, unrealizedPNL,
								realizedPNL, accountName);
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
					+ contractDetails.m_summary.m_symbol + ")");
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
					+ contractDetails.m_summary.m_symbol + ")");
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
			_logger.debug("execDetails(" + reqId + "," + contract.m_symbol
					+ "," + execution.m_orderId + ")");
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
	public void updateMktDepth(final int tickerId, final int position,
			final int operation, final int side, final double price,
			final int size) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("updateMktDepth()");
		
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.updateMktDepth(tickerId, position, operation,
								side, price, size);
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
	public void updateMktDepthL2(final int tickerId, final int position,
			final String marketMaker, final int operation, final int side,
			final double price, final int size) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("updateMktDepth2()");
			
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.updateMktDepthL2(tickerId, position,
								marketMaker, operation, side, price, size);
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
	public void historicalData(final int reqId, final String date,
			final double open, final double high, final double low,
			final double close, final int volume, final int count,
			final double WAP, final boolean hasGaps) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("historicalData(" + reqId + "," + date + "," +  open + "," + high + "," + low + "," +  close + "," +  volume + "," +  count + "," +  WAP + "," +  hasGaps + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.historicalData(reqId, date, open, high, low,
								close, volume, count, WAP, hasGaps);
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
			_logger.debug("scannerData(" + reqId + "," +  rank + "," +  contractDetails.m_summary.m_symbol + "," +  distance + "," +  benchmark + "," +  projection + "," +  legsStr + ")");
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
	public void realtimeBar(final int reqId, final long time,
			final double open, final double high, final double low,
			final double close, final long volume, final double wap,
			final int count) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("realtimeBar("+ reqId + "," +  time + "," +  open + "," +  high + "," +  low + "," +  close + "," +  volume + "," +  wap + "," +  count + ")");
		}

		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.realtimeBar(reqId, time, open, high, low, close,
								volume, wap, count);
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
	public void deltaNeutralValidation(final int reqId,
			final UnderComp underComp) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("deltaNeutralValidation(" + reqId + "," +  underComp.m_conId + ")");
		}
		_pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					_handlersSemaphore.acquire();
					for (TwsSocketReader reader : connectorHandlers) {
						reader.deltaNeutralValidation(reqId, underComp);
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
			_logger.debug("comissionReport(" + "comission=" + commissionReport.m_commission + "," +  commissionReport.m_execId + ")");
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

}
