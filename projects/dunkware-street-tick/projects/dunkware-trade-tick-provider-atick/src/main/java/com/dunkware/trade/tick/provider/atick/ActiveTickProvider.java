package com.dunkware.trade.tick.provider.atick;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

import com.dunkware.trade.tick.api.feed.TickFeed;
import com.dunkware.trade.tick.api.feed.TickFeedSubscription;
import com.dunkware.trade.tick.api.provider.ATickProvider;
import com.dunkware.trade.tick.api.provider.TickProvider;
import com.dunkware.trade.tick.api.provider.TickProviderException;
import com.dunkware.trade.tick.model.feed.TickFeedQuote;
import com.dunkware.trade.tick.model.feed.TickFeedSnapshot;
import com.dunkware.trade.tick.model.feed.TickFeedTrade;
import com.dunkware.trade.tick.model.provider.TickProviderSpec;
import com.dunkware.trade.tick.model.provider.TickProviderState;
import com.dunkware.trade.tick.model.provider.TickProviderStatsSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.provider.atick.impl.ATProviderSession;
import com.dunkware.utils.core.concurrent.DunkExecutor;
import com.dunkware.utils.core.time.DunkTime;
import com.dunkware.utils.core.time.DunkTimeZones;

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
public class ActiveTickProvider implements TickProvider {

	private Logger logger = LoggerFactory.getLogger(ActiveTickProvider.class);

	private ATProviderSession session = null;
	private ActiveTickServerAPI serverapi = new ActiveTickServerAPI();

	private BlockingQueue<ATLOGIN_RESPONSE> loginResponse = new LinkedBlockingQueue<ATLOGIN_RESPONSE>();

	private AtomicInteger snapshotRequestCount = new AtomicInteger();
	
	private TickProviderSpec spec;

	private BlockingQueue<Object> messgeQueue = new LinkedBlockingQueue<Object>();
	
	
	private AtomicInteger snapshotCounter = new AtomicInteger(0);
	private AtomicInteger quotes = new AtomicInteger(0);
	private AtomicInteger trades = new AtomicInteger(0);

	private String host;
	private int port;
	private String username;
	private String password;
	private String apiKey;

	private LocalDateTime lastStreamMessage = LocalDateTime.now();
	private LocalDateTime lastSnapshotMessage = LocalDateTime.now();
	
//	private SnapshotChecker checker; 
	
	private TickProviderState state = TickProviderState.CREATED;
	
	private String connectionError; 
	
	private TickFeed feed;

	private ConcurrentHashMap<Long, Integer> snapshotRequests = new ConcurrentHashMap<Long, Integer>();

	private List<TradeTickerSpec> requestedSubscriptions = new ArrayList<TradeTickerSpec>();
	private List<TradeTickerSpec> pendingSubscriptions = new ArrayList<TradeTickerSpec>();
	private Map<String, TickFeedSubscription> feedSubscriptions = new ConcurrentHashMap<String, TickFeedSubscription>();

	private Queue<TickFeedSubscription> pendingStreamSubscriptions = new LinkedList<TickFeedSubscription>();
	
	private Map<String, SymbolUpdates> updates = new ConcurrentHashMap<String, SymbolUpdates>();

	private StreamSubscriber streamSubsriber;
	private Runner statRunner;
	
	private List<MessageHandler> messageHandlers = new ArrayList<MessageHandler>();
	
	private SnapshotSender snapshotSender;
	
	
	public void tradeUpdate(String ticker) {
		trades.incrementAndGet();
		if (updates.get(ticker) == null) {
			SymbolUpdates u = new SymbolUpdates();
			u.trades.incrementAndGet();
			updates.put(ticker, u);
		} else {
			updates.get(ticker).trades.incrementAndGet();
		}
	}

	public void quoteUpdate(String ticker) {
		quotes.incrementAndGet();
		if (updates.get(ticker) == null) {
			SymbolUpdates u = new SymbolUpdates();
			u.quotes.incrementAndGet();
			updates.put(ticker, u);
		} else {
			updates.get(ticker).quotes.incrementAndGet();
		}
	}

	private class SymbolUpdates {
		public AtomicInteger quotes = new AtomicInteger(0);
		public AtomicInteger trades = new AtomicInteger(0);
		public AtomicInteger snapshots = new AtomicInteger(0);
	}

