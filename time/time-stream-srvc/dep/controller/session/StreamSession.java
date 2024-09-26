
package com.dunkware.trade.service.stream.server.controller.session;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;

import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStats;
import com.dunkware.trade.service.stream.json.controller.spec.StreamState;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.repository.StreamSessionEntity;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.utils.core.event.EventNode;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.model.XScriptBundle;

public interface StreamSession {

	public XScriptBundle getXScriptBundle();

	public String killSession();
	
	public LocalTime getStartTime();
	
	public LocalTime getStopTime();
	
	public void startSession(StreamSessionInput input) throws StreamSessionException;

	public void stopSession() throws StreamSessionException;

	public StreamSessionStats getStats();

	public Collection<StreamSessionNode> getNodes();

	public StreamState getState();
	
	public int getStreamId();

	public StreamController getStream();

	public String getSessionId();
	
	public StreamSessionInput getInput();

	public List<StreamSessionExtension> getExtensions();

	public Long getSessionEntityId();

	public EventNode getEventNode();
	
	public XScriptProject getScriptProject();

	public StreamSessionEntity getEntity();

	public List<TradeTickerSpec> getTickers();

	public ZoneId getZoneId();

	public StreamSessionNode getEntityNode(String ident) throws Exception;
	
	public StreamSessionNode getEntityNode(int entityId) throws Exception;
}
