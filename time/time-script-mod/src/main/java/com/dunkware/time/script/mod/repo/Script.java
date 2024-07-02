package com.dunkware.time.script.mod.repo;

import java.util.List;

import com.dunkware.time.script.mod.repo.persist.ScriptEntity;
import com.dunkware.utils.core.events.DunkEventNode;
import com.dunkware.xstream.xproject.model.XScriptBundle;

public interface Script {

	String getName();

	String getType();
	
	XScriptBundle getBundle();

	ScriptVersion getLatestVersion();
	
	List<ScriptVersion> getVersions();
	
	public DunkEventNode getEventNode();

	public boolean isArchieved();

	public ScriptEntity getEntity();
	
	public void archive() throws Exception;

}
