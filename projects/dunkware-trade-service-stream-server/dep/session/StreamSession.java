package com.dunkware.trade.service.stream.server.controller.session;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.dunkware.common.kafka.properties.DKafkaProperties;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.properties.DProperties;
import com.dunkware.common.util.properties.DPropertiesBuilder;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.event.EStreamSessionSignal;
import com.dunkware.trade.service.stream.server.controller.event.EStreamSessionStarted;
import com.dunkware.trade.service.stream.server.controller.event.EStreamSessionStarting;
import com.dunkware.trade.service.stream.server.controller.event.EStreamSessionStopped;
import com.dunkware.trade.service.stream.server.controller.event.EStreamSessionStopping;
import com.dunkware.trade.service.stream.server.controller.event.EStreamSessionTicker;
import com.dunkware.trade.service.stream.server.controller.repository.StreamSessionDO;
import com.dunkware.trade.service.stream.server.controller.repository.StreamSessionRepo;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.WorkerSignalNotify;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.WorkerTickerNotify;
import com.dunkware.trade.service.stream.server.controller.session.xstream.ControllerNotifyExtType;
import com.dunkware.trade.service.stream.server.spring.RuntimeService;
import com.dunkware.trade.service.stream.server.store.StreamStore;
import com.dunkware.trade.service.stream.server.store.StreamStoreService;
import com.dunkware.trade.tick.api.feed.TradeStreamSubscription;
import com.dunkware.trade.tick.api.ticker.Ticker;
import com.dunkware.trade.tick.api.ticker.TickerList;
import com.dunkware.trade.tick.model.TradeTicks;
import com.dunkware.xstream.core.runtime.extensions.KafkaSignalExtType;
import com.dunkware.xstream.core.runtime.extensions.KafkaStreamExtType;
import com.dunkware.xstream.core.runtime.extensions.TimeUpdaterExtType;
import com.dunkware.xstream.model.XScriptBundle;
import com.dunkware.xstream.model.XStreamBundle;
import com.dunkware.xstream.model.metrics.XStreamMetrics;
import com.dunkware.xstream.store.mysql.MySqlCaptureExtType;
import com.dunkwrae.trade.service.tick.client.stream.NetTradeStream;

public class StreamSession {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private StreamController controller;

	@Autowired
	private StreamStoreService storeService;

	@Autowired
	private RuntimeService runtimeService; 
	
	
	
	
	@Value("${controller.workers}")
	private String workerEndpoints;

	@Value("${controller.tickservice}")
	private String tickServerEndpoint; 
	
	@Value("${kafka.brokers}")
	private String kafkaBrokers;

	
	
	private List<StreamSessionWorker> workers;
	private TickerList tickerList;
	private List<Ticker> activeTickers = new ArrayList<Ticker>();
	private NetTradeStream streamFeed;
	private StreamStore streamStore;
	
	private StreamSessionDO sessionEntity;
	
	@Autowired
	private StreamSessionRepo sessionRepo;
	
	private boolean inSession = false;
	
