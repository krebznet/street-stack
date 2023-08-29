package com.dunkware.trade.service.beach.server.runtime;

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
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;

public class BeachStream {

	@Autowired
	private BeachRuntime runtime;
	
	

	private List<BeachSignalListenerWrapper> signalListeners = new ArrayList<BeachSignalListenerWrapper>();
	private Semaphore signalListenerLock = new Semaphore(1);

	private ConcurrentHashMap<String, AtomicInteger> signalSubscriptions = new ConcurrentHashMap<String, AtomicInteger>();

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

	private Marker signalMarker = MarkerFactory.getMarker("beach.stream.signals");

	private SignalConsumer signalConsumer;

	private String identifier; 
	public void init(String identifier) throws Exception {
		this.identifier = identifier;
		this.signalConsumer = new SignalConsumer();
		signalConsumer.init();

	}

	public String getIdentifier() {
		return identifier;
	}
	
	public void addSignalListener(BeachSignalListener listener, String... signals) throws Exception {
	
		for (String signal : signals) {
			AtomicInteger count = signalSubscriptions.get(signal);
			if (count == null) {
				count = new AtomicInteger(1);
			}
			signalSubscriptions.put(signal, count);
		}
		BeachSignalListenerWrapper wrapper = new BeachSignalListenerWrapper(listener, signals);
		try {
			signalListenerLock.acquire();
			signalListeners.add(wrapper);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			signalListenerLock.release();
		}

	}

	public void removeSignalListener(BeachSignalListener listener) {
		try {
			signalListenerLock.acquire();
			BeachSignalListenerWrapper wrapper = null;
			for (BeachSignalListenerWrapper StreamSignalListenerWrapper : signalListeners) {
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

	private class BeachSignalListenerWrapper {

		private BeachSignalListener listener;
		private Map<String, String> subscriptions = new ConcurrentHashMap<String, String>();

		public BeachSignalListenerWrapper(BeachSignalListener listener, String[] subs) {
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

		public BeachSignalListener getListener() {
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
					.setBrokerString(runtime.getStreamBrokers())
					.addTopic("stream_" + identifier + "_event_signal")
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

		
				
			

		}

	}

}
