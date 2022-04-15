package com.dunkware.trade.service.data.service.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.trade.service.data.json.enums.DataStreamSessionState;
import com.dunkware.trade.service.data.service.config.RuntimeConfig;
import com.dunkware.trade.service.data.service.message.StreamMessageHandler;
import com.dunkware.trade.service.data.service.message.StreamMessageService;
import com.dunkware.trade.service.data.service.repository.DataServiceRepository;
import com.dunkware.trade.service.data.service.repository.DataStreamEntity;
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
	private StreamMessageService messageService;

	@Autowired
	private DataServiceRepository dataRepo;

	private DataStreamEntity streamEntity;

	private DataStreamSession session;

	public void start(DataStreamEntity entity) {
		this.streamEntity = entity;
		messageService.addHandler(this);
		
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

		if (!hasSession()) {
			Thread runner = new Thread() {

				public void run() {
					session = new DataStreamSession();
					ac.getAutowireCapableBeanFactory().autowireBean(session);
					try {
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
		if (session.getState() == DataStreamSessionState.Completed) {
			// okay create new session
			Thread runner = new Thread() {

				public void run() {
					session = new DataStreamSession();
					ac.getAutowireCapableBeanFactory().autowireBean(session);
					try {
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
		// else we already have a running session why 
		logger.error("getting start session message on a sesssion that is not in completed status");

	}

	@Override
	public void sessionStop(StreamSessionStop stop) {

	}

}
