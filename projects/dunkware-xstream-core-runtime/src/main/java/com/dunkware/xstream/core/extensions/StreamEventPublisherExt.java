package com.dunkware.xstream.core.extensions;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.net.proto.stream.GStreamTimeUpdate;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamClock;
import com.dunkware.xstream.api.XStreamClockListener;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamExtension;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRowListener;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.core.annotations.AXStreamExtension;
import com.dunkware.xstream.util.XStreamEventHelper;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

@AXStreamExtension(type = StreamEventPublisherExtType.class)
public class StreamEventPublisherExt implements XStreamExtension, XStreamRowListener {

	private XStream stream;
	private StreamEventPublisherExtType type;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private BlockingQueue<GStreamEvent> publishQueue = new LinkedBlockingQueue<GStreamEvent>();
	// listen for signals;
	// need status here -- Signal Event Count - Snapshot Event Count -

	private Map<String,AtomicInteger> snapshotCounts = new ConcurrentHashMap<String, AtomicInteger>();
	private DKafkaByteProducer snapshotProducer;
	private EventPublisher eventPublisher;
	private EntitySnapshotBuilder snapshotRunnable;
	private DKafkaByteProducer timeProducer;
	private DKafkaByteProducer signalProducer;
	
	private BlockingQueue<RowSnapshot> snapshotQueue = new LinkedBlockingQueue<RowSnapshot>();
	private BlockingQueue<XStreamRowSignal> signalQueue = new LinkedBlockingQueue<XStreamRowSignal>();

	private TimeListener timeListener;
	private TimePublisher timePublisher = new TimePublisher();

	private BlockingQueue<LocalDateTime> timeUpdateQueue = new LinkedBlockingQueue<LocalDateTime>();

	private Map<String, List<XStreamRowSignal>> snapshotSignals = new ConcurrentHashMap<String, List<XStreamRowSignal>>();

	private SignalPublisher signalPublisher; 
	private List<SnapshotConsumer> snapshotConsumers = new ArrayList<SnapshotConsumer>();
	
	private Map<String,LocalDateTime> lastSnapshots = new ConcurrentHashMap<String, LocalDateTime>();
	
	@Override
	public void init(XStream stream, XStreamExtensionType type) throws XStreamException {
		this.stream = stream;
		this.type = (StreamEventPublisherExtType) type;
		try {
			snapshotProducer = DKafkaByteProducer.newInstance(this.type.getKafkaBrokers(), this.type.getSnapshotTopic(),
					this.type.getKafkaIdentifier() + DUUID.randomUUID(4));
		} catch (Exception e) {
			logger.error("Exception Creating Kafka Producer From Brokers " + this.type.getKafkaBrokers() + e.toString(),
					e);
			throw new XStreamException("Stream Event Publisher Init Kafka Connection Failed " + e.toString());
		}
		try {
			signalProducer = DKafkaByteProducer.newInstance(this.type.getKafkaBrokers(), this.type.getSignalTopic(),
					this.type.getKafkaIdentifier());
		} catch (Exception e) {
			logger.error("Exception Creating Kafka Producer From Brokers " + this.type.getKafkaBrokers() + e.toString(),
					e);
			throw new XStreamException("Stream Event Publisher Init Kafka Connection Failed " + e.toString());
		}
		try {
			timeProducer = DKafkaByteProducer.newInstance(this.type.getKafkaBrokers(), this.type.getTimeTopic(),
					this.type.getKafkaIdentifier());
		} catch (Exception e) {
			logger.error("Exception Creating Kafka Producer From Brokers " + this.type.getKafkaBrokers() + e.toString(),
					e);
			throw new XStreamException("Stream Event Publisher Init Kafka Connection Failed " + e.toString());
		}

	}

	@Override
	public void preStart() throws XStreamException {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() throws XStreamException {

		eventPublisher = new EventPublisher();
		eventPublisher.start();
		
		timeListener = new TimeListener();
		stream.getClock().addListener(timeListener);
		timePublisher.start();

		stream.addRowListener(this);

		snapshotRunnable = new EntitySnapshotBuilder();
		//stream.getClock().scheduleRunnable(snapshotRunnable, 1);
		
		
		signalPublisher = new SignalPublisher();
		signalPublisher.start();
		int count = 4; 
		int i = 0;
		while(i < count) { 
			SnapshotConsumer con = new SnapshotConsumer();
			con.start();
			snapshotConsumers.add(con);
			i++;
		}

	}

	@Override
	public void preDispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		int sleepCount = 0;
		while (!publishQueue.isEmpty() == false) {
			try {
				Thread.sleep(1000);
				sleepCount++;
			} catch (Exception e) {
				// TODO: handle exceptiond
			}
			if (sleepCount > 30) {
				logger.error("Disposing Stream Event Publisher Timed out on Event Publish Queeu, look at code");
				break;
			}
		}

		snapshotProducer.dispose();
		signalProducer.dispose();
		timePublisher.interrupt();
		timeProducer.dispose();
		eventPublisher.interrupt();
		stream.getClock().unscheduleRunnable(snapshotRunnable);
		signalPublisher.interrupt();
		
		for (SnapshotConsumer consumer : snapshotConsumers) {
			consumer.interrupt();
		}

	}

	@Override
	public void rowSignal(final XStreamRow row, final XStreamRowSignal signal) {
		// add it to our snapshot queue
		if (snapshotSignals.get(row.getId()) == null) {
			List<XStreamRowSignal> signals = new ArrayList<XStreamRowSignal>();
			signals.add(signal);
			this.snapshotSignals.put(row.getId(), signals);
		} else {
			List<XStreamRowSignal> signals = snapshotSignals.get(row.getId());
			signals.add(signal);
			snapshotSignals.put(row.getId(), signals);

		}
		signalQueue.add(signal);
		
	}
	
	

