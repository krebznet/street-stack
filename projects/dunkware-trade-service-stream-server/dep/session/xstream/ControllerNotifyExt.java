package com.dunkware.trade.service.stream.server.controller.session.xstream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.helpers.DHttpHelper;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.WorkerSignalNotify;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.WorkerTickerNotify;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamExtension;
import com.dunkware.xstream.api.XStreamListener;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRowListener;
import com.dunkware.xstream.core.runtime.annotations.AXStreamExtension;
import com.dunkware.xstream.model.XStreamExtensionType;
import com.dunkware.xstream.model.signal.XStreamSignal;


/**
 * Used to notify stream controller of row inserts and row signals
 */
@AXStreamExtension(type = ControllerNotifyExtType.class)
public class ControllerNotifyExt implements XStreamExtension, XStreamListener, XStreamRowListener  {
	
	private ControllerNotifyExtType myType;
	private XStream stream; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void init(XStream stream, XStreamExtensionType type) throws XStreamException {
		this.myType = (ControllerNotifyExtType)type;
		this.stream = stream; 
	}

	@Override
	public void preStart() throws XStreamException {
		
	}

	@Override
	public void start() throws XStreamException {
		stream.addStreamListener(this);
		stream.addRowListener(this);
	
	}

	@Override
	public void preDispose() {
		stream.removeRowListener(this);
		stream.removeRowListener(this);
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void rowSignal(XStreamRow row, XStreamSignal signal) {
		SignalNotify notify = new SignalNotify(signal);
		notify.start();
	}

	@Override
	public void rowInsert(XStreamRow row) {
		TickerNotify notify = new TickerNotify(row.getId(),stream.getClock().getTime());
		notify.start();	
	}
	
	
	
	private class SignalNotify extends Thread { 
		
		private XStreamSignal signa; 
		
		public SignalNotify(XStreamSignal signal) { 
			this.signa = signal;
		}
		
		public void run() { 
			WorkerSignalNotify notify = new WorkerSignalNotify();
			notify.setSignal(signa);
			notify.setStream(myType.getStream());
			notify.setNode(myType.getNode());
			try {
				DHttpHelper.postJson(myType.getEndpoint() + "/stream/session/notify/signal",notify);
			} catch (Exception e) {
				logger.error("Exception notify signal " + e.toString());
				
			}
		}
	}
	
	private class TickerNotify extends Thread { 
		
		private String symbol;
		private DTime time; 
		
		public TickerNotify(String symbol, DTime time) { 
			this.symbol = symbol;
			this.time = time; 
		}
		
		
		public void run() { 
			try {
				WorkerTickerNotify notify = new WorkerTickerNotify();
				notify.setNode(myType.getNode());
				notify.setSymbol(symbol);
				notify.setStream(myType.getStream());
				notify.setTime(time);
				DHttpHelper.postJson(myType.getEndpoint() + "/stream/session/notify/ticker", notify);
			} catch (Exception e) {
				logger.error("Exception sending ticker notify " + e.toString());
			}
		}
	}
	
	

	
}
