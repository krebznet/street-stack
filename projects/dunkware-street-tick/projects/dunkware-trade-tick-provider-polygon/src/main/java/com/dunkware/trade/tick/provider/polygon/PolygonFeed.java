package com.dunkware.trade.tick.provider.polygon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.executor.DunkExecutor;
import com.dunkware.common.util.json.DunkJson;
import com.dunkware.trade.tick.provider.polygon.core.PolygonIO;
import com.dunkware.trade.tick.provider.polygon.core.event.PolygonAggEvent;
import com.dunkware.trade.tick.provider.polygon.core.event.PolygonQuote;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PolygonFeed {

	private static final int SNAPSHOT_TICKER_LIMIT = 150;
	private static final int EVENT_HANDLER_COUNT = 5;

	private PolygonIO.WebSockets webSocket;
	// private PolygonIO.Rest restClient;

	private String apiKey;

	private Map<String, PolygonTicker> tickers = new ConcurrentHashMap<String, PolygonTicker>();
	private List<String> subscriptions;

	private BlockingQueue<String> eventQueue = new LinkedBlockingQueue<String>();
	private DunkExecutor executor;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private AtomicInteger problemCount = new AtomicInteger(0);

	private List<EventHandler> eventHandlers = new ArrayList<EventHandler>();

	private Map<String,PolygonTicker> validatedTickers = new ConcurrentHashMap<String,PolygonTicker>();

	private SecondUpdater secondUpdater = new SecondUpdater();
	
	private PolygonFeedMetrics metrics;
	
	public void connect(String apiKey, List<String> subscriptions, DunkExecutor executor) throws Exception {
		this.apiKey = apiKey;
		subscriptions.add("BAC");
		subscriptions.add("JPM");
		
		subscriptions.add("SCHW");
		subscriptions.add("SPY");
		
		metrics = new PolygonFeedMetrics(this);
		// restClient = new PolygonIO.Rest(apiKey);
		this.executor = executor;
		if (subscriptions != null) {
			this.subscriptions = subscriptions;
			for (String symbol : subscriptions) {
				// create the Polygon Tickers upfront
				PolygonTicker ticker = new PolygonTicker(symbol);
				tickers.put(symbol, ticker);
				metrics.tickerAdded(ticker);
			}
		}
		try {
			// Create web socket
			// connection & subscribe 1 second agg to all tickers
			webSocket = new PolygonIO.WebSockets(apiKey, messageHandler);
			webSocket.connect("stocks");
			for (String string : subscriptions) {
				try {
					//webSocket.subscribe("A." + string);
					webSocket.subscribe("Q." + string);
					webSocket.subscribe("T." + string);
				} catch (Exception e) {
					logger.error("Exception subscribing stock Q,A for " + string);
					throw new Exception("Exeception subscribing sec agg for ticker " + string + " " + e.toString()); //
				}
			}
			int i = 0;
			while (i < EVENT_HANDLER_COUNT) {
				EventHandler handler = new EventHandler();
				handler.start();
				eventHandlers.add(handler);
				i++;
			}
		} catch (Exception e) {
			throw new Exception("Exception connecting " + e.toString());
		}
		secondUpdater.start();

		
	}
	
	public int getValidatedTickerCount() { 
		return validatedTickers.size();
	}
	
	public int getMessageQueueSize() { 
		return eventQueue.size();
	}
	
	public void close() { 
		secondUpdater.interrupt();
	}
	

	public void subscribe(List<String> symbols) {
		for (String string : symbols) {
			if(tickers.get(string) == null) { 
				PolygonTicker ticker = new PolygonTicker(string);
				tickers.put(string, ticker);
				metrics.tickerAdded(ticker);
				try {
					//webSocket.subscribe("A." + string);
					webSocket.subscribe("Q." + string);
					ticker.setValid(true);
				} catch (Exception e) {
					ticker.setValid(false);
					logger.error("Exception subscribing ticker on web socket " + e.toString() + " " + string);
				}
				
			}
		}
	}
	
	public void resetDay() { 
		if(logger.isDebugEnabled()) { 
			logger.debug("Reseting Day on Polygon Tickers");
		}
		for (PolygonTicker ticker : tickers.values()) {
			ticker.resetDay();
		}
		metrics.resetDay();
	}

	public void disconnect() {
		webSocket.close();
		for (EventHandler eventHandler : eventHandlers) {
			eventHandler.interrupt();
		}
	}

	public void unsubscribe(String ticker) {
		this.tickers.remove(ticker);
	}

	public Collection<PolygonTicker> getTickers() {
		return tickers.values();
	}

	private Function<String, String> messageHandler = e -> {
		this.eventQueue.add(e);
		return null;
	};

	public PolygonTicker getTicker(String symbol) {
		return tickers.get(symbol);
	}
	
	public PolygonFeedMetrics getMetrics() { 
		return metrics;
	}
	
	
	
	public List<String> getInValidatedSymbols() { 
		List<String> results = new ArrayList<String>();
		for (PolygonTicker ticker : tickers.values()) {
			if(ticker.isValidated() == false) { 
				results.add(ticker.getSymbol());
			}
		}
		return results;
	}
	
	public List<String> getValidatedSymbols() { 
		List<String> results = new ArrayList<String>();
		for (PolygonTicker ticker : tickers.values()) {
			if(ticker.isValidated()) { 
				results.add(ticker.getSymbol());
			}
		}
		return results;
	}

	private class EventHandler extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					String event = eventQueue.take();
					if (1 == 1)
						continue;
					JsonElement element = null;
					try {
						element = JsonParser.parseString(event);
					} catch (Exception e) {
						problemCount.incrementAndGet();
						logger.error("Exception parsing websocket message as JsonElement " + e.toString());
						continue;
					}
					JsonArray root = null;
					try {
						root = element.getAsJsonArray();
					} catch (Exception e) {
						logger.error("Exception parsing root element as JsonArray from web socket " + e.toString());
						;
						continue;
					}
					int arraySize = root.size();
					int i = 0;
					while (i < arraySize) {
						JsonObject object = null;
						try {
							JsonElement childElement = root.get(i);
							i++;
							if (childElement.isJsonObject() == false) {
								problemCount.incrementAndGet();
								logger.warn("Expected web socket message child to be an object but is a "
										+ childElement.getClass().getName());
								continue;
							}
							object = childElement.getAsJsonObject();
							String eventType = object.get("ev").getAsString();
							if (eventType.equals("A")) {
								
								PolygonAggEvent aggEvent = null;
								try {
									String value = object.toString();
									logger.error("Aggregation " + value);
									aggEvent = DunkJson.getObjectMapper().readValue(childElement.toString(),
											PolygonAggEvent.class);
								} catch (Exception e) {
									problemCount.incrementAndGet();
									logger.error("Exception Parsing 1 Sec Agg Event " + e.toString());
									;
									continue;
								}
								PolygonTicker ticker = tickers.get(aggEvent.getSymbol());
								if (ticker == null) {
									problemCount.incrementAndGet();
									logger.error("Exception getting polygon ticker on agg event symbol "
											+ aggEvent.getSymbol());
									continue;
								}
								metrics.aggEvent(aggEvent);
								validatedTickers.put(ticker.getSymbol(), ticker);
								ticker.agg(aggEvent);
							}
							if (eventType.equals("Q")) {
								PolygonQuote quote = null;
								try {
								//	System.out.println("Quote " + childElement.toString());
									quote = DunkJson.getObjectMapper().readValue(childElement.toString(),
											PolygonQuote.class);

								} catch (Exception e) {
									logger.error("Exception parsing quote " + e.toString());
									continue;
								}
								PolygonTicker ticker = tickers.get(quote.getSymbol());
								
								if (ticker == null) {
									problemCount.incrementAndGet();
									logger.error(
											"Exception getting polygon ticker quote event symbol " + quote.getSymbol());
									continue;
								}
								ticker.quote(quote);
								metrics.quoteEvent(quote);
								
							}

						} catch (Exception e) {
							problemCount.incrementAndGet();
							logger.error("Outer Parse Web Socket Event Exception " + e.toString());

						}

					}


				} catch (Exception e) {
					if (e instanceof InterruptedException) { 
						return;
					}
					logger.error("Outer Event Handler Exception " + e.toString());
				}
			}
		}
	}
	
	
	private class SecondUpdater extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					Thread.sleep(1000);
					for (PolygonTicker ticker : tickers.values()) {
						ticker.secondUpdate();
					}
					metrics.secondUpdate();
				} catch (Exception e) {
					
				}
			}
		}
	}

	
	
	
}
