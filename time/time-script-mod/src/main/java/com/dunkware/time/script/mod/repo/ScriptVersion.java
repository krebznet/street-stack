package com.dunkware.time.script.mod.repo;

import java.time.LocalDateTime;

import com.dunkware.time.script.mod.repo.persist.ScriptVersionEntity;
import com.dunkware.time.script.model.update.ScriptUpdates;
import com.dunkware.xstream.xproject.model.XScriptBundle;

public interface ScriptVersion {

	public double getVersion(); 
	
	public ScriptVersionEntity getEntity();
	
	public XScriptBundle getScriptBundle();
	
	public ScriptUpdates getUpdates();
	
	public LocalDateTime getTimestamp();
}
