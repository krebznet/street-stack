
package com.dunkware.trade.service.stream.server.controller.session;

import java.util.Collection;
import java.util.List;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.service.stream.json.controller.model.StreamSessionSpec;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStatus;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.repository.StreamSessionEntity;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.model.scanner.SessionEntityScanner;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.model.XScriptBundle;
import com.dunkware.xstream.xproject.model.XStreamBundle;

public interface StreamSession {

	public XScriptBundle getXScriptBundle(); 
	
	public void startSession(StreamSessionInput input) throws StreamSessionException;
	
	public void stopSession() throws StreamSessionException;
	
	public StreamSessionStatus getStatus();
	
	public Collection<StreamSessionNode> getNodes();
	
	public StreamController getStream();
	
	public String getSessionId();

	public List<StreamSessionExtension> getExtensions();
	
	public DEventNode getEventNode();
	
	public Long getSessionEntityId();
	
	public XScriptProject getScriptProject();
	
	public StreamSessionEntity getEntity();
	
	public List<TradeTickerSpec> getTickers();
	
	public StreamSessionSpec getSessionSpec();
	
	public StreamSessionEntityScanner entityScanner(SessionEntityScanner model) throws XStreamRuntimeException;

	public StreamSessionNode getEntityNode(String ident) throws Exception;
}
