package com.dunkwrae.trade.service.data.mock.stream.session;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.trade.service.data.json.enums.DataStreamStatus;
import com.dunkware.trade.service.stream.json.controller.model.StreamSessionSpec;
import com.dunkware.trade.service.stream.json.message.StreamSessionStart;
import com.dunkware.trade.service.stream.json.message.StreamSessionStop;
import com.dunkwrae.trade.service.data.mock.config.ConfigService;
import com.dunkwrae.trade.service.data.mock.input.MockInput;
import com.dunkwrae.trade.service.data.mock.input.MockInputSignal;
import com.dunkwrae.trade.service.data.mock.input.MockInputVar;
import com.dunkwrae.trade.service.data.mock.message.MockMessageService;
import com.dunkwrae.trade.service.data.mock.publishers.MockSignalPublisher;
import com.dunkwrae.trade.service.data.mock.publishers.MockSnapshotPublisher;
import com.dunkwrae.trade.service.data.mock.stream.MockStream;
import com.dunkwrae.trade.service.data.mock.stream.MockStreamEntity;
import com.dunkwrae.trade.service.data.mock.util.MockMarker;
import com.dunkwrae.trade.service.data.mock.util.MockMessageHelper;

public class MockSession {

	@Autowired
	private ConfigService configService;
	
	@Autowired
	private MockMessageService messageService; 

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

	private LocalTime time;

	private LocalDate date; 
	
	private ConcurrentHashMap<String, MockSessionEntity> entities = new ConcurrentHashMap<String, MockSessionEntity>();

	private MockStream stream;

	private String sessionId;

	private SessionController controller;

	private MockInput input;

	private DataStreamStatus state = DataStreamStatus.Stopped;

	private MockSignalPublisher signalPublisher;
	private MockSnapshotPublisher snapshotPublisher;

	public void start(MockStream stream) throws Exception {
		this.stream = stream;
		this.input = stream.getInput();
		this.date = LocalDate.now(DTimeZone.toZoneId(input.getSchedule().getTimeZone()));
		time = LocalTime.now(DTimeZone.toZoneId(input.getSchedule().getTimeZone()));

		String formatted = DunkTime.format(time, DunkTime.HH_MMM_SS);
		sessionId = stream.getInput().getStreamIdentifier() + "_" + formatted;
		logger.info(MockMarker.getMarker(), "Starting Mock Stream Session {}", sessionId);

		for (MockStreamEntity entity : stream.getInput().getStreamEntities()) {
			ConcurrentHashMap<String, MockSessionVar> vars = new ConcurrentHashMap<String, MockSessionVar>();
			List<MockSessionSignalFactory> signalFactories = new ArrayList<MockSessionSignalFactory>();
			MockSessionEntity sessionEnt = new MockSessionEntity();

			for (MockInputVar inputVar : stream.getInput().getVars()) {
				MockSessionVar sessionVar = new MockSessionVar(sessionEnt, inputVar);
				vars.put(inputVar.getIdentifier(), sessionVar);
			}
			// build the signal factories;
			for (MockInputSignal inputFactory : stream.getInput().getSignalFactories()) {
				if (inputFactory.handleEntity(entity.getIdentifier())) {
					signalFactories.add(inputFactory.createSignalFactory());
				}
			}

			sessionEnt.init(this, stream, entity, vars, signalFactories);
			this.entities.put(entity.getIdentifier(), sessionEnt);
		}

		String sigtopic = "stream_" + getInput().getStreamIdentifier() + "_signals_" + DunkTime.format(LocalDate.now(DTimeZone.toZoneId(stream.getInput().getSchedule().getTimeZone())),DunkTime.YYYY_MM_DD);
		String snapshot = "stream_" + getInput().getStreamIdentifier() + "_snapshots_" + DunkTime.format(LocalDate.now(DTimeZone.toZoneId(stream.getInput().getSchedule().getTimeZone())),DunkTime.YYYY_MM_DD);
		
		// create signal and snapshot publishers
		try {
			signalPublisher = new MockSignalPublisher();
			signalPublisher.start(sessionId, configService.getKafkaBrokers(), sigtopic);
			snapshotPublisher = new MockSnapshotPublisher();
			snapshotPublisher.start(sessionId, configService.getKafkaBrokers(),
					snapshot);

		} catch (Exception e) {
			throw new Exception("Exception starting session id " + sessionId + " publishers " + e.toString());
		}

		controller = new SessionController();
		controller.start();
		
		StreamSessionStart startMessage = new StreamSessionStart();
		
		StreamSessionSpec spec = MockMessageHelper.createSessionspec(this);
		startMessage.setSpec(spec);
		logger.info(MarkerFactory.getMarker("Data"), "Sending stream session start " + spec.getSessionId());
		messageService.sendMessage(startMessage);
		
		state = DataStreamStatus.Running;

	}

	public LocalDate getDate() { 
		return date;
	}
	public MockStream getStream() {
		return stream;
	}

	public MockInput getInput() {
		return input;
	}

	public DataStreamStatus getState() {
		return state;
	}
	
	public ConfigService getConfigService() { 
		return configService;
	}

	public void stop() {
		if(state == DataStreamStatus.Stopped) { 
			return;
		}
		controller.interrupt();
		signalPublisher.dispose();
		snapshotPublisher.dispose();
		logger.info(MockMarker.getMarker(), "Session Stop " + input.getStreamIdentifier());
		this.state = DataStreamStatus.Stopped;
		
		StreamSessionStop stopMessage = new StreamSessionStop();
		stopMessage.setSpec(MockMessageHelper.createSessionspec(this));
		messageService.sendMessage(stopMessage);

	}

	public String getSessionId() {
		return sessionId;
	}

	public Collection<MockSessionEntity> getEntities() {
		return entities.values();
	}

	public LocalTime getTime() {
		return time;
	}

	private void entitySignal(MockSessionEntity entity, MockSessionSignal signal) {
		signalPublisher.publish(entity, signal, this);
	}

	private void entitySnapshot(MockSessionEntity entity) {
			snapshotPublisher.publish(entity, this);
	}

	private class SessionController extends Thread {

		public void run() {
			long loopCount = 0;
			while (!interrupted()) {
				try {
					Thread.sleep(input.getUpdateInterval());

				} catch (Exception e) {
					// TODO: handle exception
				}
				if (loopCount > 0) {
					time = time.plusSeconds(1);
				}
				loopCount++;

				for (MockSessionEntity entity : entities.values()) {
					entity.update();
					List<MockSessionSignal> signals = entity.getSignals();
					for (MockSessionSignal mockSessionSignal : signals) {
						entitySignal(entity, mockSessionSignal);
					}

					entitySnapshot(entity);
					entity.clearSignals();
				}

				// now it will need to publish;
			}
		}
	}

}
