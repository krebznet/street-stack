package com.dunkware.trade.service.stream.server.controller;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.java.utils.glazed.grid.GlazedDataGridConnector;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.runtime.services.EventService;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeBean;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerSpec;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerStats;
import com.dunkware.trade.service.stream.json.controller.spec.StreamState;
import com.dunkware.trade.service.stream.server.controller.anot.AStreamControllerExt;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionException;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionFactory;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionInput;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionEvent;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStartException;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStarted;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStopped;
import com.dunkware.trade.service.stream.server.repository.StreamEntity;
import com.dunkware.trade.service.stream.server.repository.StreamRepo;
import com.dunkware.trade.service.stream.server.repository.StreamVersionEntity;
import com.dunkware.trade.service.stream.server.spring.ConfigService;
import com.dunkware.trade.service.stream.server.tick.StreamTickService;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerType;
import com.dunkware.trade.tick.service.protocol.ticker.spec.TradeTickerListSpec;
import com.dunkware.utils.core.concurrent.DunkExecutorCountLatch;
import com.dunkware.utils.core.event.EventNode;
import com.dunkware.utils.core.events.anot.ADunkEventHandler;
import com.dunkware.utils.core.helpers.DunkAnot;
import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.utils.core.time.DunkTime;
import com.dunkware.utils.core.time.DunkTimeZones;
import com.dunkware.utils.core.time.clock.DZonedClock;
import com.dunkware.utils.core.time.clock.DZonedClockListener;
import com.dunkware.utils.core.time.clock.DZonedClockUpdater;
import com.dunkware.xstream.model.signal.StreamEntitySignalListener;
import com.dunkware.xstream.model.signal.type.XStreamSignalType;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.bundle.XscriptBundleHelper;
import com.dunkware.xstream.xproject.model.XScriptBundle;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

//TODO: AVINASHANV-17 Distributed stream computing
/**
 * this is where we have a service that deploys scripts and each stream 
 * has a schedule. it is impossible to compute all entities in a single 
 * XScript runtime so what this does is it gets all available worker nodes
 * and distributes the entities evenly across them and then a sub kafka 
 * topic is created for each node that sends market data for the entities
 * its handling. this is how we can compute 8,000 stocks at the same time
 * 
 * a stream runs in a stream session. 
 */
public class StreamController   {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamController");
	private Marker scheduleMarker = MarkerFactory.getMarker("StreamSchedule");
	private Marker stopTrace = MarkerFactory.getMarker("StreamStopTrace");

	private StreamEntity ent;

	@Autowired
	private StreamRepo streamRepo;

	@Autowired
	private DunkNet dunkNet;

	@Autowired
	private EventService eventService;

	private String exception = null;

	private StreamSession session = null;

	@Autowired
	private StreamControllerService service;

	@Autowired
	private ExecutorService executorService;

	private TradeTickerListSpec tickerList;

	@Autowired
	private StreamTickService tickService;

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
	
	private EventNode eventNode; 

	private List<StreamEntitySignalListener> signalListeners = new ArrayList<StreamEntitySignalListener>();
	
	private Semaphore signalListenerSemaphore = new Semaphore(1);

	private StreamSchedule schedule;
	
	private ZoneId zoneId;
	
	private ObservableElementList<StreamSessionNodeBean> sessionNodeBeans = null;

	
	private List<StreamControllerExt> extensions = new ArrayList<StreamControllerExt>();
	
	
	public StreamController() throws Exception {
		
	}	

	public StreamState getState() {
		return stats.getState();
	}

	public LocalTime getTime() { 
		return LocalTime.now(zoneId);
	}
	
	public LocalDateTime getDateTime() { 
		return LocalDateTime.now(zoneId);
	}
	
