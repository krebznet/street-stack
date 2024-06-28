package com.dunkware.time.repo.mod.script;

import org.springframework.stereotype.Service;

import com.dunkware.xstream.xproject.model.XScriptBundle;

@Service
public interface ScriptRepoService {

	public ScriptRepo createRepo(String identifier, XScriptBundle bundle) throws Exception;
	
	public ScriptRepo getRepo(String identifier) throws Exception; 
	
	
}
