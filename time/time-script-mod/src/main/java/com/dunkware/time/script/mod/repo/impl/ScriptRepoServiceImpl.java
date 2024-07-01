package com.dunkware.time.script.mod.repo.impl;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dunkware.time.script.mod.repo.ScriptRepo;
import com.dunkware.time.script.mod.repo.ScriptRepoService;
import com.dunkware.xstream.xproject.model.XScriptBundle;

import jakarta.annotation.PostConstruct;

@Service
public class ScriptRepoServiceImpl implements ScriptRepoService  {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Map<String,ScriptRepo> repos = new ConcurrentHashMap<String,ScriptRepo>();

	
	@PostConstruct
	private void init() { 
		
	}
	
	
	
	
	@Override
	public ScriptRepo createRepo(XScriptBundle script, String repoName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public void deleteRepo(ScriptRepo repo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public ScriptRepo getRepo(String ident) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ScriptRepo> getRepos() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
