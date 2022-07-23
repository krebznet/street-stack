package com.dunkware.trade.service.stream.worker.session.container.extensions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamTimeUpdate;
import com.dunkware.spring.channel.anot.AChannelHandler;
import com.dunkware.spring.channel.anot.AChannelSetter;
import com.dunkware.spring.channel.anot.AMessageHandler;
import com.dunkware.trade.service.stream.worker.session.container.WorkerContainer;
import com.dunkware.xstream.container.proto.GEntitySignalWrapper;
import com.dunkware.xstream.container.proto.GEntitySnapshotWrapper;
import com.dunkware.xstream.container.proto.GStreamTimeUpdateWrapper;

@AChannelHandler("WorkerContainer")
public class WorkerStreamEventConsumer {

	private WorkerContainer container; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());	
	
	@AChannelSetter()
	public void setContainer(WorkerContainer container) { 
		this.container = container; 
	}
	

	@AMessageHandler()
	public void handleTimeUpdate(GStreamTimeUpdateWrapper update) { 
		try {
			GStreamTimeUpdate timeUpdate = GStreamTimeUpdate.parseFrom(update.getBytes());
			container.getCache().consumeStreamTime(timeUpdate);
		} catch (Exception e) {
			logger.error("Exception deserialziing time update wrapper and setting on container " + e.toString());;
		}
	}
	
	
	
	@AMessageHandler()
	public void handleSignal(GEntitySignalWrapper signal) { 
		try {
			GEntitySignal gSignal = GEntitySignal.parseFrom(signal.getBytes());
			container.getCache().consumeStreamSignal(gSignal);
		} catch (Exception e) {
			logger.error("Exception deserialzing setting evnet signal on worker " + e.toString());
		}
	}
	
	
	@AMessageHandler()
	public void handleSnapshot(GEntitySnapshotWrapper snapshot) { 
		try {
			GEntitySnapshot gEntitySnapshot = GEntitySnapshot.parseFrom(snapshot.getBytes());
			container.getCache().consumeStreamSnapshot(gEntitySnapshot);
		} catch (Exception e) {
			logger.error("Exception deserialziing and setting snapshot on worker " + e.toString());
		}
		
	}
}
