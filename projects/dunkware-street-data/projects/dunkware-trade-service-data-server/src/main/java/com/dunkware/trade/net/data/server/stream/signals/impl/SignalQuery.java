package com.dunkware.trade.net.data.server.stream.signals.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SignalQuery {

	private LocalDateTime from = null;
	private LocalDateTime to = null;
	private boolean enableDateRange = false; 
	private List<Integer> signalIdMatchers = new ArrayList<Integer>();
	private boolean enableSignalIdMatchers = false; 
	private List<Integer> entityIdMatchers = new ArrayList<Integer>();
	private boolean enableEntityIdMatchers = false; 
	private int sortField = 0; // not sure here - sort by datetime, sorty by signal id, sort by entity id
	

}
