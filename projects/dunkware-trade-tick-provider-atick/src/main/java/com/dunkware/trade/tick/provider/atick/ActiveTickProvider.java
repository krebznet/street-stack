package com.dunkware.trade.tick.provider.atick;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.trade.tick.api.provider.ATickProvider;
import com.dunkware.trade.tick.api.provider.TickProviderException;
import com.dunkware.trade.tick.api.provider.TickProviderSubscription;
import com.dunkware.trade.tick.api.provider.TradeSymbolService;
import com.dunkware.trade.tick.api.provider.impl.TickProviderImpl;
import com.dunkware.trade.tick.model.feed.TickFeedTicker;
import com.dunkware.trade.tick.model.provider.TickProviderSpec;
import com.dunkware.trade.tick.model.provider.TickProviderState;
import com.dunkware.trade.tick.model.provider.TickProviderStatsSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.provider.atick.impl.ATProviderSession;

import at.feedapi.ActiveTickServerAPI;
import at.feedapi.Helpers;
import at.shared.ATServerAPIDefines;
import at.shared.ATServerAPIDefines.ATGUID;
import at.shared.ATServerAPIDefines.ATLOGIN_RESPONSE;
import at.shared.ATServerAPIDefines.ATQuoteFieldType;
import at.shared.ATServerAPIDefines.ATSYMBOL;
import at.shared.ATServerAPIDefines.ATStreamRequestType;
import at.utils.jlib.Errors;

@ATickProvider(type = "ActiveTick")
public class ActiveTickProvider extends TickProviderImpl {

	public static final boolean SUBSCRIBE = false;

	private Logger logger = LoggerFactory.getLogger("ATProvider");

	private ATProviderSession session = null;
	private ActiveTickServerAPI serverapi = new ActiveTickServerAPI();

	private BlockingQueue<ATLOGIN_RESPONSE> loginResponse = new LinkedBlockingQueue<ATLOGIN_RESPONSE>();

	private SnapshotRunner snapshotRunner;

	private TickProviderSpec spec;

	private String host;
	private int port;
	private String username;
	private String password;
	private String apiKey;

	private ConcurrentHashMap<Long, String> quoteRequests = new ConcurrentHashMap<Long, String>();

	private TradeSymbolService symbolService; 
	
	
	public TradeSymbolService getSymbolService() { 
		return symbolService;
	}
	
	
	@Override
	public void resetDay() {
	
	}


