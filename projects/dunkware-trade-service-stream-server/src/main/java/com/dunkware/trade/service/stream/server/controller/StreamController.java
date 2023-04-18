package com.dunkware.trade.service.stream.server.controller;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.spec.locale.DCountry;
import com.dunkware.common.util.concurrency.ReusableCountDownLatch;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.dtime.DZonedClock;
import com.dunkware.common.util.dtime.DZonedClockListener;
import com.dunkware.common.util.dtime.DZonedClockUpdater;
import com.dunkware.common.util.dtime.DZonedDateTime;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.common.util.json.DJson;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.proto.stream.GStreamSpec;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerSpec;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerState;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerStreamStats;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionException;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionFactory;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionInput;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionEvent;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionException;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStarted;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStopped;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStopping;
import com.dunkware.trade.service.stream.server.controller.util.GStreamHelper;
import com.dunkware.trade.service.stream.server.repository.StreamEntity;
import com.dunkware.trade.service.stream.server.repository.StreamRepo;
import com.dunkware.trade.service.stream.server.repository.StreamVersionEntity;
import com.dunkware.trade.service.stream.server.spring.ConfigService;
import com.dunkware.trade.service.stream.server.spring.RuntimeService;
import com.dunkware.trade.service.stream.server.tick.StreamTickService;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.service.protocol.ticker.spec.TradeTickerListSpec;
import com.dunkware.xstream.model.signal.StreamSignal;
import com.dunkware.xstream.model.signal.StreamSignalListener;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.bundle.XscriptBundleHelper;
import com.dunkware.xstream.xproject.model.XScriptBundle;

