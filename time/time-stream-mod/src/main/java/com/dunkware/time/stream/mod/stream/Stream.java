package com.dunkware.time.stream.mod.stream;

import com.dunkware.time.script.mod.repo.ScriptRepo;
import com.dunkware.time.stream.mod.entity.DBStream;
import com.dunkware.time.stream.model.admin.config.StreamConfig;
import com.dunkware.utils.core.events.DunkEventNode;
import com.dunkware.utils.core.scheduler.Scheduler;
import com.dunkware.utils.core.scheduler.model.Event;

public interface Stream {

	int getId();

	String getIdent();

	String getType();

	public void update(StreamConfig settings) throws Exception; 
	
	public Scheduler getScheduler();
	
	public Event getSessionEvent();
	
	public ScriptRepo getScriptRepo();

	public DunkEventNode getEventNode();

	public DBStream getEntity();


}
