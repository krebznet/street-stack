package com.dunkware.time.script.mod.repo;

import java.time.LocalDateTime;

import com.dunkware.time.script.mod.repo.entity.ScriptReleaseEntity;
import com.dunkware.xstream.model.script.model.XScriptRelease;
import com.dunkware.xstream.xproject.XScriptProject;

public interface ScriptRelease {

	public XScriptRelease getModel();
	
	String getVersion(); 
	
	XScriptProject getProject();
	
	public ScriptReleaseEntity getEntity();
	
	public LocalDateTime getTimestamp();
	
	public Script getScript();
	
	public String getScriptName();
	
	public String getXScript();
	
}
