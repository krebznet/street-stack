package com.dunkwrae.trade.service.data.mock.stream.session;

import com.dunkwrae.trade.service.data.mock.input.MockInputVar;

public class MockSessionVar {
	
	private MockInputVar inputVar; 
	private MockSessionEntity entity;
	private MockSessionVarUpdater updater;
	
	private volatile Object value = null;
	
	
	public MockSessionVar(MockSessionEntity entity, MockInputVar inputVar) {
		this.inputVar = inputVar;
		this.entity = entity;
		this.updater = inputVar.buildVarUpdater();
		
	}
	
	public String getIdentifier() { 
		return inputVar.getIdentifier();
	}
	
	public MockInputVar getInputVar() { 
		return inputVar;
	}
	
	public String getName() { 
		return inputVar.getName();
	}
	
	public int getId() { 
		return inputVar.getId();
	}
	
	public void update() { 
		updater.update(this);
	}
	
	public void setValue(Object value) { 
		this.value = value;
	}

	public Object getValue() { 
		return value;
	}
	
	public MockSessionEntity getEntity() { 
		return entity;
	}
	
	public MockSession getSession() { 
		return entity.getSession();
	}
}
