package com.dunkware.trade.net.data.server.stream.signals.sessionn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.trade.net.data.server.stream.signals.StreamSignalIngestorListener;
import com.dunkware.trade.net.data.server.stream.signals.StreamSignals;
import com.dunkware.trade.net.data.server.stream.signals.StreamSignalsHelper;
import com.dunkware.trade.net.data.server.stream.signals.sessionn.query.SessionSignalQueryGrid;
import com.dunkware.trade.net.data.server.stream.signals.sessionn.query.SessionSignalTypeQueryGrid;
import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalSessionQuery;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalTypeSessionQuery;
import com.dunkware.xstream.model.signal.StreamEntitySignal;

/**
 * Okay this gets created on startup, session stop will reset it
 * 
 * @author duncankrebs
 *
 */
public class StreamSignalsSessionImpl implements StreamSignalsSession, StreamSignalIngestorListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamSignals");

	private BlockingQueue<StreamEntitySignal> signalQueue = new LinkedBlockingQueue<StreamEntitySignal>();

	private int signalConsumeCounter = 0;
	private SignalHandler signalHandler; 

	private StreamSignals signals;

	/**
	 * Signal beans
	 */
	private List<StreamSignalBean> signalBeans = new ArrayList<StreamSignalBean>();
	private Semaphore signalBeansLock = new Semaphore(1);

	public void init(StreamSignals signals) {
		try {
			this.signals = signals;
			logger.info(marker, "Starting Session Signals");
			signals.getSignalIngestor().addSignalListener(this);
			signalHandler = new SignalHandler();
			signalHandler.start();

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

	/**
	 * Okay this has stream signals
	 */
	public StreamSignals getStreamSignals() {
		return signals;

	}

	private class SignalHandler extends Thread {

		public void run() {
			setName("Stream Session Signal Handler");
			while(!interrupted()) { 
				try {
					StreamEntitySignal signal = signalQueue.take();
					StreamSignalBean bean = StreamSignalsHelper.entitySignalToBean(signal, getStreamSignals().getStreamBlueprint());
					try {
						signalBeansLock.acquire();
						signalBeans.add(bean);
					} catch (Exception e) {
						logger.error(marker, "Exception adding signal bean to list " + e.toString());
					} finally { 
						signalBeansLock.release();
					}
				} catch (Exception e) {
					if (e instanceof InterruptedException) { 
						return;
					}
					logger.error(marker, "Exception converting entity signal to signal bean " + e.toString());
				}

				
			}

		}
	}

	
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<StreamSignalBean> sessionSignals() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public SessionSignalQueryGrid querySignalGrid(StreamSignalSessionQuery query) throws Exception {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionSignalTypeQueryGrid querySignalTypeGrid(StreamSignalTypeSessionQuery query) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	

	
}
