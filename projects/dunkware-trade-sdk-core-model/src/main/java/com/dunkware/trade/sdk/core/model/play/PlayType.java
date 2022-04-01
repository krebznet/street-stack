package com.dunkware.trade.sdk.core.model.play;

import com.dunkware.trade.sdk.core.model.trade.EntryType;
import com.dunkware.trade.sdk.core.model.trade.ExitType;

public class PlayType {
	
	private String name; 
	private EntryType entry; 
	private ExitType exit;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EntryType getEntry() {
		return entry;
	}
	public void setEntry(EntryType entry) {
		this.entry = entry;
	}
	public ExitType getExit() {
		return exit;
	}
	public void setExit(ExitType exit) {
		this.exit = exit;
	} 
	
	

}
