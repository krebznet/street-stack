package com.dunkware.trade.service.stream.server.session.core;

import java.util.List;
import java.util.Map;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStats;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStatus;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.session.StreamSession;
import com.dunkware.trade.service.stream.server.session.StreamSessionException;
import com.dunkware.trade.service.stream.server.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.session.StreamSessionInput;
import com.dunkware.trade.service.stream.server.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.session.repository.StreamSessionDO;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.xproject.XScriptProject;

public class StreamSessionImpl implements StreamSession {

	@Override
	public void startSession(StreamSessionInput input) throws StreamSessionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopSession() throws StreamSessionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StreamSessionStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StreamSessionStats getStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StreamSessionNode> getNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StreamController getStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSessionId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StreamSessionExtension> getExtensions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DEventNode getEventNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSessionEntityId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XScriptProject getScriptProject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StreamSessionDO getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperty(String name, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TradeTickerSpec> getTickers() {
		// TODO Auto-generated method stub
		return null;
	}


	
	

}
