package com.dunkware.trade.service.stream.server.controller;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
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
import com.dunkware.net.proto.stream.GStreamSpec;
import com.dunkware.trade.service.stream.json.controller.spec.StreamSpec;
import com.dunkware.trade.service.stream.json.controller.spec.StreamStatsSpec;
import com.dunkware.trade.service.stream.json.controller.spec.StreamStatus;
import com.dunkware.trade.service.stream.server.controller.repository.StreamDO;
import com.dunkware.trade.service.stream.server.controller.repository.StreamVersionDO;
import com.dunkware.trade.service.stream.server.controller.util.GStreamHelper;
import com.dunkware.trade.service.stream.server.logging.LogService;
import com.dunkware.trade.service.stream.server.session.StreamSession;
import com.dunkware.trade.service.stream.server.session.StreamSessionException;
import com.dunkware.trade.service.stream.server.session.StreamSessionFactory;
import com.dunkware.trade.service.stream.server.session.StreamSessionInput;
import com.dunkware.trade.service.stream.server.session.events.EStreamSessionException;
import com.dunkware.trade.service.stream.server.session.events.EStreamSessionStarted;
import com.dunkware.trade.service.stream.server.session.events.EStreamSessionStopped;
import com.dunkware.trade.service.stream.server.spring.ConfigService;
import com.dunkware.trade.service.stream.server.spring.ExecutorService;
import com.dunkware.trade.service.stream.server.spring.RuntimeService;
import com.dunkware.trade.service.stream.server.tick.StreamTickService;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.service.protocol.ticker.spec.TradeTickerListSpec;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.bundle.XscriptBundleHelper;
import com.dunkware.xstream.xproject.model.XScriptBundle;

public class StreamController {

	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

	private StreamDO ent;

	private StreamSchedule schedule;

	private StreamStatsSpec stats = new StreamStatsSpec();

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

	private String kafkaBrokers;

	@Autowired
	private StreamTickService tickService;

	@Autowired
	private ConfigService config;
	
	@Autowired
	private ApplicationContext ac;

	private StreamSpec spec;

	private GStreamSpec gStreamSpec = null;
	
	private XScriptProject scriptProject; 
	
	private StreamVersionDO streamVersion;

	public StreamController() throws Exception {

	}

