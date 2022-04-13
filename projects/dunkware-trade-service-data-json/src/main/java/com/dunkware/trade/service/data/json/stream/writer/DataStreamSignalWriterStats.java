package com.dunkware.trade.service.data.json.stream.writer;

import com.dunkware.common.util.dtime.DDateTime;

public class DataStreamSignalWriterStats {
	
	private String stream; 
	private DataStreamWriterState state; 
	private DDateTime initializedDateTime;
	
	
	private DataStreamSignalWriterSessionStats activeSession = null;

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
	

	public DataStreamSignalWriterSessionStats getActiveSession() {
		return activeSession;
	}

	public void setActiveSession(DataStreamSignalWriterSessionStats activeSession) {
		this.activeSession = activeSession;
	}

	public DDateTime getInitializedDateTime() {
		return initializedDateTime;
	}

	public void setInitializedDateTime(DDateTime initializedDateTime) {
		this.initializedDateTime = initializedDateTime;
	}
	
	
	
	

	

	
	
	
	
	
	

}
