package com.dunkware.time.script.mod.repo;

import java.time.LocalDateTime;

import com.dunkware.utils.core.json.bytes.JsonBytes;
import com.dunkware.xstream.xproject.model.XScriptBundle;

public interface ScriptRepoVersion {

	public double getVersion(); 
	
	public ScriptRepoVersion getEntity();
	
	public XScriptBundle getScriptBundle();
	
	public JsonBytes getScriptBytes();
	
	public LocalDateTime getTimestamp();
}
