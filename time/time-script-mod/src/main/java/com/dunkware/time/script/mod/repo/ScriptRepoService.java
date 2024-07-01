package com.dunkware.time.script.mod.repo;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.dunkware.xstream.xproject.model.XScriptBundle;

@Service
public interface ScriptRepoService {
	
	public void deleteRepo(ScriptRepo repo) throws Exception; 
	
	public ScriptRepo createRepo(XScriptBundle script, String repoName) throws Exception; 
	
	public ScriptRepo getRepo(String ident) throws Exception; 
	
	public Collection<ScriptRepo> getRepos();
	
}
