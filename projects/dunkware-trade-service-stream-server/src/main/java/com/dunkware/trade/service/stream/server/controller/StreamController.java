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
import org.springframework.context.ApplicationContext;

import com.dunkware.common.spec.locale.DCountry;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.observable.ObservableBeanListConnector;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.runtime.services.EventService;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeBean;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerSpec;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerStats;
import com.dunkware.trade.service.stream.json.controller.spec.StreamState;
import com.dunkware.trade.service.stream.server.blueprint.StreamBlueprint;
import com.dunkware.trade.service.stream.server.blueprint.StreamBlueprintService;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionException;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionFactory;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionInput;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionEvent;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStartException;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStopped;
import com.dunkware.trade.service.stream.server.repository.StreamEntity;
import com.dunkware.trade.service.stream.server.repository.StreamRepo;
import com.dunkware.trade.service.stream.server.repository.StreamVersionEntity;
import com.dunkware.trade.service.stream.server.spring.ConfigService;
import com.dunkware.trade.service.stream.server.tick.StreamTickService;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.service.protocol.ticker.spec.TradeTickerListSpec;
import com.dunkware.xstream.model.signal.StreamEntitySignalListener;
import com.dunkware.xstream.model.signal.type.XStreamSignalType;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.bundle.XscriptBundleHelper;
import com.dunkware.xstream.xproject.model.XScriptBundle;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

public class StreamController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamController");

	private StreamEntity ent;

	@Autowired
	private StreamRepo streamRepo;

	@Autowired
	private DunkNet dunkNet;


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

	private StreamControllerStats stats = new StreamControllerStats();

	private String streamException;

	private LocalTime statsCacheTimestamp = null;

	private List<StreamEntitySignalListener> signalListeners = new ArrayList<StreamEntitySignalListener>();
	
	private Semaphore signalListenerSemaphore = new Semaphore(1);

	private ObservableElementList<StreamSessionNodeBean> sessionNodeBeans = null;

	public StreamController() throws Exception {

	}

	public StreamState getState() {
		return stats.getState();
	}

	public void start(StreamEntity ent) throws Exception {
		
		sessionNodeBeans = new ObservableElementList<StreamSessionNodeBean>(
				GlazedLists.threadSafeList(new BasicEventList<StreamSessionNodeBean>()),
				new ObservableBeanListConnector<StreamSessionNodeBean>());
		
		logger.info(marker, "Starting Stream " + ent.getName());
		eventNode = eventService.getEventRoot().createChild(this);
		eventNode.addEventHandler(this);
		try {
			this.blueprint = blueprintService.getBlueprint(ent.getName());
		} catch (Exception e) {
			logger.error(marker, "Can not find stream blueprint");
			streamException = "Stream Blueprint get exep " + e.toString();
			throw e;
		}

		this.ent = ent;
		stats = new StreamControllerStats();
		stats.setName(ent.getName());
		stats.setState(StreamState.Stopped);
		streamVersion = getCurrentVersion();

		spec = DJson.getObjectMapper().readValue(ent.getSpec(), StreamControllerSpec.class);

		try {
			TradeTickerListSpec spec = tickerList = tickService.getClient().getTickerList(getEntity().getTickerLists());
			tickers = spec.getTickers();
		} catch (Exception e) {
			logger.error("Tick Service Exception Call Getting Stream Tickers for Stream {} Exception {}", getName(),
					e.toString(), e);
			stats.setStreamException("Ticker List Get Exception " + e.toString());
			stats.setState(StreamState.ControllerException);
		}

		try {

			scriptBundle = DJson.getObjectMapper().readValue(streamVersion.getBundle(), XScriptBundle.class);
			scriptProject = XscriptBundleHelper.loadProject(scriptBundle);

		} catch (Exception e) {
			stats.setState(StreamState.ControllerException);
			stats.setStreamException("Exception Building XScript Bundle " + e.toString());
			throw new Exception("Exception parsing stream version script bundle from entity " + e.toString());
		}

	}

	public StreamBlueprint getBlueprint() {
		return blueprint;
	}
	
	public ObservableElementList<StreamSessionNodeBean> getSessionNodeBeans() {
		return sessionNodeBeans;
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
		return dunkNet.getConfig().getServerBrokers();
	}

	public void stopSession() throws StreamSessionException {
		if (session == null) {
			throw new StreamSessionException("Session Not Found");
		}
		logger.info(marker, "Stopping {} Session", getName());
		this.stats.setState(StreamState.Stopping);
		session.stopSession();
	}

	
	public String killSession() { 
		if(session == null) { 
			return "No Current Session";
		}
		setState(StreamState.RunKill);
		return session.killSession();
		
	
		
	}
	
	private boolean canStart() { 
		StreamState state = getState();
		if(state == StreamState.Running) {
			return false;
		}
		if(state == StreamState.Stopping) {
			return false;
		}
		if(state == StreamState.ControllerException) {
			return false;
		}
		return true;
	}

	
	public synchronized void startSession() throws StreamSessionException {
		if(!canStart()) { 
			throw new StreamSessionException("Cannot start stream in state " + getState());
		}
		logger.info(marker, "Starting {}", getName());
		session = StreamSessionFactory.createSession();
		ac.getAutowireCapableBeanFactory().autowireBean(session);
		List<XStreamSignalType> signalTypes = null;
		try {
			signalTypes = blueprint.getXStreamSignalTypes();
		} catch (Exception e) {
			throw new StreamSessionException("Exception building XStreamSignalTypes " + e.toString());
		}
		stats.setState(StreamState.Starting);
		try {
			StreamSessionInput input = new StreamSessionInput();
			input.setSignalTypes(signalTypes);
			input.setTickers(tickers);
			input.setController(this);
			Vector<DunkNetNode> nodes = dunkNet.getNodes("StreamWorker");
			int sleepCount = 0;
			while (nodes.size() == 0) {
				try {
					Thread.sleep(1000);
					sleepCount++;
					if (sleepCount > 5) {
						break;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			if (nodes.size() == 0) {
				logger.error(marker, "Exception Starting Stream {} Session, available worker nodes is 0", getName());
				stats.setState(StreamState.StartException);
				stats.setStartException("0 Workers Node Discovered");
				session = null;
				return;
			}
			input.setWorkerNodes(nodes);
			logger.info("Stream {} Session Starting", getName());
			sessionNodeBeans.clear();
			session.startSession(input);
			session.getEventNode().addEventHandler(this);
		} catch (StreamSessionException e) {
			stats.setState(session.getState());
			stats.setStartException("Exception starting Stram " + getName() + " session " + e.toString());
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

	public StreamControllerStats getStats() {
		if (session != null) {

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
	 * Notify when session is stopped
	 * 
	 * @param sessionStopped
	 */
	@ADEventMethod()
	public void sessionStopped(EStreamSessionStopped sessionStopped) {
		if (logger.isDebugEnabled()) {
			logger.debug("Recieved Session Stopped Event, Status Update Stopped");
		}
		try {
			ent.setState(StreamState.Stopped);
			ent.getSessions().add(session.getEntity());
			streamRepo.save(ent);
		} catch (Exception e) {
			logger.error(marker, "Exception updating stream entit on session stop " + e.toString());
		}

	}
	
	@ADEventMethod()
	public void sessionStartException(EStreamSessionStartException exp) {
		this.stats.getErrors().add(exp.getError());
	}

	@ADEventMethod()
	public void sessionEvent(EStreamSessionEvent event) {
		setState(event.getSession().getState());
	}

	
	private void setState(StreamState state) { 
		this.stats.setState(state);;
	}
}