	public void start(StreamDO ent) throws Exception {
		// set member variabbes
		this.ent = ent;
		stats = new StreamStatsSpec();
		stats.setStream(ent.getName());
		stats.setStatus(StreamStatus.Stopped);
		streamVersion = getCurrentVersion();
	
			String bundle = streamVersion.getBundle();
			try {
				
				XScriptBundle bund = DJson.getObjectMapper().readValue(streamVersion.getBundle(),
						XScriptBundle.class);
				scriptProject = XscriptBundleHelper.loadProject(bund);
			
				
			} catch (Exception e) {
				stats.setStatus(StreamStatus.Exception);
				stats.setError("Exception Building XScript Bundle " + e.toString());
				throw new Exception("Exception parsing stream version script bundle from entity " +
				e.toString());
			}
			
		
		// create/init stats
		
		spec = DJson.getObjectMapper().readValue(ent.getSpec(), StreamSpec.class);
		this.specUpdate(spec);
		
		if(config.isScheduleStreams()) { 
			schedule = new StreamSchedule();

			Runnable runner = new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(5000);
						schedule.start();

					} catch (Exception e) {
						logger.error("Exception starting schedule in runnable " + e.toString(), e);
					}

				}
			};
			
			 if(config.isScheduleStreams()) {
			
				Thread runnerThread = new Thread(runner);
				runnerThread.start();
			} else {
				
			}	
		}
		
			

	}
	
	public DTimeZone getTimeZone() { 
		return spec.getTimeZone();
	}
	
	public StreamVersionDO getStreamVersion() { 
		return streamVersion;
	}
	
	public DCountry getCountry() { 
		return this.ent.getCountry();
	}

	public StreamSpec getSpec() {
		return spec;
	}

	public StreamControllerService getService() {
		return service;
	}

	public StreamDO getEntity() {
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
		if (getStatus() != StreamStatus.Running && getStatus() != StreamStatus.Starting) {
			throw new StreamSessionException("Cannot stop session when stream is not running or startiung");
		}
		try {
			session.stopSession();
		} catch (Exception e) {
			throw e;
		}
		session = null;
		stats.setStatus(StreamStatus.Stopped);

	}

	public synchronized void startSession() throws StreamSessionException {
		// validate we are in a status that can start session
		if (getStatus() == StreamStatus.Starting || getStatus() == StreamStatus.Running ) {
			logger.error("Fart Bug , startSession being called while status is " + getStatus().name());
			return;
		}
		stats.setStatus(StreamStatus.Starting);
		// create session / set session stats
		session = StreamSessionFactory.createSession();
		ac.getAutowireCapableBeanFactory().autowireBean(session);
		stats.setSession(session.getStats());
		try {
			// if the session throws an exception if there
			stats.setStatus(StreamStatus.Starting);
			int tickers = getTickerList().getSize(); 
			int nodeTickerLimit = config.getSessionNodeTickerLimit();
			int nodeCount = 0;
			int tickerCount = 0;
			for (TradeTickerSpec ticker : getTickerList().getTickers()) {
				tickerCount++;
				if(tickerCount == nodeTickerLimit) { 
					nodeCount++;
					tickerCount = 0;
				}
			}
			StreamSessionInput input = new StreamSessionInput();
			
			session.startSession(this);
			// need to call this after start session annoying
			session.getEventNode().addEventHandler(this);
		} catch (StreamSessionException e) {
			stats.setStatus(StreamStatus.Exception);
			stats.setError("Exception starting session " + e.toString());
			this.exception = e.toString();
		}
	}
	
	public TradeTickerListSpec getTickerList() throws Exception { 
		if(tickerList == null) { 
			tickerList = tickService.getClient().getTickerList(getEntity().getTickerLists());
		}
		return tickerList;
		
	}

	public StreamSession getSession() {
		return session;
	}

	public StreamStatsSpec getStats() {
		// update session stats
		if(session != null) { 
			stats.setSession(session.getStats());
		}
		return stats;
	}

	public StreamVersionDO getCurrentVersion() {
		List<StreamVersionDO> versions = ent.getVersions();
		StreamVersionDO lateset = versions.get(0);
		for (StreamVersionDO streamVersionDO : versions) {
			if (streamVersionDO.getVersion() > lateset.getVersion()) {
				lateset = streamVersionDO;
			}
		}
		return lateset;
	}

	public StreamStatus getStatus() {
		return stats.getStatus();
	}

	public void specUpdate(StreamSpec spec) {
		this.spec = spec;
		try {
			tickerList = tickService.getClient().getTickerList(getEntity().getTickerLists());
		} catch (Exception e) {
			logger.error("Exception building ticker list on spec update " + e.toString());
		}
		
		try {
			gSpec = GStreamHelper.build(this);
		} catch (Exception e) {
			logger.error("Exception building g stream spec on spec init " + e.toString());
		}
		
		
		if(schedule == null) { 
			return;
		}
		if (scheduleEnabled)
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
				if (clock.isAfterLocalTime(startTime) && clock.isBeforeLocalTime(stopTime)) {
					try {
						if (logger.isInfoEnabled()) {
							logger.info("Stream Session Schedule Starting Stream {}", ent.getName());
						}
						startSession();
						inSession = true;
					} catch (Exception e) {
						logger.error("{}Stream Session Schedule Starting Stream Exception " + e.toString(), ent.getName(), e);
					}

				} else {
					if (logger.isDebugEnabled()) {
						logger.debug("{} Schedule Starting, after start/stop time for day", ent.getName());
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
			DayOfWeek today = clockUpdater.getClock().getDayOfWeek();
			if (days.contains(today)) {
				if (logger.isDebugEnabled()) {
					logger.debug("{} Scheduler New Day Set Session Day True", ent.getName());
				}
				isSessionDay = true;
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("{} Scheudler New Day Set Session Day False", ent.getName());

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
						if (logger.isInfoEnabled()) {
							logger.info("Stream Schedule Stopping Stream {}", ent.getName());
						}
						inSession = false;
						stopSession();
					} catch (Exception e) {
						logger.error("Stream Schedule Stopping {} Exception {}", ent.getName(), e.toString(), e);
					}
				}
			} else {
				// not in session look for start if its after start time and before stop time
				if (clock.isAfterLocalTime(startTime) && clock.isBeforeLocalTime(stopTime)) {
					
					try {
						if(getStatus() != StreamStatus.Starting || getStatus() != StreamStatus.Running) { 
							logger.info("Starting session on clock update where current time is between start/stop time and status is not running or starting");
							startSession();
							inSession = true;
							logger.error("Fart Bug, clock update is calling start session while session is in " + getStatus().name());
							return;
						} 
					} catch (Exception e) {
						logger.error("{} Exception Starting Session In Schedule " + e.toString(), ent.getName(), e);
					}

				}
			}

		}

		public void dispose() {
			//TODO: delete a stream controller ?
			if(schedule != null) { 
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
			if(logger.isDebugEnabled()) {
				logger.debug("Recieved Session Started Event, Status Update Running");
			}
			stats.setStatus(StreamStatus.Running);
	}

	/**
	 * Notify when session has exception
	 * 
	 * @param sessionException
	 */
	@ADEventMethod()
	public void sessionException(EStreamSessionException sessionException) {
		if(logger.isDebugEnabled()) { 
			logger.debug("Recieved Session Exception Event " + sessionException.getSession().getStats().getException());
		}
		stats.setStatus(StreamStatus.Exception);
	}

	/**
	 * Notify when session is stopped
	 * 
	 * @param sessionStopped
	 */
	@ADEventMethod()
	public void sessionStopped(EStreamSessionStopped sessionStopped) {
		if(logger.isDebugEnabled()) {
			logger.debug("Recieved Session Stopped Event, Status Update Stopped");
		}
		stats.setStatus(StreamStatus.Stopped);
	}

}