	@Override
	public void connect(TickProviderSpec spec, TradeSymbolService symbolService, DExecutor executor) throws TickProviderException {
		this.symbolService = symbolService; 
		session = new ATProviderSession(serverapi, this);
		this.spec = spec;
		try {
			if (spec.getProperties().containsKey("host") == false) {
				throw new TickProviderException("host property not set");
			}

			if (spec.getProperties().containsKey("port") == false) {
				throw new TickProviderException("port property not set");
			}
			if (spec.getProperties().containsKey("username") == false) {
				throw new TickProviderException("username property not set");
			}
			if (spec.getProperties().containsKey("apiKey") == false) {
				throw new TickProviderException("apiKey property not set");
			}
			if (spec.getProperties().containsKey("password") == false) {
				throw new TickProviderException("password property not set");
			}
			port = (Integer)spec.getProperties().get("port");
			username = spec.getProperties().get("username").toString();
			password = spec.getProperties().get("password").toString();
			apiKey = spec.getProperties().get("apiKey").toString();
			host = spec.getProperties().get("host").toString();

		} catch (Exception e) {
			throw new TickProviderException("Exception parsing ActiveTick Spec Properties " + e.toString());
		}
		logger.debug("Connecting {} {} {}", host, port, username);
		serverapi.ATInitAPI();

		if (apiKey.length() != 32) {
			throw new TickProviderException("ATFeed API Key is not 32 characters");
		}

		ATGUID atguid = (new ATServerAPIDefines()).new ATGUID();
		atguid.SetGuid(apiKey);

		boolean rc = session.Init(atguid, host, port, username, password);
		if (!rc) {
			throw new TickProviderException("Set API Key Failed (returned false");
		}
		try {
			ATLOGIN_RESPONSE response = loginResponse.poll(10, TimeUnit.SECONDS);
			if (response == null) {
				throw new TickProviderException("Login Response not returned from ATAPI in 10 seconds");
			}
			switch (response.loginResponse.m_atLoginResponseType) {
			case ATServerAPIDefines.ATLoginResponseType.LoginResponseSuccess:
				setStatus(TickProviderState.CONNECTED);
				logger.info("Connected");
				break;
			case ATServerAPIDefines.ATLoginResponseType.LoginResponseInvalidUserid:
				setStatus(TickProviderState.ERROR);
				setConnectionError("Invalid Username " + username);
				logger.error("Failed Login Response Invalid User");
				throw new TickProviderException("Invalid Username " + username);
			case ATServerAPIDefines.ATLoginResponseType.LoginResponseInvalidPassword:
				logger.error("Failed Login Response Invalid Password");
				setStatus(TickProviderState.ERROR);
				throw new TickProviderException("Invalid Password " + password);
			case ATServerAPIDefines.ATLoginResponseType.LoginResponseInvalidRequest:
				logger.error("Failed Login Response Invalid Request");
				setStatus(TickProviderState.ERROR);
				throw new TickProviderException("Invalid Login Request");
			case ATServerAPIDefines.ATLoginResponseType.LoginResponseLoginDenied:
				setStatus(TickProviderState.ERROR);
				logger.error("Login Denied");
				throw new TickProviderException("Login Denied");
			case ATServerAPIDefines.ATLoginResponseType.LoginResponseServerError:
				logger.error("Failed Login Server Error");
				setStatus(TickProviderState.ERROR);
				throw new TickProviderException("Server Error");
			default:
				setStatus(TickProviderState.ERROR);
				logger.info("Failed Login Response Unknown");
				throw new TickProviderException(
						"Unknown Login Response value " + response.loginResponse.m_atLoginResponseType);
			}
			snapshotRunner = new SnapshotRunner();
			snapshotRunner.start();
			
		} catch (InterruptedException e) {
			setStatus(TickProviderState.ERROR);
			throw new TickProviderException("Thread Interrupted during connect");
		}
	}
	
	


	
	

	@Override
	public void subscribeTickers(Collection<TradeTickerSpec> tickers) {
		logger.error("Implement ActiveTick subsribe tickers");
	}

	@Override
	public String tickerToString(TradeTickerSpec ticker) {
		return ticker.getSymbol();
	}
	

	@Override
	public TickFeedTicker getFeedTicker(String symbol) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public List<TradeTickerSpec> getInValidatedSubscriptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void subscribe(String symbol) throws TickProviderException {
		if (!SUBSCRIBE) {
			return;
		}
		
		ATSYMBOL atSymbol = Helpers.StringToSymbol(symbol);
		List<ATSYMBOL> atSymbols = new ArrayList<ATSYMBOL>();
		atSymbols.add(atSymbol);
		ATStreamRequestType requestType = (new ATServerAPIDefines()).new ATStreamRequestType();
		requestType.m_streamRequestType = ATStreamRequestType.StreamRequestSubscribe;
		// TODO: Fix stream request subscription NPE
		long request = 0;
		try {
			Object req = session.GetRequestor();
			if (req == null) {
				logger.error("Session getRequestor is null WTF ");
				return;
			}
			request = session.GetRequestor().SendATQuoteStreamRequest(atSymbols, requestType, 3000);
		} catch (NullPointerException e) {
			logger.error("NPE on subscribe " + symbol);
			return;
		}
		if (request < 0) {
			String error = Errors.GetStringFromError((int) request);
			logger.error("ticker subscribe problem " + symbol + " " + error);
			notifyInternalException("Symbol " + symbol + " subscribe error " + error);
		}

	}

