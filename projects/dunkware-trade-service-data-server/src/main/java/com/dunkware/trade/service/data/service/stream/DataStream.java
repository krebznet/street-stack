package com.dunkware.trade.service.data.service.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.anot.AClusterPojoEventHandler;
import com.dunkware.trade.service.data.json.enums.DataStreamSessionState;
import com.dunkware.trade.service.data.service.config.RuntimeConfig;
import com.dunkware.trade.service.data.service.repository.DataStreamEntity;
import com.dunkware.trade.service.data.service.repository.DataStreamEntityRepo;
import com.dunkware.trade.service.data.service.stream.session.DataStreamSession;
import com.dunkware.trade.service.data.service.util.DataMarkers;
import com.dunkware.trade.service.stream.json.message.StreamSessionStart;
import com.dunkware.trade.service.stream.json.message.StreamSessionStop;

public class DataStream   {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RuntimeConfig configService;

	@Autowired
	private ApplicationContext ac;

	@Autowired
	private Cluster cluster;
	
	
	@Autowired
	private DataStreamEntityRepo streamRepo;

	private DataStreamEntity streamEntity;

	private DataStreamSession session;

	public void start(DataStreamEntity entity) {
		cluster.addComponent(this);
		this.streamEntity = entity;
		logger.info("Started Data Stream " + entity.getName());
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

	@AClusterPojoEventHandler(pojoClass = StreamSessionStart.class)
	public void sessionStart(final StreamSessionStart start) {
		logger.info("Received Stream Session Start {} ", start.getSpec().getStreamIdentifier());
		if (start.getSpec().getStreamIdentifier().equals(getName()) == false) {
			logger.info("Stream Session Start did not match session identifier");
			return;
		}
		logger.info(MarkerFactory.getMarker(start.getSpec().getSessionId()),"Recieved Session Start Event ID " + start.getSpec().getSessionId());
		
		if (!hasSession()) {
			Thread runner = new Thread() {

				public void run() {
					session = new DataStreamSession();
					ac.getAutowireCapableBeanFactory().autowireBean(session);
					try {
						logger.info("Starting Data Stream Session {} ", start.getSpec().getStreamIdentifier());
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

	@AClusterPojoEventHandler(pojoClass = StreamSessionStop.class)
	public void sessionStop(StreamSessionStop stop) {
		if (stop.getSpec().getStreamIdentifier().equals(getName()) == false) {
			return;
		}
		logger.info(MarkerFactory.getMarker(stop.getSpec().getSessionId()),"Recieved Session Stop Event ID " + stop.getSpec().getSessionId());
		
	
		if(session != null) { 
			if(session.getState() == DataStreamSessionState.Running) { 
				
				session.controllerStop();
			}
		}
	}

}
