package com.dunkware.time.script.mod.repo.impl;

import java.util.List;

import com.dunkware.time.script.mod.repo.ScriptRepo;
import com.dunkware.time.script.mod.repo.ScriptRepoVersion;
import com.dunkware.time.script.mod.repo.persist.ScriptEntity;
import com.dunkware.xstream.xproject.model.XScriptBundle;

public class ScriptRepoImpl implements ScriptRepo {

	private ScriptEntity entity; 
	
	public void init(ScriptEntity entity) { 
		this.entity = entity; 
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XScriptBundle getBundle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScriptRepoVersion getLatestVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScriptRepoVersion> getVersions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	


	
}