	@Override
	protected void unsubscribe(String symbol) {
		if (!SUBSCRIBE) {
			return;
		}
		if (logger.isTraceEnabled()) {
			logger.trace("UNSUBSCRIBE:" + symbol);
		}
		ATSYMBOL atSymbol = Helpers.StringToSymbol(symbol);
		List<ATSYMBOL> atSymbols = new ArrayList<ATSYMBOL>();
		atSymbols.add(atSymbol);

		ATStreamRequestType requestType = (new ATServerAPIDefines()).new ATStreamRequestType();
		requestType.m_streamRequestType = ATStreamRequestType.StreamRequestUnsubscribe;
		long request = session.GetRequestor().SendATQuoteStreamRequest(atSymbols, requestType,
				ActiveTickServerAPI.DEFAULT_REQUEST_TIMEOUT);
		if (request < 0) {
			String error = Errors.GetStringFromError((int) request);
			logger.error("ticker usubscribe problem " + symbol + " " + error);
			notifyInternalException("Symbol " + symbol + " unsubscribe error " + error);
		}
	}

	public void sessionLoginResponse(ATLOGIN_RESPONSE response) {
		loginResponse.add(response);
	}

	public ConcurrentHashMap<Long, String> getQuoteRequests() {
		return quoteRequests;
	}

	private class SnapshotRunner extends Thread {

		public void run() {
			try {
				while (!interrupted()) {
					Thread.sleep(1000);
					if (getSubscriptions().size() == 0) {
						continue;
					}
					for (TickProviderSubscription sub : getSubscriptions()) {
						ATSYMBOL symbol = Helpers.StringToSymbol(sub.getTicker().getSymbol());
						List<ATSYMBOL> symbols = Arrays.asList(symbol);
						List<ATQuoteFieldType> lstFieldTypes = new ArrayList<ATQuoteFieldType>();
						ATServerAPIDefines atServerAPIDefines = new ATServerAPIDefines();
						lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.Symbol));
						lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.LastPrice));
						lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.Volume));
						lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.LastTradeDateTime));
						lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.OpenPrice));
						lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.ClosePrice));
						lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.HighPrice));
						lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.LowPrice));
						lstFieldTypes
								.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.AfterMarketTradeCount));
						lstFieldTypes
								.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.ExtendedHoursLastPrice));
						lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.PreMarketVolume));
						lstFieldTypes
								.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.PreMarketTradeCount));
						lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.TradeCount));
						long request = session.GetRequestor().SendATQuoteDbRequest(symbols, lstFieldTypes,
								ActiveTickServerAPI.DEFAULT_REQUEST_TIMEOUT); // this must only return 500 
						quoteRequests.put(request, sub.getSymbol());
						if (request < 0) {
							logger.error("Snapshot Request Exception " + Errors.GetStringFromError((int) request));

						}
					}
					// request them
//					List<ATSYMBOL> lstSymbols = new ArrayList<ATSYMBOL>();
//					for (TickProviderSubscription sub : getSubscriptions()) {
//						ATSYMBOL symbol = Helpers.StringToSymbol(sub.getTicker().getSymbol());	
//						lstSymbols.add(symbol);
//					}

					// lstFieldTypes.add(atServerAPIDefines.new
					// ATQuoteFieldType(ATQuoteFieldType.AskPrice));
					// lstFieldTypes.add(atServerAPIDefines.new
					// ATQuoteFieldType(ATQuoteFieldType.AskSize));
					// lstFieldTypes.add(atServerAPIDefines.new
					// ATQuoteFieldType(ATQuoteFieldType.BidPrice));
					// lstFieldTypes.add(atServerAPIDefines.new
					// ATQuoteFieldType(ATQuoteFieldType.BidSize));

				}
			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					return;
				}
				logger.error("Snapshot Thread Exception " + e.toString(), e);
			}

		}
	}

	@Override
	public TickProviderStatsSpec getStats() {
		TickProviderStatsSpec stats = new TickProviderStatsSpec();
		stats.setState(TickProviderState.DISCONNECTED);
		stats.setSubscriptionCount(this.getSubscriptions().size());
		return stats;
	}

	@Override
	public TickProviderState getState() {
		return TickProviderState.DISCONNECTED;
	}

	@Override
	public List<TradeTickerSpec> getValidatedSubscriptions() {
		logger.error("implement active tick validated subscriptions");
		return null;
	}
	
	
	
	

	
}