	public ObservableElementList<StreamSessionNodeBean> getSessionNodeBeans() { 
		return sessionNodeBeans;
	}
	
	
	public EventNode getEventNode() { 
		return eventNode;
	}
	public void start(StreamEntity ent) throws Exception {
		
		eventNode = eventService.getEventRoot().createChild(this);
		
		sessionNodeBeans = new ObservableElementList<StreamSessionNodeBean>(
				GlazedLists.threadSafeList(new BasicEventList<StreamSessionNodeBean>()),
				new GlazedDataGridConnector<StreamSessionNodeBean>());
		
		logger.info(marker, "Starting Stream " + ent.getName());
		
		try {
			//this.blueprint = blueprintService.getBlueprint(ent.getName());
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

		spec = DunkJson.getObjectMapper().readValue(ent.getSpec(), StreamControllerSpec.class);
		try {
			zoneId = DunkTimeZones.getZoneId(spec.getTimeZone());
		} catch (Exception e) {
			logger.error("Exception creating time zone from stream spec " + e.toString());
			stats.setState(StreamState.ControllerException);;
			throw e;
		}
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

			scriptBundle = DunkJson.getObjectMapper().readValue(streamVersion.getBundle(), XScriptBundle.class);
			scriptProject = XscriptBundleHelper.loadProject(scriptBundle);

		} catch (Exception e) {
			stats.setState(StreamState.ControllerException);
			stats.setStreamException("Exception Building XScript Bundle " + e.toString());
			throw new Exception("Exception parsing stream version script bundle from entity " + e.toString());
		}
			
		// create extensions 
		Set<Class<?>> extClasses = DunkAnot.getClassesAnnotedWith(AStreamControllerExt.class); 
		for (Class<?> class1 : extClasses) {
			try {
				StreamControllerExt ext = (StreamControllerExt)class1.newInstance();
				ac.getAutowireCapableBeanFactory().autowireBean(ext);
				extensions.add(ext);
			} catch (Exception e) {
				logger.error("exception creating extension class {} exception {}",class1.getName(),e.toString());
			}
		}
		
		for (StreamControllerExt ext : extensions) {
			try {
				ext.initialize(this);
			} catch (Exception e) {
				logger.error("exception init extenson {}, error {}",ext.getClass().getName(),e.toString());
			}
		}
		
		
		if (config.isScheduleStreams()) {
			schedule = new StreamSchedule();
			logger.info("Starting Stream  {} controller scheudle",ent.getName());
			Runnable runner = new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(1000);
						schedule.start();

					} catch (Exception e) {
						logger.error("Exception starting schedule in runnable " + e.toString(), e);
					}

				}
			};

			if (config.isScheduleStreams()) {

				Thread runnerThread = new Thread(runner);
				runnerThread.start();
			} else {

			}
		}

	}
	
	

	
	public ZoneId getTimeZone() {
		return zoneId;
	}


	
	
	
	
	public int getExchangeId() {
		return (int)ent.getId();
	}


	public void setTickers(String...tickers) throws Exception {
		
		List<TradeTickerSpec> specs = new ArrayList<TradeTickerSpec>();
		
		for (String ticker : tickers) {
			TradeTickerSpec spec = new TradeTickerSpec();
			spec.setId(-1);
			spec.setSymbol(ticker);
			spec.setName(ticker);
			spec.setType(TradeTickerType.Equity);
			spec.setAvgVolume(0);
			specs.add(spec);
		}
		TradeTickerListSpec fuck = new TradeTickerListSpec();
		fuck.setId(-1);
		fuck.setName("Override");
		fuck.setSize(tickers.length);
		fuck.setTickers(specs);
		this.tickerList = fuck; 
	}

	

	public XScriptBundle getScriptBundle() {
		return scriptBundle;
	}


	public StreamVersionEntity getStreamVersion() {
		return streamVersion;
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
	
	public int getTickerId(String ident) throws Exception { 
		for (TradeTickerSpec spec : tickers) {
			if(spec.getSymbol().equalsIgnoreCase(ident)) {
				return spec.getId();
			}
		}
		throw new Exception("Trade ticker ident " + ident + " not found on stream controller");
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
		if(session != null) { 
			setState(session.getState());;
		}
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
		sessionNodeBeans.getReadWriteLock().writeLock().lock();
		sessionNodeBeans.clear();
		sessionNodeBeans.getReadWriteLock().writeLock().unlock();
		logger.info(marker, "Starting {}", getName());
		session = StreamSessionFactory.createSession();
		ac.getAutowireCapableBeanFactory().autowireBean(session);
		List<XStreamSignalType> signalTypes = null;
		try {
			//signalTypes = blueprint.getXStreamSignalTypes();
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
					nodes = dunkNet.getNodes("StreamWorker");
					if (sleepCount > 5) {
						break;
					}
				} catch (Exception e) {
					
				}
			}
			if (nodes.size() == 0) {
				logger.error(marker, "Exception Starting Stream {} Session, available worker nodes is 0", getName());
				logger.error(marker, "Exxception did return {} nodes total" + nodes.size());
				try {
					Thread.sleep(10000);
					
				} catch (Exception e) {
					
				}
				
				stats.setState(StreamState.StartException);
				stats.setStartException("0 Workers Node Discovered");
				
				
				session = null;
				return;
			}
			input.setWorkerNodes(nodes);
			logger.info("Stream {} Session Starting", getName());
			
			
			session.startSession(input);
			session.getEventNode().adDunkEventHandler(this);
			
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


	public StreamControllerStats getStats() {
		if (session != null) {

			stats.setSession(session.getStats());
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
	@ADunkEventHandler
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
	
	@ADunkEventHandler
	public void sessionStartException(EStreamSessionStartException exp) {
		this.stats.getErrors().add(exp.getError());
	}

	@ADunkEventHandler
	public void sessionEvent(EStreamSessionEvent event) {
		setState(event.getSession().getState());
	}
	
	@ADunkEventHandler
	public void sessionStarted(EStreamSessionStarted started) { 
		for (StreamSessionNode streamSessionNodeBean : session.getNodes()) {
			try {
				//sessionNodeBeans.getReadWriteLock().writeLock().lock();
				//sessionNodeBeans.add(streamSessionNodeBean.getBean());
			} catch (Exception e) {
				
			} finally {
			//	sessionNodeBeans.getReadWriteLock().writeLock().unlock();
			}
		}
	}
	

	
	private void setState(StreamState state) { 
		this.stats.setState(state);;
	}
	
	
	public boolean inSession() { 
		if(session == null) { 
			return false; 
		}
		if(session.getState() == StreamState.Running || session.getState() == StreamState.Stopping) { 
			return true;
		}
		return false;
	}
	
	private class StreamSchedule extends Thread implements DZonedClockListener {

		private List<DayOfWeek> days = new ArrayList<DayOfWeek>();
		private ZonedDateTime lastDateTime;
		private volatile boolean inSession = false;
		private volatile boolean isSessionDay = false;

		private LocalTime stopTime;
		private LocalTime startTime;
		// come on dude! last fuckin piece
		
		private Marker marker = MarkerFactory.getMarker("stream.controller.schedule");

		private DunkExecutorCountLatch latch = new DunkExecutorCountLatch();

		private DZonedClockUpdater clockUpdater;
		private DZonedClock clock;

		public StreamSchedule() throws Exception {
			// populate days of week
			String[] ids = spec.getDays().split(",");
			for (String id : ids) {
				days.add(DayOfWeek.of(Integer.valueOf(id)));
			} // StreamHookWebService /sream/hook/session/start
				///
				// set start and stop time
			startTime = spec.getStartTime();
			stopTime = spec.getStopTime();

		}

		public void specUpdate() {
			latch.increment();
			// set the new start/stop time
			this.stopTime = spec.getStopTime();
			this.startTime = spec.getStartTime();
			// set the new market days
			String[] ids = spec.getDays().split(",");
			days.clear();
			for (String id : ids) {
				days.add(DayOfWeek.of(Integer.valueOf(id)));
			}
			boolean wasSessionDay = isSessionDay;
			newDay();
			// if not session day but in session need to stopSession();
			if (wasSessionDay && !isSessionDay) {
				if (inSession) {
					inSession = false;
					try {
						logger.info(scheduleMarker,"Schedule Stopping Stram {} Session",getName());
						stopSession();
					} catch (Exception e) {
						logger.error("Exception Stopping Session In Schedule Spec Update " + e.toString(), e);
					}

				}
			}
			latch.decrement();
		}

		public void run() {
			clockUpdater = DZonedClockUpdater.now(zoneId, 1, TimeUnit.SECONDS);
			clock = clockUpdater.getClock();
			lastDateTime = ZonedDateTime.of(clock.getDateTime(),getTimeZone());
			newDay();
			if (isSessionDay) {
				// do we start a half baked session ? 
				if (clock.isAfterLocalTime(startTime) && clock.isBeforeLocalTime(stopTime)) {
					try {
						if (logger.isInfoEnabled()) {
							logger.info(scheduleMarker,"Starting Stream after start time of {} current time is {} stream is {}",startTime.toString(),DunkTime.format(lastDateTime.toLocalDateTime(), DunkTime.YYMMDDHHMMSS), ent.getName());
						}
						startSession();
						inSession = true;
					} catch (Exception e) {
						logger.error(marker,"{}Stream Session Schedule Starting Stream Exception " + e.toString(),
								ent.getName(), e);
					}

				} else {
					if (logger.isDebugEnabled()) {
						logger.debug(marker,"{} Schedule Starting, after start/stop time for day", ent.getName());
					}
				}
			} else {
				logger.debug("Starting Scheudle on no session day");
			}
			clock.addListener(this);
		}

		/**
		 * Updates boolean flag isSessionDay
		 */
		private void newDay() {
			isSessionDay = false;
			DayOfWeek today = clockUpdater.getClock().getDayOfWeek();
			
			if (days.contains(today)) {
				if (logger.isDebugEnabled()) {
					logger.debug(marker,"{} Scheduler New Day Set Session Day True", ent.getName());
				}
				if(today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY) { 
					logger.error("In Session Day But Today is Saturday Or Sunday WTF");
				}
				isSessionDay = true;
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug(marker,"{} Scheudler New Day Set Session Day False", ent.getName());

				}
			}
		}
		
		@Override
		public void clockUpdate(DZonedClock clock) {
			try {
				latch.waitTillZero();
			} catch (Exception e) {
				// interrupted so what?
			}
			if (clock.getDateTime().toLocalDate().isAfter(lastDateTime.toLocalDate())) {
				newDay();
			}
			if (inSession) {
				if (clock.getDateTime().toLocalTime().isAfter(stopTime)) {
					// STOP SESSIOn:
					try {
						
						inSession = false;
						logger.info(scheduleMarker,"Schedule Stopping Stream {} Session", getName());
						stopSession();
					} catch (Exception e) {
						logger.error(scheduleMarker,"Stream Schedule Stopping {} Exception {}", ent.getName(), e.toString(), e);
					}
				}
			} else { // else not in session
				if(!isSessionDay) {
					// DUMB FUCK HERE IS BUG 
					return;
				}
				// not in session look for start if its after start time and before stop time
				if (clock.isAfterLocalTime(startTime) && clock.isBeforeLocalTime(stopTime)) {
					
					try {
						
						if (!inSession()) {
							logger.info(
									scheduleMarker,"Starting session on clock update where current time is between start/stop time and status is not running or starting");
							startSession();
							inSession = true;
							return;
						}
					} catch (Exception e) {
						logger.error("{} Exception Starting Session In Schedule " + e.toString(), ent.getName(), e);
					}

				}
			}

		}

		public void dispose() {
			// delete a stream controller ?
			if (schedule != null) {
				schedule.interrupt();
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Stream Session Schedule Disposing {}", ent.getName());
			}
			clockUpdater.getClock().removeListener(this);
			clockUpdater.dispose();
		}

	}
	
	
	
}
