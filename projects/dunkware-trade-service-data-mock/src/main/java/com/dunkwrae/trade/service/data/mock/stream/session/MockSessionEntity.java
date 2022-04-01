package com.dunkwrae.trade.service.data.mock.stream.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkwrae.trade.service.data.mock.input.MockInputSignal;
import com.dunkwrae.trade.service.data.mock.input.MockInputVar;
import com.dunkwrae.trade.service.data.mock.stream.MockStream;
import com.dunkwrae.trade.service.data.mock.stream.MockStreamEntity;
import com.dunkwrae.trade.service.data.mock.stream.MockStreamSignalType;
import com.dunkwrae.trade.service.data.mock.stream.MockStreamVar;

public class MockSessionEntity {
	
	
	private ConcurrentHashMap<String,MockSessionVar> vars = new ConcurrentHashMap<String,MockSessionVar>();
	private List<MockSessionSignalFactory> signalFactories = new ArrayList<MockSessionSignalFactory>();
	private List<MockSessionSignal> signals = new ArrayList<MockSessionSignal>();
	
	private MockSession session;
	private MockStream stream;
	private MockStreamEntity streamEntity;
	
	public void init(MockSession session, MockStream stream, MockStreamEntity streamEntity, ConcurrentHashMap<String, MockSessionVar> vars,
			List<MockSessionSignalFactory> signalFactories) { 
		this.streamEntity = streamEntity;
		this.signalFactories = signalFactories;
		this.vars = vars;
		this.session = session; 
		this.stream = stream;  
	}
	
	public Collection<MockSessionVar> getVars() { 
		return vars.values();
	}
	
	public MockSessionVar getVar(String name) { 
		return vars.get(name);
	}

	public void signal(MockStreamSignalType type) {
		MockSessionSignal sig = new MockSessionSignal(this, session.getTime(), type);
		this.signals.add(sig);
	}
	
	public List<MockSessionSignal> getSignals() { 
		return signals; 
	}
	
	public void clearSignals() { 
		signals.clear();
	}
	
	public String getIdentifier() { 
		return this.streamEntity.getIdentifier();
	}
	
	public int getId() { 
		return this.streamEntity.getId();
	}
	
	public MockSession getSession() { 
		return session;
	}
	
	public MockStream getStream() { 
		return stream; 
	}
	
	public void update() { 
		for (MockSessionVar var : vars.values()) {
			var.update();
		}
		
		for (MockSessionSignalFactory mockSessionSignalFactory : signalFactories) {
			mockSessionSignalFactory.update(this);
		}
	}
	
	public MockStreamEntity getStreamEntity() { 
		return streamEntity;
	}
}
