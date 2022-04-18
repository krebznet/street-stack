package com.dunkware.trade.service.data.service.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.net.cluster.node.trace.TraceLogger;
import com.dunkware.net.cluster.node.trace.TraceService;
import com.dunkware.trade.service.data.json.enums.DataStreamSessionState;
import com.dunkware.trade.service.data.service.config.RuntimeConfig;
import com.dunkware.trade.service.data.service.message.StreamMessageHandler;
import com.dunkware.trade.service.data.service.message.StreamMessageService;
import com.dunkware.trade.service.data.service.repository.DataStreamEntity;
import com.dunkware.trade.service.data.service.repository.DataStreamEntityRepo;
import com.dunkware.trade.service.data.service.util.DataMarkers;
import com.dunkware.trade.service.stream.json.message.StreamSessionStart;
import com.dunkware.trade.service.stream.json.message.StreamSessionStop;

public class DataStream implements StreamMessageHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RuntimeConfig configService;

	@Autowired
	private ApplicationContext ac;

	@Autowired
	private TraceService trace; 
	
	@Autowired
	private StreamMessageService messageService;

	@Autowired
	private DataStreamEntityRepo streamRepo;

	private DataStreamEntity streamEntity;

	private DataStreamSession session;

	private TraceLogger traceLogger; 
	public void start(DataStreamEntity entity) {
		this.streamEntity = entity;
		messageService.addHandler(this);
		traceLogger = trace.logger(getClass());
		traceLogger.addLabel("Stream", entity.getName());
	}

	public DataStreamSession getSession() {
		return session;
	}

	public boolean hasSession() {
		if (session == null) {
			return false;
		}
		return true;
	}

	public DTimeZone getTimeZone() {
		return DTimeZone.NewYork;
	}

	public String getName() {
		return streamEntity.getName();
	}

	public DataStreamEntity getEntity() {
		return streamEntity;
	}

	@Override
	public void sessionStart(final StreamSessionStart start) {

		if (start.getSpec().getStreamIdentifier().equals(getName()) == false) {
			return;
		}
		
		traceLogger.info("Receieved Session {} Start Message", start.getSpec().getSessionId());

		if (!hasSession()) {
			Thread runner = new Thread() {

				public void run() {
					session = new DataStreamSession();
					ac.getAutowireCapableBeanFactory().autowireBean(session);
					try {
						session.init();
						session.controllerStart(DataStream.this, start.getSpec());
					} catch (Exception e) {
						logger.error(DataMarkers.getServiceMarker(),
								"Exception starting stream session on session start " + e.toString());
					}

				}
			};

			runner.start();
			return;
		}

		// okay create new session
		Thread runner = new Thread() {

			public void run() {
				session = new DataStreamSession();
				ac.getAutowireCapableBeanFactory().autowireBean(session);
				try {
					session.init();
					session.controllerStart(DataStream.this, start.getSpec());
				} catch (Exception e) {
					logger.error(DataMarkers.getServiceMarker(),
							"Exception starting stream session on session start " + e.toString());
				}

			}
		};

		runner.start();

	}

	@Override
	public void sessionStop(StreamSessionStop stop) {
		if (stop.getSpec().getStreamIdentifier().equals(getName()) == false) {
			return;
		}
		traceLogger.info("Received Session {}", stop.getSpec().getSessionId());
		if(session != null) { 
			if(session.getState() == DataStreamSessionState.Running) { 
				traceLogger.info("Calling controllerStop on session");
				session.controllerStop();
			}
		}
	}

}
