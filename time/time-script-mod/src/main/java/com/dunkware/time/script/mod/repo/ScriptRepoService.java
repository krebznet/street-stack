package com.dunkware.time.script.mod.repo;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.dunkware.utils.core.event.EventNode;

@Service
public interface ScriptRepoService {
	
	public void deleteRepo(String  name) throws Exception; 
	
	public ScriptRepo createRepo(String script, String repoName,String type) throws Exception; 
	
	public ScriptRepo getRepo(String name) throws Exception; 
	
	public Collection<ScriptRepo> getRepos();
	
	public boolean repoExists(String name); 
	
	public EventNode getEventNode();
}