	public void startSession(StreamController controller, StreamStore streamStore) throws StreamSessionException {
		//this.sessionEntity = new StreamSessionDO();
		//sessionEntity.setStartTime(controll);
		EStreamSessionStarting starting = new EStreamSessionStarting(this);
		runtimeService.getEventTree().getRoot().event(starting);
		logger.info("Starting Stream Session " + controller.getName());
		this.controller = controller;
		this.streamStore = streamStore;
		// get the ticker list
		tickerList = buildTickerList(controller);
		// build the workers
		workers = buildWorkers();
		List<TickerList> workerLists = new ArrayList<TickerList>();
		for (StreamSessionWorker worker : workers) {
			workerLists.add(new TickerList());
		}
		// split ticker list into worker lists evenly
		int workerIndex = 0;
		for (Ticker ticker : tickerList.getTickers()) {
			if (workerIndex == workers.size()) {
				workerIndex = 0;
			}
			workerLists.get(workerIndex).getTickers().add(ticker);
			workerIndex++;
		}
		if(logger.isDebugEnabled()) { 
			int i = 0;
			for (TickerList tickerList : workerLists) {
				StringBuilder list = new StringBuilder();
				for (Ticker ticker : tickerList.getTickers()) {
					list.append(",");
					list.append(ticker.getSymbol());
				}
				logger.debug("Stream Worker TickerList " + i +  " " + list.toString() );
			}
		}
		
		// now lets start the workers!
		workerIndex = 0;
		String captureTable = null;
		String pattern = "yyyy_MM_dd";
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		String dateStamp = formatter.format(now);
		
		captureTable = controller.getName().toLowerCase() +  "_" + dateStamp;
		System.out.println(captureTable);
		for (StreamSessionWorker worker : workers) {
			
			try {
				TradeStreamSubscription sub = new TradeStreamSubscription();
				sub.setTickers(workerLists.get(workerIndex).getTickers());
				List<Integer> types = new ArrayList<Integer>();
				types.add(TradeTicks.TickSnapshot);
				sub.setTickTypes(types);
				logger.debug("Creating Worker Feed " + "Worker" + workerIndex);
				streamFeed = NetTradeStream.newInstance(tickServerEndpoint, sub, "Worker" + workerIndex,false);
				workerIndex++;
				Thread.sleep(2500);
			} catch (Exception e) {
				logger.error("Exception creating node net trade stream " + e.toString(),e);
				throw new StreamSessionException("Exception creating worker stream feed " + e.toString());
			}
			
			XStreamBundle bundle = new XStreamBundle();
			TimeUpdaterExtType timeupdater = new TimeUpdaterExtType();
			timeupdater.setTimeZone(DTimeZone.NewYork);
			bundle.addExtension(timeupdater);
			bundle.setTimeZone(controller.getSpec().getTimeZone());
			MySqlCaptureExtType capture = new MySqlCaptureExtType();
			capture.setDatabase(streamStore.getEntity().getDbSchema());
			capture.setHost(streamStore.getEntity().getHost());
			capture.setUsername(streamStore.getEntity().getUser());
			capture.setPassword(streamStore.getEntity().getPass());
			capture.setPort(streamStore.getEntity().getPort());
			
			capture.setTablePrefix(captureTable);
			bundle.addExtension(capture);

			KafkaStreamExtType tickStream = new KafkaStreamExtType();
			tickStream.setProperties(streamFeed.getConsumerProperties());
			tickStream.setDataTicks(TradeTicks.TickSnapshot + ":" + TradeTicks.FieldSymbol);
			//TODO: Configure data ticks from stream spec 
			bundle.addExtension(tickStream);
			
			KafkaSignalExtType kafkaSignal = new KafkaSignalExtType();
			DProperties props = DPropertiesBuilder.newBuilder()
			.addProperty(DKafkaProperties.BOOTSTRAP_SERVERS,kafkaBrokers)
			.addProperty(DKafkaProperties.TOPICS, controller.getName() + "_signals").build();
			kafkaSignal.setProperties(props);
			bundle.addExtension(kafkaSignal);
			
			
			//ControllerNotifyExtType notify = new ControllerNotifyExtType();
			//notify.setEndpoint(runtimeService.getEndpoint());
			//notify.setNode(worker.getEndpoint());
			//notify.setStream(getStream().getEntity().getName());
			//bundle.addExtension(notify);
			try {
				XScriptBundle scriptBundle = DJson.getObjectMapper().readValue(controller.getEntity().getVersions().get(0).getBundle(), XScriptBundle.class);
				bundle.setScriptBundle(scriptBundle);
			} catch (Exception e) {
				throw new StreamSessionException("Exception building script bundle " + e.toString());
			}
			
			try {
				worker.startWorker(bundle, controller.getEntity().getName(), streamFeed);
			} catch (Exception e) {
				// do we cancel all the other ones? 
				throw new StreamSessionException("Exception starting worker " + e.toString());
			}

			// TODO: create StreamSessionDO and populate the tickers
			// set the version etc. 
		}
		inSession = true; 
		EStreamSessionStarted started = new EStreamSessionStarted(this);
		runtimeService.getEventTree().getRoot().event(started);
	}
	
