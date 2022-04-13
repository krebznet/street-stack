package com.dunkware.trade.service.data.json.stream.writer;

import com.dunkware.common.util.dtime.DDateTime;

public class DataStreamSnapshotWriterStats {
	
	private String stream; 
	private DataStreamWriterState state; 
	private DDateTime initializedDateTime;
	
	
	private DataStreamSnapshotWriterSessionStats activeSession = null;

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public DataStreamWriterState getState() {
		return state;
	}

	public void setState(DataStreamWriterState state) {
		this.state = state;
	}
	
	public DataStreamSnapshotWriterSessionStats getActiveSession() {
		return activeSession;
	}

	public void setActiveSession(DataStreamSnapshotWriterSessionStats activeSession) {
		this.activeSession = activeSession;
	}

	public DDateTime getInitializedDateTime() {
		return initializedDateTime;
	}

	public void setInitializedDateTime(DDateTime initializedDateTime) {
		this.initializedDateTime = initializedDateTime;
	}
	
	
	
	

	
	
}