public class StreamController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

	private StreamEntity ent;

	@Autowired
	private StreamRepo streamRepo;
	
	@Value("${session.worker.nodes}")
	private String confgiredWorkerNodes; 
	
	private StreamSchedule schedule;

	private StreamControllerStreamStats stats = new StreamControllerStreamStats();

	private String exception = null;

	private StreamSession session = null;

	@Autowired
	private StreamControllerService service;

	@Autowired
	private Cluster cluster;

	@Autowired
	private RuntimeService runtimeService;

	private GStreamSpec gSpec;

	private TradeTickerListSpec tickerList;

	@Value("${net.cluster.server.brokers}")
	private String kafkaBrokers;

	@Autowired
	private StreamTickService tickService;

	@Autowired
	private ConfigService config;
	
	private XScriptBundle scriptBundle;

	@Autowired
	private ApplicationContext ac;

	private StreamControllerSpec spec;

	private GStreamSpec gStreamSpec = null;

	private XScriptProject scriptProject;

	private StreamVersionEntity streamVersion;

	private List<TradeTickerSpec> tickers;
	
	private Marker marker = MarkerFactory.getMarker("StreamController");
	
	
	
	private List<StreamSignalListener> signalListeners = new ArrayList<StreamSignalListener>();
	private Semaphore signalListenerSemaphore = new Semaphore(1);

	public StreamController() throws Exception {

	}
	

	public void start(StreamEntity ent) throws Exception {
		logger.info(":Starting Stream Controller " + ent.getName());
		
		// set member variabbes
		this.ent = ent;
		stats = new StreamControllerStreamStats();
		stats.setName(ent.getName());
		stats.setState(StreamControllerState.Stopped);
		streamVersion = getCurrentVersion();

		spec = DJson.getObjectMapper().readValue(ent.getSpec(), StreamControllerSpec.class);
		this.specUpdate(spec);
		
		try {
			TradeTickerListSpec spec = tickerList = tickService.getClient().getTickerList(getEntity().getTickerLists());
			tickers = spec.getTickers();
		} catch (Exception e) {
			logger.error("Tick Service Exception Call Getting Stream Tickers for Stream {} Exception {}", getName(),
					e.toString(), e);
			stats.setError("Ticker List Get Exception " + e.toString());
			stats.setState(StreamControllerState.Exception);
		}

		String bundle = streamVersion.getBundle();
		try {

			scriptBundle = DJson.getObjectMapper().readValue(streamVersion.getBundle(), XScriptBundle.class);
			scriptProject = XscriptBundleHelper.loadProject(scriptBundle);
			

		} catch (Exception e) {
			stats.setState(StreamControllerState.Exception);
			stats.setError("Exception Building XScript Bundle " + e.toString());
			throw new Exception("Exception parsing stream version script bundle from entity " + e.toString());
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
		logger.info(MarkerFactory.getMarker(session.getSessionId()), "Stopping {} Session",getName());
		session.stopSession();

	}

	public synchronized void startSession() throws StreamSessionException {
		// validate we are in a status that can start session
		if (getStats().getState() == StreamControllerState.Starting || getStats().getState() == StreamControllerState.Running) {
			throw new StreamSessionException("Stream State is " + stats.getState().name() + " cannot start session");
		}
		stats.setState(StreamControllerState.PendingStarting);
		// create session / set session stats
		session = StreamSessionFactory.createSession();
		ac.getAutowireCapableBeanFactory().autowireBean(session);
		
		try {
			
			StreamSessionInput input = new StreamSessionInput();
			// TOODO: here right -->
			input.setTickers(tickers);
			input.setController(this);
			//cluster.getNodeSevice().getAvailableWorkerNodes();
			List<ClusterNode> nodes = new ArrayList<ClusterNode>();
			try {
				Thread.sleep(2500);	
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			String[] configedNodes = confgiredWorkerNodes.split(",");
			try {
				for (String nodeId : configedNodes) {
					ClusterNode node = cluster.getNode(nodeId);
					if(node == null) { 
						logger.error("Stream Worker Configured Node not found " + nodeId);
				 	} else { 
				 		nodes.add(node);
				 	}
				} 
	
			} catch (Exception e) {
				logger.error("exception requesting worker nodes " + e.toString());
				throw new StreamSessionException("Exception getting worker nodes " + e.toString());
			}

			if (nodes.size() == 0) {
				logger.error("Exception Starting Stream {} Session, available worker nodes is 0", getName());
				stats.setState(StreamControllerState.Exception);
				stats.setError("Np Available Worker Nodes");;
				session = null;
				throw new StreamSessionException("No Available worker nodes to start stream session");
			}
			input.setWorkerNodes(nodes);
			logger.info("Stream {} Scheduler Starting Session",getName());
			session.startSession(input);
		//	stats.setSession(session.getStatus());
			// need to call this after start session annoying
			session.getEventNode().addEventHandler(this);
		} catch (StreamSessionException e) {
			stats.setState(StreamControllerState.Exception);
			stats.setError("Exception starting Stram " + getName()+  " session " + e.toString());
			this.exception = e.toString();
			throw e;
		}
	}
	public StreamSession getSession() {
		return session;
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

	public StreamControllerState getState() {
		return stats.getState();
	}

	public void specUpdate(StreamControllerSpec spec) {
		this.spec = spec;
		try {
			tickerList = tickService.getClient().getTickerList(getEntity().getTickerLists());
		} catch (Exception e) {
			logger.error("Exception building ticker list on spec update " + e.toString());
		}

		try {
			gSpec = GStreamHelper.build(this);
		} catch (Exception e) {
			logger.error("Exception building g stream spec on spec update for stream {} " + e.toString(),getName());
		}

		if (schedule == null) {
			return;
		}
		if (config.isScheduleStreams())
			this.schedule.specUpdate();

	}

	public GStreamSpec getGStreamSpec() {
		return gSpec;
	}

	private class StreamSchedule extends Thread implements DZonedClockListener {

		private List<DayOfWeek> days = new ArrayList<DayOfWeek>();
		private DZonedDateTime lastDateTime;
		private volatile boolean inSession = false;
		private volatile boolean isSessionDay = false;

		private DTime stopTime;
		private DTime startTime;
		// come on dude! last fuckin piece
		
		private Marker marker = MarkerFactory.getMarker("stream.controller.schedule");

		private ReusableCountDownLatch latch = new ReusableCountDownLatch();

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
						logger.info("Schedule Stopping Stram {} Session",getName());
						stopSession();
					} catch (Exception e) {
						logger.error("Exception Stopping Session In Schedule Spec Update " + e.toString(), e);
					}

				}
			}
			latch.decrement();
		}

		public void run() {
			clockUpdater = DZonedClockUpdater.now(spec.getTimeZone(), 1, TimeUnit.SECONDS);
			clock = clockUpdater.getClock();
			lastDateTime = clockUpdater.getClock().getDateTime();
			newDay();
			if (isSessionDay) {
				// do we start a half baked session ? 
				if (clock.isAfterLocalTime(startTime) && clock.isBeforeLocalTime(stopTime)) {
					try {
						if (logger.isInfoEnabled()) {
							logger.info("Stream Session Schedule Starting Stream {}", ent.getName());
						}
						logger.info("Schedule Starting Stream Session for Stream " + getName());
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
				if (clock.getDateTime().toLocalTime().get().isAfter(stopTime.get())) {
					// STOP SESSIOn:
					try {
						
						inSession = false;
						logger.info(marker,"Schedule Stopping Stream {} Session", getName());
						stopSession();
					} catch (Exception e) {
						logger.error(marker,"Stream Schedule Stopping {} Exception {}", ent.getName(), e.toString(), e);
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
						if (getStats().getState() != StreamControllerState.Starting || getStats().getState()!= StreamControllerState.Running) {
							logger.info(
									marker,"Starting session on clock update where current time is between start/stop time and status is not running or starting");
							startSession();
							inSession = true;
							logger.error(marker,"Fart Bug, clock update is calling start session while session is in "
									+ getStats().getName());
							return;
						}
					} catch (Exception e) {
						logger.error("{} Exception Starting Session In Schedule " + e.toString(), ent.getName(), e);
					}

				}
			}

		}

		public void dispose() {
			// TODO: delete a stream controller ?
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

	/**
	 * Notify when our session has started
	 * 
	 * @param sessionStarted
	 */
	@ADEventMethod()
	public void sessionStartedEvent(EStreamSessionStarted sessionStarted) {
		if (logger.isDebugEnabled()) {
			logger.debug("Recieved Session Started Event, Status Update Running");
		}
		stats.setState(StreamControllerState.Running);
		ent.setState(StreamControllerState.Running);
		streamRepo.save(ent);
		
		
	}
	
	public List<TradeTickerSpec> getTickers() { 
		return tickerList.getTickers();
	}

	/**
	 * Notify when session has exception
	 * 
	 * @param sessionException
	 */
	@ADEventMethod()
	public void sessionException(EStreamSessionException sessionException) {
		if (logger.isDebugEnabled()) {
			logger.debug("Recieved Session Exception Event " + sessionException.getSession().getStatus().getException());
		}
		stats.setState(StreamControllerState.Exception);
		// sessionException state
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
		ent.setState(StreamControllerState.Stopped);
		ent.getSessions().add(session.getEntity());
		streamRepo.save(ent);
	}
	
	@ADEventMethod()
	public void sessionStopping(EStreamSessionStopping stopping) {
		if (logger.isDebugEnabled()) {
			logger.debug("Recieved Session Stopped Event, Status Update Stopped");
		}
		stats.setState(StreamControllerState.Stopping);
		
	}
	
	public void sessionEvent(EStreamSessionEvent event) { 
		if (event instanceof EStreamSessionStopped) { 
			sessionStopped((EStreamSessionStopped)event);
			
		}
		if (event instanceof EStreamSessionStopping) { 
			sessionStopping((EStreamSessionStopping)event);
			
		}
		if (event instanceof EStreamSessionStarted) { 
			sessionStartedEvent((EStreamSessionStarted)event);
		}
	}
	
	public void addSignalListener(final StreamSignalListener listener) { 
		Runnable adder = new Runnable() {
			
			@Override
			public void run() {
				boolean result = true;
				try {
					 result = signalListenerSemaphore.tryAcquire(5, TimeUnit.SECONDS);
					if(!result) { 
						logger.error("Code error getting semaphore in signal listener add ");
						return;
					}
					signalListeners.add(listener);
				} catch (Exception e) {
					logger.error("Exception getting signal listener semaphore add exception " + e.toString());
				} finally { 
					if(result) { 
						signalListenerSemaphore.release();
					}
				}
			}
		};
		this.cluster.getExecutor().execute(adder);
	}
	
	
	public void removeSignalListener(final StreamSignalListener listener) { 
	Runnable remover = new Runnable() {
			
			@Override
			public void run() {
				boolean result = true;
				try {
					 result = signalListenerSemaphore.tryAcquire(5, TimeUnit.SECONDS);
					if(!result) { 
						logger.error("Code error getting semaphore in signal listener remove ");
						return;
					}
					signalListeners.remove(listener);
				} catch (Exception e) {
					logger.error("Exception getting signal listener semaphore remove exception " + e.toString());
				} finally { 
					if(result) { 
						signalListenerSemaphore.release();
					}
				}
			}
		};
		this.cluster.getExecutor().execute(remover);
	}
	
	/**
	 * Okay called by the stream session somehow
	 * @param signal
	 */
	public void signal(final StreamSignal signal) { 
		Runnable signalNotifier = new Runnable() {
			
			@Override
			public void run() {
				boolean result = true;
				try {
					 result = signalListenerSemaphore.tryAcquire(5, TimeUnit.SECONDS);
					if(!result) { 
						logger.error("Code error getting semaphore in signal notifier  ");
						return;
					}
					for (StreamSignalListener streamSignalListener : signalListeners) {
						streamSignalListener.onSignal(signal);
					}
				} catch (Exception e) {
					logger.error("Exception getting signal listener semaphore notify exception " + e.toString());
				} finally { 
					if(result) { 
						signalListenerSemaphore.release();
					}
				}
			}
		};
		
		cluster.getExecutor().execute(signalNotifier);
	}

}
