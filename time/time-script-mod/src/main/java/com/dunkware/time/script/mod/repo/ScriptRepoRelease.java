package com.dunkware.time.script.mod.repo;

import java.time.LocalDateTime;

import com.dunkware.time.script.mod.repo.entity.DBScriptRepoRelease;
import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;
import com.dunkware.xstream.model.script.release.XScriptRelease;
import com.dunkware.xstream.xproject.XScriptProject;

public interface ScriptRepoRelease {

	public XScriptRelease getRelease();
	
	String getVersion(); 
	
	XScriptProject getProject();
	
	public DBScriptRepoRelease getEntity();
	
	public LocalDateTime getTimestamp();
	
	public ScriptRepo getRepo();
	
	public String getScriptName();
	
	public String getSource();
	
	public XScriptDescriptor getDescriptor();
	
}
