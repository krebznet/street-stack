package com.dunkware.time.stream.mod.stream;

import com.dunkware.time.script.mod.repo.ScriptRepo;
import com.dunkware.time.stream.mod.entity.DBStream;
import com.dunkware.time.stream.model.dto.StreamDTO;
import com.dunkware.utils.core.event.EventNode;
import com.dunkware.utils.core.scheduler.Scheduler;
import com.dunkware.utils.core.scheduler.model.Event;

public interface IStream {

	long getId();

	String getIdent();

	String getType();

	public StreamDTO getDTO();
	
	public Scheduler getScheduler();
	
	public Event getSessionEvent();
	
	public ScriptRepo getScriptRepo();

	public EventNode getEventNode();

	public DBStream getEntity();
	
	// need dynamic entity mother fucke 
	
	public boolean isRunning(); 
	
	


}
