package com.dunkware.trade.service.data.json.data.stream.session;

import com.dunkware.common.util.dtime.DTime;

public class StreamSessionStats {
	
	private DTime sessionStart; 
	private DTime streamStart; 
	
	private String stream; 
	private String sessionId; 
	
	private StreamSessionCacheStats cache; 
	private StreamSessionSignalWriterStats signalWriter; 
	private StreamSessionSnapshotWriterStats snapshotWriter;
	
	
	public DTime getSessionStart() {
		return sessionStart;
	}
	public void setSessionStart(DTime sessionStart) {
		this.sessionStart = sessionStart;
	}
	public DTime getStreamStart() {
		return streamStart;
	}
	public void setStreamStart(DTime streamStart) {
		this.streamStart = streamStart;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public StreamSessionCacheStats getCache() {
		return cache;
	}
	public void setCache(StreamSessionCacheStats cache) {
		this.cache = cache;
	}
	public StreamSessionSignalWriterStats getSignalWriter() {
		return signalWriter;
	}
	public void setSignalWriter(StreamSessionSignalWriterStats signalWriter) {
		this.signalWriter = signalWriter;
	}
	public StreamSessionSnapshotWriterStats getSnapshotWriter() {
		return snapshotWriter;
	}
	public void setSnapshotWriter(StreamSessionSnapshotWriterStats snapshotWriter) {
		this.snapshotWriter = snapshotWriter;
	} 

	
}
