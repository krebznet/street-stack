package com.dunkwrae.trade.service.data.mock.stream;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.concurrency.ReusableCountDownLatch;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DZonedClock;
import com.dunkware.common.util.dtime.DZonedClockListener;
import com.dunkware.common.util.dtime.DZonedClockUpdater;
import com.dunkware.common.util.dtime.DZonedDateTime;
import com.dunkware.trade.service.data.json.enums.DataStreamStatus;
import com.dunkwrae.trade.service.data.mock.input.MockInput;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSession;

public class MockStream {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	private MockInput input ;
	private MockSession session = null;
	private boolean running = false; 
	private SessionController controller;
	
	@Autowired
	private ApplicationContext ac;
	
	public void init(MockInput input)  throws Exception { 
		this.input = input; 
	//	controller = new SessionController();
	//	controller.start();
	}

	public MockInput getInput() { 
		return input;
	}
	
	public boolean isRunning() { 
		return running; 
	}
	
	public MockSession getSession() { 
		return session; 
	}
	
	
	public MockSession startSession() throws Exception {
		if(session != null) { 
			if(session.getState() == DataStreamStatus.Running) { 
				throw new Exception("Session found with running state");
			}
		}
		 session = new MockSession();
		 ac.getAutowireCapableBeanFactory().autowireBean(session);
		session.start(this);
		running = true;
		return session;
	}
	
	
	public void stopSession() throws Exception { 
		if(session == null) { 
			running = false;
			throw new Exception("Session not found");
		}
		try {
			session.stop();
			session = null;
				
		} catch (Exception e) {
			
		}
		running = false;
		
	}
	
	// going to have a schedule here just like StreamController 
	// build a session; start it then message bus. 
	
	private class SessionController extends Thread implements DZonedClockListener {

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

		public SessionController() throws Exception {
			// populate days of week
			String[] ids = input.getSchedule().getDays();
			for (String id : ids) {
				days.add(DayOfWeek.of(Integer.valueOf(id)));
			} // StreamHookWebService /sream/hook/session/start
			  /// 
			// set start and stop time
			startTime = DTime.from(input.getSchedule().getStart());
			stopTime = DTime.from(input.getSchedule().getStop());

		}

		public void run() {
			clockUpdater = DZonedClockUpdater.now(input.getSchedule().getTimeZone(), 1, TimeUnit.SECONDS);
			clock = clockUpdater.getClock();
			lastDateTime = clockUpdater.getClock().getDateTime();
			newDay();
			if (isSessionDay) {
				if (clock.isAfterLocalTime(startTime) && clock.isBeforeLocalTime(stopTime)) {
					try {
						if (logger.isInfoEnabled()) {
							logger.info("Mock Stream Session Scheudlign Starting Stream {}", input.getStreamIdentifier());
						}
						startSession();
						inSession = true;
					} catch (Exception e) {
						logger.error("{}Mock Stream Session Schedule Starting Stream Exception " + e.toString(), input.getStreamIdentifier(),e);
					}

				} else {
					if (logger.isDebugEnabled()) {
						logger.debug("{} Mock Stream Session Starting, after start/stop time for day", input.getStreamIdentifier());
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
					logger.debug("{} Scheduler New Day Set Session Day True", input.getStreamIdentifier());
				}
				isSessionDay = true;
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("{} Scheudler New Day Set Session Day False", input.getStreamIdentifier());

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
							logger.info("Mock Stream {} Schedule Stopping Session ID {}", input.getStreamIdentifier(), "sessionidhere");
						}
						inSession = false;
						stopSession();
					} catch (Exception e) {
						logger.error("Mock Stream Schedule Stopping {} Exception {}", input.getStreamIdentifier(), e.toString(), e);
					}
				}
			} else {
				// not in session look for start if its after start time and before stop time
				if (clock.isAfterLocalTime(startTime) && clock.isBeforeLocalTime(stopTime)) {
					
					// TODO: start session if not already started. 
				}
			}

		}

		public void dispose() {
			//TODO: delete a stream controller ? 
			if (logger.isDebugEnabled()) {
				logger.debug("Stream Session Schedule Disposing {}", input.getStreamIdentifier());
			}
			clockUpdater.getClock().removeListener(this);
			clockUpdater.dispose();
		}

	}

	
}