	private class TimeListener implements XStreamClockListener {

		@Override
		public void timeUpdate(XStreamClock clock, LocalDateTime time) {
				logger.debug(MarkerFactory.getMarker("TimeUpdate"), DunkTime.toStringTimeStamp(time));
				timeUpdateQueue.add(time);
				// in the same thread iterating through rows
				// can't be that long 
				for (XStreamRow row : stream.getRows()) {
					RowSnapshot snapshot = new RowSnapshot();
					snapshot.row = row;
					snapshot.time = time;
					snapshot.signals = snapshotSignals.remove(row.getId());
					snapshotQueue.add(snapshot);
				}
		}

	}

	private class TimePublisher extends Thread {

		private LocalDateTime lastUpdate = null;
		public void run() {
			while (!interrupted()) {
				try {
					LocalDateTime time = timeUpdateQueue.take();
					GStreamEvent event = GStreamEvent.newBuilder().setType(GStreamEventType.TimeUpdate)
					.setTimeUpdate(GStreamTimeUpdate.newBuilder()
							.setTime(DProtoHelper.toTimeStamp(time, stream.getInput().getTimeZone())).build())
					.build();
					timeProducer.sendBytes(event.toByteArray());
					if(lastUpdate != null) { 
						Duration duration = Duration.between(lastUpdate, time);
						if(duration.getSeconds() > 1) { 
							logger.error("Stream Clock Not Right, Last Time receieved was {} current is {}",DunkTime.toStringTimeStamp(lastUpdate),DunkTime.toStringTimeStamp(time));
						}
					}
					lastUpdate = time;
				} catch (Exception e) {
					logger.error("Exception sending time update " + e.toString());
					// TODO: handle exception
				}
			}
		}
	}

	// use system time? 
	public class EntitySnapshotBuilder implements Runnable {
		public void run() {
			try {
				for (XStreamRow row : stream.getRows()) {
					try {
						// so here #1 we need to pull signals of the queue
						// update the schema to NewEntitySnapshotPublish have signals
						// then clear the queue
						List<XStreamRowSignal> signals = snapshotSignals.remove(row.getId());
						
						if (signals == null) {
							signals = new ArrayList<XStreamRowSignal>();
						}

						//GStreamEvent event = XStreamEventHelper.buildEntitySnapshotEvent(row,
						//		DTimeZone.toZoneId(stream.getInput().getTimeZone()), signals);
						//publishQueue.add(event);
					} catch (Exception e) {
						logger.error("Exception creating entity snapshot event " + e.toString());
						// TODO: handle exception
					}
				}
			} catch (Exception e) {
				logger.error("Exception Taking Row Snapshots " + e.toString());
			}
		}
	}
	
	private class SignalPublisher extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					XStreamRowSignal signal = signalQueue.take();
					GStreamEvent event = XStreamEventHelper.buildEntitySignalEvent(signal.getRow(), signal);
					signalProducer.sendBytes(event.toByteArray());
					
				} catch (Exception e) {
					if (e instanceof InterruptedException) { 
						return;
					}
					logger.error("Exception taking/publishing signal " + e.toString());
				}
			}
		}
	}
	
	
	
	private class RowSnapshot { 
		
		public List<XStreamRowSignal> signals;
		public LocalDateTime time; 
		public XStreamRow row; 
	}
	
	
	private class SnapshotConsumer extends Thread { 
		
		public void run() { 
			
			while(!interrupted()) { 
				try {
					RowSnapshot snapshot = snapshotQueue.take();
					LocalDateTime lastSnapshotTime = lastSnapshots.get(snapshot.row.getId());
					if(lastSnapshotTime != null) { 
						if(lastSnapshotTime.isEqual(snapshot.time)) { 
							logger.error(MarkerFactory.getMarker("SnapshotDuplicate"), "sending a snapshot on {} with same time stamp as last snapshot " + DunkTime.toStringTimeStamp(lastSnapshotTime) + " " + DunkTime.toStringTimeStamp(snapshot.time),snapshot.row);
							lastSnapshotTime = snapshot.time;
							continue;
						}
					}
					lastSnapshots.put(snapshot.row.getId(), snapshot.time);
					GStreamEvent event = XStreamEventHelper.buildEntitySnapshotEvent(snapshot.row,
							DTimeZone.toZoneId(stream.getInput().getTimeZone()), snapshot.signals,snapshot.time);
					if(logger.isDebugEnabled()) { 
						logger.debug(MarkerFactory.getMarker("SnapshotPublish"), "{} {} {}",snapshot.row.getId(),DunkTime.toStringTimeStamp(snapshot.time),type.getNode());
					}
					publishQueue.add(event);
				} catch (Exception e) {
					if (e instanceof InterruptedException) { 
						return;
					}
					logger.error("Exception in snapshot consumer " + e.toString());
				}

				
			}
		}
		
	}

	public class EventPublisher extends Thread {

		public void run() {
		
			while (!interrupted()) {
				
				GStreamEvent event = null;
				try {
					event = publishQueue.take();
				} catch (Exception e) {
					logger.error("Exception taking event from  " + e.toString());
					continue;
				}

				try {
					if (event.getType() == GStreamEventType.EntitySnapshot) {
						try {
							snapshotProducer.sendBytes(event.toByteArray());							
						} catch (Exception e) {
							logger.error("Exception sending message on snapshot producer " + e.toString());
						}

					}

				} catch (Exception e) {
					logger.error("Exception Publishing Stream Event " + e.toString());
				}

			}
		}
	}

	// needs a row signal map
	// <entiyidentifier,List<signals>)

}
