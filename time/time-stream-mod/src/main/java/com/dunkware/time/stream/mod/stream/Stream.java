package com.dunkware.time.stream.mod.stream;

import java.util.List;

import com.dunkware.time.script.mod.repo.ScriptRepoRelease;
import com.dunkware.time.stream.mod.entity.DBStream;
import com.dunkware.time.stream.model.admin.settings.StreamSettings;
import com.dunkware.utils.core.events.DunkEventNode;
import com.dunkware.utils.core.scheduler.Scheduler;
import com.dunkware.utils.core.scheduler.model.Event;
import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;

public interface Stream {

	int getId();

	String getName();

	String getType();

	public void saveSettings(StreamSettings settings) throws Exception; 
	
	public Scheduler getScheduler();
	
	public Event getSessionEvent();
	
	public ScriptRepoRelease getScript();

	List<ScriptRepoRelease> getScriptHistory();
	
	public DunkEventNode getEventNode();

	public DBStream getEntity();


}
