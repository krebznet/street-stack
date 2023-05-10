package com.dunkware.trade.service.beach.protocol.play;

import java.util.ArrayList;
import java.util.List;

public class Play {

	private String entrySignal; 
	private String playName; 
	private double maxCapital;
	private double tradeCapital; 
	
	// Entry Strategy 
	private PlayOrderType entryType; 
	private double entryChaseInterval;
	private int entryTimeout; 
	
	// Exit Order Type 
	private PlayOrderType exitType; 
	private double exitChaseInterval; 
	private List<PlayExit> exits = new ArrayList<PlayExit>();
	
}
