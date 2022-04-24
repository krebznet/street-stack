package com.dunkware.trade.service.stream.server.session;

import java.util.List;
import java.util.Map;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStatus;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStatus;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.session.repository.StreamSessionDO;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.xproject.XScriptProject;

public interface StreamSession {

	public void startSession(StreamSessionInput input) throws StreamSessionException;
	
	public void stopSession() throws StreamSessionException;
	
	public StreamSessionStatus getStatus();
	
	public List<StreamSessionNode> getNodes();
	
	public StreamController getStream();
	
	public String getSessionId();

	public List<StreamSessionExtension> getExtensions();
	
	public DEventNode getEventNode();
	
	public Long getSessionEntityId();
	
	public XScriptProject getScriptProject();
	
	public StreamSessionDO getEntity();
	
	public List<TradeTickerSpec> getTickers();
}
