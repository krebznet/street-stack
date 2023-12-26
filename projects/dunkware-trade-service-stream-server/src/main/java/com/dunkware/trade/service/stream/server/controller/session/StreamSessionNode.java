package com.dunkware.trade.service.stream.server.controller.session;

import java.util.List;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeBean;
import com.dunkware.trade.service.stream.json.controller.spec.StreamState;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.xproject.model.XStreamBundle;

public interface StreamSessionNode {

	XStreamBundle getStreamBundle();
	
	StreamState getState();
	
	StreamSessionNodeBean getBean();
	
	DunkNetNode getDunkNode();
	
	DunkNetChannel getChannel();
	
	
	String getNodeId();
	
	int getNumericId();
	
	String getWorkerId();
	
	List<TradeTickerSpec> getTickers();
	
	boolean hasTicker(String symbol);
	
	public List<String> getErrors();
	
	void stop(); 
	
	StreamSessionWorkerStartReq getStartReq();
	
	void start(StreamSessionNodeInput input) ;
	
	DEventNode getEventNode();
	
	public StreamSessionNodeInput getInput();
	
	public StreamSession getSession();
	
	public StreamController getStream();
		
	String getStartExcetpion();
	
	String getStopException();
	
	public void cancel();
	
	public boolean isRunning();
	
	public double stoppingElapsedTime();
	
	public double startingElapsedTime();
	
	public boolean isStarting();
	
	public boolean isStopping();
	
	public boolean isStopException();
	
	public StreamSessionNodeStopState getStopState();
	
	
	
	
	
	
}
