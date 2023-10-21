package com.dunkware.trade.service.beach.server.runtime.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.runtime.BeachStream;
import com.dunkware.xstream.model.signal.StreamSignal;
import com.dunkware.xstream.model.signal.StreamSignalListener;
import com.dunkware.xstream.model.spec.StreamSpec;
import com.dunkware.xstream.model.spec.StreamSpecEntitySignal;

public class BeachStreamImpl implements BeachStream {

	@Autowired
	private BeachRuntime runtime;

	private List<StreamSignalListenerWrapper> signalListeners = new ArrayList<StreamSignalListenerWrapper>();
	private Semaphore signalListenerLock = new Semaphore(1);

	private ConcurrentHashMap<String, AtomicInteger> signalSubscriptions = new ConcurrentHashMap<String, AtomicInteger>();

	private StreamSpec streamSpec;

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	
	private Marker signalMarker = MarkerFactory.getMarker("beach.stream.signals");

	private SignalConsumer signalConsumer; 
	
	public void init(StreamSpec streamSpec) throws Exception {
		this.streamSpec = streamSpec;
		this.signalConsumer = new SignalConsumer();
		signalConsumer.init();

	}

	@Override
	public boolean signalExists(String signal) {
		for (StreamSpecEntitySignal specSignal : streamSpec.getEntitySignals()) {
			if (specSignal.getIdentifier().equalsIgnoreCase(signal)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public StreamSpec getSpec() {
		return streamSpec;
	}

	@Override
	public void addSignalListener(StreamSignalListener listener, String... signals) throws Exception {
		for (String signal : signals) {
			if (!signalExists(signal)) {
				throw new Exception(
						"Signal Listener Invalid Signal Subscription " + signal + " does not exist in stream spec");
			}
		}
		for (String signal : signals) {
			AtomicInteger count = signalSubscriptions.get(signal);
			if (count == null) {
				count = new AtomicInteger(1);
			}
			signalSubscriptions.put(signal, count);
		}
		StreamSignalListenerWrapper wrapper = new StreamSignalListenerWrapper(listener, signals);
		try {
			signalListenerLock.acquire();
			signalListeners.add(wrapper);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			signalListenerLock.release();
		}

	}

	@Override
	public void removeSignalListener(StreamSignalListener listener) {
		try {
			signalListenerLock.acquire();
			StreamSignalListenerWrapper wrapper = null;
			for (StreamSignalListenerWrapper StreamSignalListenerWrapper : signalListeners) {
				if (StreamSignalListenerWrapper.getListener().equals(listener)) {
					wrapper = StreamSignalListenerWrapper;
					break;
				}
			}
			if (wrapper != null) {
				for (String subscription : wrapper.getSubscriptions()) {
					AtomicInteger count = signalSubscriptions.get(subscription);
					if (count != null) {
						if (count.decrementAndGet() == 0) {
							signalSubscriptions.remove(subscription);
						} else {
							signalSubscriptions.put(subscription, count);
						}
					}

				}
				signalListeners.remove(wrapper);
			} else {
				logger.warn("Signal Listener Remove Request Not Found (The Listener " + listener.getClass().getName());
			}

			// decrement signal subscriptions

		} catch (Exception e) {
			logger.error("Remove Signal Listener Exception " + e.toString());
		} finally {
			signalListenerLock.release();
		}

	}

	private class StreamSignalListenerWrapper {

		private StreamSignalListener listener;
		private Map<String, String> subscriptions = new ConcurrentHashMap<String, String>();

		public StreamSignalListenerWrapper(StreamSignalListener listener, String[] subs) {
			this.listener = listener;
			for (String string : subs) {
				subscriptions.put(string, string);
			}
		}

		public boolean subscribed(String signalName) {
			if (subscriptions.containsKey(signalName)) {
				return true;
			}
			return false;
		}

		public StreamSignalListener getListener() {
			return listener;
		}

		public Collection<String> getSubscriptions() {
			return subscriptions.values();
		}

	}

	private class SignalConsumer implements DKafkaByteHandler2 {

		private DKafkaByteConsumer2 consumer;

		public void init() throws Exception {
			DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder
					.newBuilder(ConsumerType.AllPartitions, OffsetType.Latest)
					.setBrokerString(streamSpec.getEventBrokers())
					.addTopic("stream_" + streamSpec.getIdentifier() + "_event_signal")
					.setClientAndGroup("d" + DUUID.randomUUID(5), "d" + DUUID.randomUUID(6)).build();
			try {
				consumer = DKafkaByteConsumer2.newInstance(spec);
				consumer.addStreamHandler(this);
				consumer.start();
			} catch (Exception e) {
				throw new Exception("Exception connecting to stream event signal topic " + e.toString());
			}
		}

		@Override
		public void record(ConsumerRecord<String, byte[]> record) {

			GStreamEvent event = null;
			try {
				event = GStreamEvent.parseFrom(record.value());
			} catch (Exception e) {
				logger.error("Exception parsing GStreamEvent from signal topic " + e.toString());
			}
			if (event.getType() == GStreamEventType.EntitySignal) {
				GEntitySignal signal = event.getEntitySignal();
				if(signalSubscriptions.containsKey(signal.getIdentifier())) {
					StreamSignal streamSignal = new StreamSignal();
					streamSignal.setEntId(signal.getEntityId());
					streamSignal.setEntIdent(signal.getEntityIdentifier());
					streamSignal.setTime(DProtoHelper.toLocalDateTime(signal.getTime(), streamSpec.getTimeZone()));
					streamSignal.setId(signal.getId());
					streamSignal.setIdent(signal.getIdentifier());
					
					try {
						signalListenerLock.acquire();
						for (StreamSignalListenerWrapper wrapper : signalListeners) {
							if(wrapper.subscribed(signal.getIdentifier())) { 
								if(logger.isTraceEnabled()) { 
									logger.trace(signalMarker, "Signal Listener " + wrapper.getListener().getClass().getName() + " signal " + streamSignal.getIdent() + " ticker " + streamSignal.getEntIdent());
								}
								wrapper.getListener().onSignal(streamSignal);
							}
						}
					} catch (Exception e) {
						logger.error("Exception processing stream signal " + e.toString());
					} finally { 
						signalListenerLock.release();
					}
				}
			}

		}

	}

}
