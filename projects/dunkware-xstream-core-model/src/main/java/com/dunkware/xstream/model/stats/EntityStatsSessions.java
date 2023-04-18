package com.dunkware.xstream.model.stats;

import java.util.ArrayList;
import java.util.List;

public class EntityStatsSessions {
	
	private boolean resolved = true;
	private List<EntityStatsSession> sessions = new ArrayList<EntityStatsSession>();

	public List<EntityStatsSession> getSessions() {
		return sessions;
	}

	public void setSessions(List<EntityStatsSession> sessions) {
		this.sessions = sessions;
	}

	public boolean isResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}
	
	
	

}