	public StreamController getStream() { 
		return controller;
	}

	public void stopSession() throws StreamSessionException {
		if(!inSession) { 
			throw new StreamSessionException("Not In Session State on StreamSession");
		}
		if(logger.isDebugEnabled()) { 
			logger.debug("Stopping Session");
		}
		EStreamSessionStopping stopping = new EStreamSessionStopping(this);
		runtimeService.getEventTree().getRoot().event(stopping);
		// lets stop the session on each 
		// worker 
		for (StreamSessionWorker worker : workers) {
			if(logger.isDebugEnabled()) { 
				logger.debug("Stopping Worker " + worker.getEndpoint());
			}
			try {
				// do we want to throw an entire exception
				// because we can't get a worker to connect? 
				worker.stopWorker();	
			} catch (Exception e) {
				logger.error("Exception Stopping Stream Worker Endpoint " + worker.getEndpoint() + " " + e.toString());;
			}
			
		}
		
		inSession = false;
		EStreamSessionStopped stopped = new EStreamSessionStopped(this);
		runtimeService.getEventTree().getRoot().event(stopped);

	}
	
	public List<Ticker> getActiveTickers() { 
		return activeTickers;
	}
	
	
	public XStreamMetrics streamStats(boolean rows, boolean vars) { 
		return null;
	}
	
	public List<StreamSessionWorker> getWorkers() {
		return workers;
	}
	
	public TickerList getTickerList() { 
		return tickerList;
	}
	
	/**
	 * Worker Signal Notify 
	 * @param notify
	 */
	public void sessionSignal(WorkerSignalNotify notify) {
		StreamSessionSignal sessionSignal = new StreamSessionSignal(this, notify.getSignal());
		EStreamSessionSignal event = new EStreamSessionSignal(this, sessionSignal);
		getEventNode().event(event);
	}

	/**
	 * Worker Ticker Notify 
	 * @param notify
	 */
	public void sessionTicker(WorkerTickerNotify notify) { 
		Ticker ticker = tickerList.getTicker(notify.getSymbol());
		activeTickers.add(ticker);
		EStreamSessionTicker event = new EStreamSessionTicker(this, ticker,notify.getTime());
		getEventNode().event(event);
	}
	
	public StreamStore getStreamStore() { 
		return this.streamStore;
	}
	
	public DEventNode getEventNode() { 
		return runtimeService.getEventTree().getRoot();
	}
	
	private List<StreamSessionWorker> buildWorkers() throws StreamSessionException {
		List<StreamSessionWorker> wokers = new ArrayList<StreamSessionWorker>();
		String[] endpoints = workerEndpoints.split("~");
		for (String string : endpoints) {
			try {
				StreamSessionWorker worker = new StreamSessionWorker(string, "fuzz",controller);
				wokers.add(worker);
			} catch (Exception e) {
				throw new StreamSessionException(
						"Exception creating session worker for endpoint " + string + " " + e.toString(), e);
			}

		}
		return wokers;
	}

	private TickerList buildTickerList(StreamController controller) throws StreamSessionException {
		String listNames[] = controller.getEntity().getTickerLists().split(",");
		for (String string : listNames) {
			try {
				TickerList list = controller.getService().getTickerClient().getTickerList(string);
				return list;
				// TODO: implement multiple ticker lists
			} catch (Exception e) {
				throw new StreamSessionException("Exception getting ticker list " + string + " " + e.toString());
			}

		}
		throw new StreamSessionException("BuilderTIckerList has nothing");

	}
}
