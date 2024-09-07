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
import com.ib.client.Bar;
import com.ib.client.CommissionReport;
import com.ib.client.Contract;
import com.ib.client.ContractDescription;
import com.ib.client.ContractDetails;
import com.ib.client.Decimal;
import com.ib.client.DeltaNeutralContract;
import com.ib.client.DepthMktDataDescription;
import com.ib.client.EClientSocket;
import com.ib.client.EJavaSignal;
import com.ib.client.EReader;
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

	private EClientSocket client = null;

	private String _twsHost;

	private int _twsPort;

	private int _twsConnectionId;
	
//	ExecutorService _pool;

	private List<TwsSocketReader> connectorHandlers = new ArrayList<TwsSocketReader>();

	private Semaphore _handlersSemaphore = new Semaphore(1);
	
	private TwsBroker broker = null;
	
	private EReaderSignal readerSignal;


	public TwsConnectorSocket() {
		//_pool = Executors.newFixedThreadPool(10);


	}

	public void connect(TwsBroker broker, final String host,
			final int port, final int connectionId)  {
		
			this.broker = broker;
		//	m_client = new EClientSocket(this, new EJavaSignal());
			_twsHost = host;
			_twsPort = port;
			_twsConnectionId = connectionId;
			makeConnection();
			
	}
	
	  public void makeConnection() {

	        //Try to connect here
	        System.out.println("Connecting to TWS API...");
	        readerSignal = new EJavaSignal();
	        client = new EClientSocket(this, readerSignal);


	        // Pause here for connection to complete
	        try {
	            while (!(client.isConnected())) {
	                Thread.sleep(500);
	                client.eConnect("127.0.0.1", _twsPort, _twsConnectionId);
	            }
	            System.out.println("Connected!");

	            final EReader reader = new EReader(client, readerSignal);
	            reader.start();


	            new Thread(() -> {

	                while (client.isConnected()) {
	                    readerSignal.waitForSignal();
	                    try {
	                        reader.processMsgs();
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                        System.out.println("Error in reader: " + e.getMessage());
	                    }
	                }
	            }).start();


	        } catch (Exception eas) {
	        	System.out.println(eas.toString());
	        }

	    }

	
	public boolean isConnected() { 
		return client.isConnected();
	}
	
	
	public void disconnect() {
		client.eDisconnect();
		
	}

	public EClientSocket getClientSocket() {
		return client;
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
					
				} finally {
					_handlersSemaphore.release();
				}
			}
		});
		runner.start();
		
		
		
		
	}
	
	

	@Override
	public void issueSignal() {
		
		
	}

	@Override
	public void waitForSignal() {
		
		
	}

	@Override
	public void error(final Exception e) {
		if (_logger.isDebugEnabled()) {
			_logger.debug("error(" + "," + e.getClass().getName() + " "
					+ e.toString());
		}
		_logger.error("Exception Error " + e.toString());
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
		broker.getExecutor().execute(new Runnable() {

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
