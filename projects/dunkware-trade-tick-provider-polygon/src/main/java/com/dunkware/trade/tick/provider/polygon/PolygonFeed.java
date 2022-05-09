package com.dunkware.trade.tick.provider.polygon;

import java.net.http.HttpResponse;
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

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.tick.provider.polygon.core.PolygonIO;
import com.dunkware.trade.tick.provider.polygon.core.PolygonIO.Rest;
import com.dunkware.trade.tick.provider.polygon.core.PolygonSnapshot;
import com.dunkware.trade.tick.provider.polygon.core.event.PolygonAggEvent;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PolygonFeed {

	private static final int SNAPSHOT_TICKER_LIMIT = 150;
	private static final int EVENT_HANDLER_COUNT = 1;

	private PolygonIO.WebSockets webSocket;
	//private PolygonIO.Rest restClient;

	private String apiKey;

	private Map<String, PolygonTicker> tickers = new ConcurrentHashMap<String, PolygonTicker>();
	private List<String> subscriptions;

	private BlockingQueue<String> eventQueue = new LinkedBlockingQueue<String>();
	private DExecutor executor;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private AtomicInteger problemCount = new AtomicInteger(0);

	private SnapshotRunner snapshotRunner;

	private List<EventHandler> eventHandlers = new ArrayList<EventHandler>();

	
	public void connect(String apiKey, List<String> subscriptions, DExecutor executor) throws Exception {
		this.apiKey = apiKey;
	//	restClient = new PolygonIO.Rest(apiKey);
		this.executor = executor;
		if(subscriptions != null) { 
			this.subscriptions = subscriptions;
			for (String symbol : subscriptions) {
				// create the Polygon Tickers upfront
				PolygonTicker ticker = new PolygonTicker(symbol);
				tickers.put(symbol, ticker);
			}	
		}
		try {
			// Create web socket
			// connection & subscribe 1 second agg to all tickers
			webSocket = new PolygonIO.WebSockets(apiKey, messageHandler);
			webSocket.connect("stocks");
			for (String string : subscriptions) {
				try {
					webSocket.subscribe("A." + string);
				} catch (Exception e) {
					throw new Exception("Exeception subscribing sec agg for ticker " + string + " " + e.toString()); // TODO:
																														// handle
																														// exception
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

		snapshotRunner = new SnapshotRunner();
		snapshotRunner.start();
	}

	public void subscribe(List<String> symbols) {
		for (String string : symbols) {
			if (tickers.get(string) == null) {
				PolygonTicker tiker = new PolygonTicker(string);
				tickers.put(string, tiker);
			}
		}
	}

	public void disconnect() {
		snapshotRunner.interrupt();
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

	private class EventHandler extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					String event = eventQueue.take();
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
									aggEvent = DJson.getObjectMapper().readValue(childElement.toString(), PolygonAggEvent.class);
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
								ticker.agg(aggEvent);
							}
						} catch (Exception e) {
							problemCount.incrementAndGet();
							logger.error("Outer Parse Web Socket Event Exception " + e.toString());

						}

					}

					try {
						// polyEvent = DJson.getObjectMapper().readValue(event, PolygonEvent.class);
						// need to parse a JsonObject
						// String object.get("eve"))
						String eventType = null;
						if (event.equals("A")) {
							PolygonTicker ticker = tickers.get("jsonobject.get(symb)");
							// volume / avgtrade size
							// tickcount
							// ticker.getTradeCount().addAndGet(SNAPSHOT_TICKER_LIMIT)
							//

						}
						// if a quote update the quote
						// if a trade update trade count by 1;

						// we can do this 2 ways, quote/trade subscriptions.

					} catch (Exception e) {
						// logger error
						continue;
					}
					// if second agg;
					// get ticker set shit on it
					//

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	private class SnapshotRunner extends Thread {

		public void run() {
			while (true) {
				List<SnapshotBatch> requests = new ArrayList<SnapshotBatch>();
				int count = 0;
				List<String> requestTickers = new ArrayList<String>();
				for (PolygonTicker ticker : tickers.values()) {
					if (count == SNAPSHOT_TICKER_LIMIT) {
						SnapshotBatch req = new SnapshotBatch(requestTickers.toArray(new String[requestTickers.size()]));
						requests.add(req);
						count = 0;
						requestTickers.clear();
					} else {
						requestTickers.add(ticker.getSymbol());
						count++;
					}
				}
				if(requestTickers.size() > 0) { 
					// add last batch 
					SnapshotBatch req = new SnapshotBatch(requestTickers.toArray(new String[requestTickers.size()]));
					requests.add(req);
					requestTickers.clear();
				}
				for (SnapshotBatch request : requests) {
					executor.execute(request);
				}
				requests.clear();
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Exception Outer Snapshot Runner " + e.toString());
					problemCount.incrementAndGet();

				}
			}

		}
	}

	/**
	 * Snapshot Batch
	 * 
	 * @author duncankrebs
	 *
	 */
	private class SnapshotBatch implements Runnable {

		final private String[] runTickers;

		public SnapshotBatch(String[] tickers) {
			this.runTickers = tickers;
		}

		@Override
		public void run() {
			 PolygonIO.Rest restClient = new Rest(apiKey);

			try {
				System.out.println("Running Snapshot Batch for tickers " + runTickers.toString());
				StringBuilder url = new StringBuilder();
				url.append("/v2/snapshot/locale/us/markets/stocks/tickers?");
				int count = 0;
				for (String string : runTickers) {
					if (count == 0) {
						url.append(string);
					} else {
						url.append(",");
						url.append(string);
					}
					count++;
				}
				String endpoint = url.toString();
				HttpResponse<String> response = null;
				try {
					 response = restClient.get(endpoint);		
					 System.out.println("returned snapshot response " + response.body());
					 
				} catch (Exception e) {
					System.err.println("exception snapshot invoke " + e.toString());
					
					// TODO: handle exception
				}
				if(1 == 1) { 
					return;
				}
				PolygonSnapshot[] snapshots = null;
				try {
					String out = response.body();
					System.out.println(out);
					System.out.println(System.lineSeparator());
					snapshots = DJson.getObjectMapper().readValue(response.body(), PolygonSnapshot[].class);
				} catch (Exception e) {
					logger.error("Exception parsing snapshot batch response " + e.toString());
					problemCount.incrementAndGet();
					return;
				}
				for (PolygonSnapshot snapshot : snapshots) {
					PolygonTicker ticker = PolygonFeed.this.tickers.get(snapshot.getTicker());
					if (ticker == null) {
						logger.error("Snapshot polygon ticker not found " + snapshot.getTicker());
						problemCount.incrementAndGet();
						continue;
					}
					ticker.snapshot(snapshot);
				}
			} catch (Exception e) {
				problemCount.incrementAndGet();
				logger.error("Outer snapshot batch exception " + e.toString());
			}

		}

	}

}
