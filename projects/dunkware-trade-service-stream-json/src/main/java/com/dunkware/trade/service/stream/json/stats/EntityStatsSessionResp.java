package com.dunkware.trade.service.stream.json.stats;

import com.dunkware.xstream.model.stats.session.EntitySessionStats;

public class EntityStatsSessionResp {
	
	private boolean resolved = true; 
	private String resolveError = null;
	private EntitySessionStats session;
	public boolean isResolved() {
		return resolved;
	}
	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}
	public String getResolveError() {
		return resolveError;
	}
	public void setResolveError(String resolveError) {
		this.resolveError = resolveError;
	}
	public EntitySessionStats getSession() {
		return session;
	}
	public void setSession(EntitySessionStats session) {
		this.session = session;
	}

	
}
