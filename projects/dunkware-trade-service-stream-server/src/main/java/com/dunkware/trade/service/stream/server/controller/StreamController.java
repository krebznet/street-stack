package com.dunkware.trade.service.stream.server.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.spec.locale.DCountry;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.common.util.json.DJson;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.runtime.services.EventService;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerSpec;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerState;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerStreamStats;
import com.dunkware.trade.service.stream.server.blueprint.StreamBlueprint;
import com.dunkware.trade.service.stream.server.blueprint.StreamBlueprintService;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionException;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionFactory;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionInput;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionException;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStarted;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStopped;
import com.dunkware.trade.service.stream.server.repository.StreamEntity;
import com.dunkware.trade.service.stream.server.repository.StreamRepo;
import com.dunkware.trade.service.stream.server.repository.StreamVersionEntity;
import com.dunkware.trade.service.stream.server.spring.ConfigService;
import com.dunkware.trade.service.stream.server.tick.StreamTickService;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.service.protocol.ticker.spec.TradeTickerListSpec;
import com.dunkware.xstream.model.signal.StreamSignalListener;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.bundle.XscriptBundleHelper;
import com.dunkware.xstream.xproject.model.XScriptBundle;

public class StreamController
 {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamController");
	
	private StreamEntity ent;

	@Autowired
	private StreamRepo streamRepo;
	

	@Autowired
	private DunkNet dunkNet;

	private StreamControllerStreamStats stats = new StreamControllerStreamStats();

	private String exception = null;

	private StreamSession session = null;

	@Autowired
	private StreamControllerService service;

	private DEventNode eventNode;
	
	@Autowired
	private ExecutorService executorService;
	
	@Autowired
	private EventService eventService; 

	private TradeTickerListSpec tickerList;

	@Value("${net.cluster.server.brokers}")
	private String kafkaBrokers;

	@Autowired
	private StreamTickService tickService;
	
	@Autowired
	private StreamBlueprintService blueprintService;

	private StreamBlueprint blueprint = null;
	
	@Autowired
	private ConfigService config;
	
	private XScriptBundle scriptBundle;

	@Autowired
	private ApplicationContext ac;

	private StreamControllerSpec spec;

	private XScriptProject scriptProject;

	private StreamVersionEntity streamVersion;

	private List<TradeTickerSpec> tickers;
	
	private StreamControllerState state = StreamControllerState.Starting;
	
	private String streamException; 
	
	private LocalTime statsCacheTimestamp = null;
	
	private List<StreamSignalListener> signalListeners = new ArrayList<StreamSignalListener>();
	private Semaphore signalListenerSemaphore = new Semaphore(1);

	public StreamController() throws Exception {

	}
	
	public StreamControllerState getState() { 
		return state;
	}

	public void start(StreamEntity ent) throws Exception {
		logger.info(marker, "Starting Stream " + ent.getName());
		eventNode = eventService.getEventRoot().createChild(this);
		try {
			this.blueprint = blueprintService.getBlueprint(ent.getName());
		} catch (Exception e) {
			logger.error(marker,"Can not find stream blueprint");
			streamException = "Stream Blueprint get exep " + e.toString();
			throw e;
		}
	
		this.ent = ent;
		stats = new StreamControllerStreamStats();
		stats.setName(ent.getName());
		stats.setState(StreamControllerState.Stopped);
		streamVersion = getCurrentVersion();

		spec = DJson.getObjectMapper().readValue(ent.getSpec(), StreamControllerSpec.class);
		
		
		try {
			TradeTickerListSpec spec = tickerList = tickService.getClient().getTickerList(getEntity().getTickerLists());
			tickers = spec.getTickers();
		} catch (Exception e) {
			logger.error("Tick Service Exception Call Getting Stream Tickers for Stream {} Exception {}", getName(),
					e.toString(), e);
			stats.setError("Ticker List Get Exception " + e.toString());
			stats.setState(StreamControllerState.LoadException);
		}

		try {

			scriptBundle = DJson.getObjectMapper().readValue(streamVersion.getBundle(), XScriptBundle.class);
			scriptProject = XscriptBundleHelper.loadProject(scriptBundle);
			

		} catch (Exception e) {
			stats.setState(StreamControllerState.LoadException);
			stats.setError("Exception Building XScript Bundle " + e.toString());
			throw new Exception("Exception parsing stream version script bundle from entity " + e.toString());
		}


	}
	
	public StreamBlueprint getBlueprint() { 
		return blueprint;
	}
	
	public XScriptBundle getScriptBundle() { 
		return scriptBundle;
	}

	public DTimeZone getTimeZone() {
		return spec.getTimeZone();
	}

	public StreamVersionEntity getStreamVersion() {
		return streamVersion;
	}

	public DCountry getCountry() {
		return this.ent.getCountry();
	}

	public StreamControllerSpec getSpec() {
		return spec;
	}

	public StreamControllerService getService() {
		return service;
	}

	public StreamEntity getEntity() {
		return ent;
	}

	public String getName() {
		return ent.getName();
	}

	public XScriptProject getScriptProject() {
		return scriptProject;
	}

	public String getKafkaBrokers() {
		return kafkaBrokers;
	}

	public void stopSession() throws StreamSessionException {
		if(session == null) { 
			throw new StreamSessionException("Session Not Found");
		}
		logger.info(marker, "Stopping {} Session",getName());
		this.stats.setState(StreamControllerState.Stopping);
		session.stopSession();
	}

	public synchronized void startSession() throws StreamSessionException {
		if(session != null) { 
			
		}
		//if (getStats().getState() == StreamControllerState.Starting) {
			
		//}
		//if (getStats().getState() == StreamControllerState.Running) {
		////	throw new StreamSessionException("Stream State is " + stats.getState().name() + " cannot start session");
		//}
		logger.info(marker, "Starting {}", getName());
		stats.setState(StreamControllerState.Starting);
		session = StreamSessionFactory.createSession();
		ac.getAutowireCapableBeanFactory().autowireBean(session);
		try {
			StreamSessionInput input = new StreamSessionInput();
			input.setTickers(tickers);
			input.setController(this);
			Vector<DunkNetNode> nodes = dunkNet.getNodes("StreamWorker");
			int sleepCount = 0;
			while(nodes.size() == 0) { 
				try {
					Thread.sleep(1000);
					sleepCount++;
					if(sleepCount > 5) { 
						break;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			if (nodes.size() == 0) {
				logger.error(marker, "Exception Starting Stream {} Session, available worker nodes is 0", getName());
				stats.setState(StreamControllerState.StartException);
				stats.setError("Np Available Worker Nodes");;
				session = null;
				throw new StreamSessionException("No Available worker nodes to start stream session");
			}
			input.setWorkerNodes(nodes);
			logger.info("Stream {} Session Starting",getName());
			session.startSession(input);
			session.getEventNode().addEventHandler(this);
		} catch (StreamSessionException e) {
			stats.setState(StreamControllerState.StartException);
			stats.setError("Exception starting Stram " + getName()+  " session " + e.toString());
			this.exception = e.toString();
			throw e;
		}
	}
	public StreamSession getSession() {
		return session;
	}

	public DEventNode getEventNode() {
		return eventNode;
	}
	
	public StreamControllerStreamStats getStats() {
		if(session != null) { 
		
			stats.setSession(session.getStatus());
			stats.setName(getSession().getSessionId());
		}
		
		return stats;
		
	}

	public StreamVersionEntity getCurrentVersion() {
		List<StreamVersionEntity> versions = ent.getVersions();
		StreamVersionEntity lateset = versions.get(0);
		for (StreamVersionEntity streamVersionDO : versions) {
			if (streamVersionDO.getVersion() > lateset.getVersion()) {
				lateset = streamVersionDO;
			}
		}
		return lateset;
	}

	public List<TradeTickerSpec> getTickers() { 
		return tickerList.getTickers();
	}

	/**
	 * Notify when our session has started
	 * 
	 * @param sessionStarted
	 */
	@ADEventMethod()
	public void sessionStartedEvent(EStreamSessionStarted sessionStarted) {
		logger.info(marker, "Stream Session Started {}",getName());
		stats.setState(StreamControllerState.Running);
		ent.setState(StreamControllerState.Running);
	}
	
	
	/**
	 * Notify when session has exception
	 * 
	 * @param sessionException
	 */
	@ADEventMethod()
	public void sessionException(EStreamSessionException sessionException) {
		logger.error(marker, "Stream {} Session Could Not Start with Eception {}",getName(),sessionException.getException());
		stats.setState(StreamControllerState.StartException);

	}

	/**
	 * Notify when session is stopped
	 * 
	 * @param sessionStopped
	 */
	@ADEventMethod()
	public void sessionStopped(EStreamSessionStopped sessionStopped) {
		if (logger.isDebugEnabled()) {
			logger.debug("Recieved Session Stopped Event, Status Update Stopped");
		}
		stats.setState(StreamControllerState.Stopped);
		try {
			ent.setState(StreamControllerState.Stopped);
			ent.getSessions().add(session.getEntity());
			streamRepo.save(ent);	
		} catch (Exception e) {
			logger.error(marker, "Exception updating stream entit on session stop " + e.toString());
		}
		
	}

}