	@Override
	public void connect(TickProviderSpec spec, TickFeed feed, DunkExecutor executor) throws TickProviderException {
		this.feed = feed;
		Runner runner = new Runner();
		runner.start();
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
			port = (Integer) spec.getProperties().get("port");
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
				state = TickProviderState.CONNECTED;
				logger.info("Connected");
				break;
			case ATServerAPIDefines.ATLoginResponseType.LoginResponseInvalidUserid:
				state = TickProviderState.ERROR;
				logger.error("Failed Login Response Invalid User");
				connectionError = "Failed Login response";
				throw new TickProviderException("Invalid Username " + username);
			case ATServerAPIDefines.ATLoginResponseType.LoginResponseInvalidPassword:
				logger.error("Failed Login Response Invalid Password");
				state = TickProviderState.ERROR;
				connectionError = "Invalid Password";
				throw new TickProviderException("Invalid Password " + password);
			case ATServerAPIDefines.ATLoginResponseType.LoginResponseInvalidRequest:
				logger.error("Failed Login Response Invalid Request");
				connectionError = "Failed Login";
				state = TickProviderState.ERROR;
				throw new TickProviderException("Invalid Login Request");
			case ATServerAPIDefines.ATLoginResponseType.LoginResponseLoginDenied:
				state = TickProviderState.ERROR;
				logger.error("Login Denied");
				connectionError = "Login Denied";
				throw new TickProviderException("Login Denied");
			case ATServerAPIDefines.ATLoginResponseType.LoginResponseServerError:
				logger.error("Failed Login Server Error");
				state = TickProviderState.ERROR;
				connectionError = "Failed Login Server Error";
				throw new TickProviderException("Server Error");
			default:
				state = TickProviderState.ERROR;
				logger.info("Failed Login Response Unknown");
				connectionError = "Failed Login Unknown";
				throw new TickProviderException(
						"Unknown Login Response value " + response.loginResponse.m_atLoginResponseType);
			}

		} catch (InterruptedException e) {
			state = TickProviderState.ERROR;
			throw new TickProviderException("Thread Interrupted during connect");
		}
	}

	@Override
	public boolean isSubscribed(TradeTickerSpec ticker) {
		if(feedSubscriptions.get(ticker.getSymbol()) == null) { 
			return false; 
		}
		return true; 
	}

	public Map<Long,Integer> getPendingSnapshotRequests() { 
		return snapshotRequests;
	}
	@Override
	public TickFeedSubscription getSubscription(TradeTickerSpec ticker) throws TickProviderException {
		TickFeedSubscription sub = feedSubscriptions.get(ticker.getSymbol());
		if(sub == null) { 
			throw new TickProviderException("Subscription " + ticker.getSymbol() + " does not exist");
		}
		return sub;
	}

	@Override
	public TickFeedSubscription getSubscription(String symbol) throws TickProviderException {
		TickFeedSubscription sub = feedSubscriptions.get(symbol);
		if(sub == null) { 
			throw new TickProviderException("Subscription " + symbol + " does not exist");
		}
		return sub;
	}

	@Override
	public String getConnectionError() {
		return connectionError;
	}

	@Override
	public List<TradeTickerSpec> getInvalidTickers() {
		return pendingSubscriptions;
	}

	@Override
	public Collection<TickFeedSubscription> getSubscriptions() {
		return feedSubscriptions.values();
	}

	@Override
	public int getQuoteCount() {
		return quotes.get();
	}

	@Override
	public int getTradeCount() {
		return trades.get();
	}

	@Override
	public int getSnapshotCount() {
		return snapshotCounter.get();
	}

	@Override
	public void newDay() {
		for (TickFeedSubscription sub : feedSubscriptions.values()) {
			sub.newDay();
		}
	}
	
	// callback methods 
	
	public void onTrade(TickFeedTrade trade) { 
		messgeQueue.add(trade);
	}
	
	public void onSnapshot(TickFeedSnapshot snapshot) { 
		lastSnapshotMessage = LocalDateTime.now(DunkTimeZones.zoneNewYork());
		this.lastStreamMessage = LocalDateTime.now(DunkTimeZones.zoneNewYork());
		SymbolUpdates updat = updates.get(snapshot.getSymbol());
		if(updat == null) { 
			updat = new SymbolUpdates();
			updat.snapshots.set(1);
			updates.put(snapshot.getSymbol(), updat);
		} else { 
			updates.get(snapshot.getSymbol()).snapshots.incrementAndGet();
		}
		snapshotCounter.incrementAndGet();
		if(feedSubscriptions.get(snapshot.getSymbol()) == null) {
			TradeTickerSpec spec = this.feed.getTickerSpec(snapshot.getSymbol());
			TickFeedSubscription sub = new TickFeedSubscription(snapshot,spec);
			sub.setSymbol(snapshot.getSymbol());
			sub.setLastPrice(snapshot.getLast());
			
			feedSubscriptions.put(snapshot.getSymbol(), sub);
			pendingStreamSubscriptions.add(sub);
			
		} else { 
			TickFeedSubscription sub = feedSubscriptions.get(snapshot.getSymbol()); 
			sub.setLastSnapshot(snapshot);
		}
	}
	
	public void onQuote(TickFeedQuote quote) { 
		lastStreamMessage = LocalDateTime.now(DunkTimeZones.zoneNewYork());		
		messgeQueue.add(quote);
	}

	@Override
	public void subscribeTickers(Collection<TradeTickerSpec> tickers) {
		// first we want to do snapshot; 
		for (TradeTickerSpec tradeTickerSpec : tickers) {
			if(requestedSubscriptions.contains(tradeTickerSpec) == true || pendingSubscriptions.contains(tradeTickerSpec)) { 
				continue;
			}
			requestedSubscriptions.add(tradeTickerSpec);
			pendingSubscriptions.add(tradeTickerSpec);
		}
		List<ATSYMBOL> symbolList = new ArrayList<ATSYMBOL>();
		for (TradeTickerSpec sub : tickers) {
			ATSYMBOL symbol = Helpers.StringToSymbol(sub.getSymbol());
			symbolList.add(symbol);
		}
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
		lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.AfterMarketTradeCount));
		lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.ExtendedHoursLastPrice));
		lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.PreMarketVolume));
		lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.PreMarketTradeCount));
		lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.TradeCount));
		lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.BidPrice));
		lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.BidSize));
		lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.AskPrice));
		lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.AskSize));
		// So the issue here is we need to on FeedSubscription: we either add AfterMarketTradeCount as variabl
		// add AfterMarketHoursLastPrice, PreMarketVolume,PreMarketTradeCount
		// or you add the sum to the volume but then its off 
		
		// EXTENDED HOURS LAST PRICE -> 
		long request = session.GetRequestor().SendATQuoteDbRequest(symbolList, lstFieldTypes,
				ActiveTickServerAPI.DEFAULT_REQUEST_TIMEOUT); // this must only return 500
		snapshotRequests.put(request, snapshotRequestCount.incrementAndGet());

	}
	
	
	

	public void sessionLoginResponse(ATLOGIN_RESPONSE response) {
		
		int count = 0;
		while(count < 6) { 
			MessageHandler handler = new MessageHandler();
			handler.start();
			messageHandlers.add(handler);
			count++;
		}
		loginResponse.add(response);
		statRunner = new Runner();
		statRunner.start();
		
		streamSubsriber = new StreamSubscriber();
		streamSubsriber.start();
		
	//	checker = new SnapshotChecker();
	//	checker.start();
		
		snapshotSender = new SnapshotSender();
		snapshotSender.start();
	}
	
	private void subscribeStream(List<TickFeedSubscription> subscriptions) { 
		if(1 == 1) { 
			return;
		}
		List<ATSYMBOL> atSymbols = new ArrayList<ATSYMBOL>();
		for (TickFeedSubscription sub : subscriptions) {
			ATSYMBOL atSymbol = Helpers.StringToSymbol(sub.getSymbol());
			atSymbols.add(atSymbol);
		}
		ATStreamRequestType requestType = (new ATServerAPIDefines()).new ATStreamRequestType();
		requestType.m_streamRequestType = ATStreamRequestType.StreamRequestSubscribe;
		long request = 0;
		try {
			Object req = session.GetRequestor();
			if (req == null) {
				logger.error("Session getRequestor is null WTF ");
				return;
			}
			request = session.GetRequestor().SendATQuoteStreamRequest(atSymbols, requestType, 3000);
		} catch (NullPointerException e) {
			logger.error("NPE on subscribe tickers " + e.toString());
			return;
		}
		if (request < 0) {
			String error = Errors.GetStringFromError((int) request);
			logger.error("Exception subscribing tickers returned error code");
		}
	}
	
	public class StreamSubscriber extends Thread { 
		
		public void run() {
			try {
				while(!interrupted()) { 
					Thread.sleep(5000);
					List<TickFeedSubscription> list = new ArrayList<TickFeedSubscription>();
					list.addAll(pendingStreamSubscriptions);
				    pendingStreamSubscriptions.clear();
				    if(list.size() > 0) { 
					   subscribeStream(list);    	
				    
				    }
					
				}
			    
			} catch (Exception e) {
				logger.error("Exception adding pending tick feed subscriptions " + e.toString());
			}
		}
	}

	public class Runner extends Thread {

		private int lq = -1; 
		private int lt = -1;
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
					if(lq == -1) { 
						lq = quotes.get();
					}
					if(lt == -1) { 
						lt = trades.get();
					}
					lq = quotes.get() - lq; 
					lt = trades.get() - lt;
					

					
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	


	@Override
	public TickProviderStatsSpec getStats() {
		TickProviderStatsSpec stats = new TickProviderStatsSpec();
		stats.setState(state);
		stats.setSubscriptionCount(this.feedSubscriptions.size());
		stats.setMessageCount(this.quotes.get() + this.trades.get() + this.snapshotCounter.incrementAndGet());
		stats.setLastSnapshotMessage(DunkTime.toStringTimeStamp(lastSnapshotMessage));
		stats.setLastStreamMessage(DunkTime.toStringTimeStamp(lastStreamMessage));
		return stats;
	}

	@Override
	public TickProviderState getState() {
		return state;
	}



	@Override
	public boolean isSubscribed(String symbol) {
		if(feedSubscriptions.get(symbol) == null) { 
			return false;
		}
		return true;
	}

	private class SnapshotSender extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					List<ATSYMBOL> symbolList = new ArrayList<ATSYMBOL>();
					for (TickFeedSubscription sub : feedSubscriptions.values()) {
						ATSYMBOL symbol = Helpers.StringToSymbol(sub.getSymbol());
						symbolList.add(symbol);
					}
					List<ATQuoteFieldType> lstFieldTypes = new ArrayList<ATQuoteFieldType>();
					ATServerAPIDefines atServerAPIDefines = new ATServerAPIDefines();
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.Symbol));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.LastPrice));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.Volume));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.BidPrice));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.BidSize));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.AskPrice));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.AskSize));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.LastTradeDateTime));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.OpenPrice));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.ClosePrice));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.HighPrice));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.LowPrice));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.AfterMarketTradeCount));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.ExtendedHoursLastPrice));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.PreMarketVolume));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.PreMarketTradeCount));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.TradeCount));
					
					long request = session.GetRequestor().SendATQuoteDbRequest(symbolList, lstFieldTypes,
							ActiveTickServerAPI.DEFAULT_REQUEST_TIMEOUT); // this must only return 500
					snapshotRequests.put(request, snapshotRequestCount.incrementAndGet());
					Thread.sleep(1000);
				} catch (Exception e) {
					logger.error("Exception sending snapshot request in snapshot sender " + e.toString());
					
				}
			}
		}
	}
	private class MessageHandler extends Thread { 
		
		public void run() { 
			try {
				while(!interrupted())	 {
					Object message = messgeQueue.take();
					if (message instanceof TickFeedQuote) {
						TickFeedQuote quote = (TickFeedQuote)message;
						feed.onQuote(quote);
						SymbolUpdates updat = updates.get(quote.getSymbol());
						if(updat == null) { 
							updat = new SymbolUpdates();
							updat.quotes.incrementAndGet();
							updates.put(quote.getSymbol(), updat);
						} else { 
							updates.get(quote.getSymbol()).quotes.incrementAndGet();
						}
						quotes.incrementAndGet();
						feedSubscriptions.get(quote.getSymbol()).setLastQuote(quote);
						
					}
					if(message instanceof TickFeedTrade) { 
						TickFeedTrade trade = (TickFeedTrade)message;
						feed.onTrade(trade);
						SymbolUpdates updat = updates.get(trade.getSymbol());
						if(updat == null) { 
							updat = new SymbolUpdates();
							updat.trades.set(1);
							updates.put(trade.getSymbol(), updat);
						} else { 
							updates.get(trade.getSymbol()).trades.incrementAndGet();
						}
						trades.incrementAndGet();
						feedSubscriptions.get(trade.getSymbol()).setLastTrade(trade);
					}
					
				}
			} catch (Exception e) {
				logger.error(MarkerFactory.getMarker("CRASH"), "Exception in consuming tick data " + e.toString());
			}
		}
		
	}

	
	private class SnapshotChecker extends Thread { 
		
		public void run() { 
			try {
				while(!interrupted()) { 
					Thread.sleep(5000);
					List<ATSYMBOL> symbolList = new ArrayList<ATSYMBOL>();
					for (TickFeedSubscription sub : feedSubscriptions.values()) {
						ATSYMBOL symbol = Helpers.StringToSymbol(sub.getSymbol());
						symbolList.add(symbol);
					}
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
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.AfterMarketTradeCount));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.AfterMarketVolume));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.ExtendedHoursLastPrice));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.PreMarketVolume));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.PreMarketTradeCount));
					lstFieldTypes.add(atServerAPIDefines.new ATQuoteFieldType(ATQuoteFieldType.TradeCount));

					long request = session.GetRequestor().SendATQuoteDbRequest(symbolList, lstFieldTypes,
							ActiveTickServerAPI.DEFAULT_REQUEST_TIMEOUT); // this must only return 500
					snapshotRequests.put(request, snapshotRequestCount.incrementAndGet());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
