package com.dunkware.time.script.mod.repo;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.dunkware.utils.core.events.DunkEventNode;
import com.dunkware.xstream.xproject.model.XScriptBundle;

@Service
public interface ScriptService {
	
	public void archiveScript(String  name) throws Exception; 
	
	public Script createScript(XScriptBundle script, String repoName) throws Exception; 
	
	public Script getScript(String name) throws Exception; 
	
	public Collection<Script> getScripts();
	
	public boolean scriptExists(String name); 
	
	public DunkEventNode getEventNode();
}
