package com.dunkware.xstream.data.cache;

import java.util.List;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.executor.DExecutor;

public class CacheStreamInput {
	
	private List<CacheExtensionType> extensions;
	private DExecutor executor;
	private DTimeZone timeZone;
	private String identifier;
	private boolean debug = false;
	private boolean snapshotHistory = false;
	
	public CacheStreamInput(List<CacheExtensionType> extensions, DExecutor executor, DTimeZone zone, String identifier) { 
		this.extensions = extensions;
		this.executor = executor;
		this.timeZone = zone;
		this.identifier = identifier;
	}
	
	public List<CacheExtensionType> getExtensions() {
		return extensions;
	}
	public void setExtensions(List<CacheExtensionType> extensions) {
		this.extensions = extensions;
	}
	public DExecutor getExecutor() {
		return executor;
	}
	public void setExecutor(DExecutor executor) {
		this.executor = executor;
	}
	public DTimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(DTimeZone timeZone) {
		this.timeZone = timeZone;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public boolean isSnapshotHistory() {
		return snapshotHistory;
	}

	public void setSnapshotHistory(boolean snapshotHistory) {
		this.snapshotHistory = snapshotHistory;
	}

	
	
	
	
	
	
	
	
	
	

}
