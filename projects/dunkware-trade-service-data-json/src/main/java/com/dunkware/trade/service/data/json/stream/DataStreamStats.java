package com.dunkware.trade.service.data.json.stream;

import com.dunkware.trade.service.data.json.stream.session.DataStreamSessionStats;

public class DataStreamStats {
	
	private String name; 
	private int completedSessions; 
	private boolean activeSession; 
	
	private DataStreamSessionStats sessionStatus;
}
