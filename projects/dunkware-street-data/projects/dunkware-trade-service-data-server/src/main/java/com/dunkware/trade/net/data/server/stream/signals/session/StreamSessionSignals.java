package com.dunkware.trade.net.data.server.stream.signals.session;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.trade.net.data.server.stream.signals.StreamSignalListener;
import com.dunkware.trade.net.data.server.stream.signals.StreamSignals;
import com.dunkware.trade.service.data.model.signals.StreamSessionSignalTypeBeans;
import com.dunkware.xstream.model.signal.StreamEntitySignal;

public class StreamSessionSignals implements StreamSignalListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamSignals");

	private Map<Integer, StreamSessionSignalType> signalTypes = new ConcurrentHashMap<Integer, StreamSessionSignalType>();

	private BlockingQueue<StreamEntitySignal> signalQueue = new LinkedBlockingQueue<StreamEntitySignal>();

	private int signalConsumeCounter = 0;

	private StreamSignals signals;

	public void init(StreamSignals signals) {
		try {
			this.signals = signals;
			logger.info(marker, "Starting Session Signals");
			signals.getSignalIngestor().addSignalListener(this);

		} catch (Exception e) {
			logger.error(marker, "Exception starting stream session signals " + e.toString());
		}
	}

	@Override
	public void onSignal(StreamEntitySignal signal) {
		try {
			signalQueue.add(signal);
		} catch (Exception e) {
			if (e instanceof InterruptedException) {
				return;
			}
			logger.error(marker, "Exception takign siganl from qu " + e.toString());
		}
	}

	public StreamSessionSignalTypeBeans getSignalTypeBeans() {
		StreamSessionSignalTypeBeans beans = new StreamSessionSignalTypeBeans();
		for (StreamSessionSignalType type : signalTypes.values()) {
			beans.getBeans().add(type.getBean());

		}
		return beans;
	}

	public StreamSignals getStreamSignals() {
		return signals;

	}

	private class SnapshotLogger extends Thread {
		public void run() {
			try {
				int count = signalConsumeCounter;
				while (!interrupted()) {
					Thread.sleep(30000);
					if (logger.isDebugEnabled()) {
						logger.debug(marker, " ");
					}
				}
			} catch (Exception e) {
			}

		}

		private class SignalHandler extends Thread {

			public void run() {
				setName("Stream Session Signal Handler");
				while (!interrupted()) {
					try {
						StreamEntitySignal sig = signalQueue.take();
						try {
							signalConsumeCounter++;
							StreamSessionSignalType b = signalTypes.get(sig.getId());
							if (b == null) {
								b = new StreamSessionSignalType(sig.getId());
								b.signal(sig);
								signalTypes.put(sig.getId(), b);
							} else {
								signalTypes.get(sig.getId()).signal(sig);
								;
							}
						} catch (Exception e) {
							if (e instanceof InterruptedException) {
								return;
							}
							logger.error(marker, "Exception processing signal  " + e.toString());
							;

						}
					} catch (Exception e) {
						if (e instanceof InterruptedException) {
							return;
						}
						logger.error(marker, "Exception in signal heandler  " + e.toString());

					}
				}

			}
		}
	}
}
