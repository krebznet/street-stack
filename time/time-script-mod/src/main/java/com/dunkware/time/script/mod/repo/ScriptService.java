package com.dunkware.time.script.mod.repo;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.dunkware.utils.core.events.DunkEventNode;

@Service
public interface ScriptService {
	
	public void deleteScript(String  name) throws Exception; 
	
	public Script createScript(String script, String repoName,String type) throws Exception; 
	
	public Script getScript(String name) throws Exception; 
	
	public Collection<Script> getScripts();
	
	public boolean hasScript(String name); 
	
	public DunkEventNode getEventNode();
}
