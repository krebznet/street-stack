package com.dunkware.time.script.mod.repo.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.time.script.mod.repo.Script;
import com.dunkware.time.script.mod.repo.ScriptService;
import com.dunkware.time.script.mod.repo.ScriptSignal;
import com.dunkware.time.script.mod.repo.ScriptVariable;
import com.dunkware.time.script.mod.repo.ScriptVersion;
import com.dunkware.time.script.mod.repo.persist.ScriptEntity;
import com.dunkware.time.script.mod.repo.persist.ScriptVersionEntity;
import com.dunkware.utils.core.events.DunkEventNode;
import com.dunkware.xstream.xproject.model.XScriptBundle;


public class ScriptImpl implements Script {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	

	private ScriptEntity entity; 
	
	private List<ScriptVersion> versions = new ArrayList<ScriptVersion>();
	private List<ScriptVariable> variables = new ArrayList<ScriptVariable>();
	private List<ScriptSignal> signals = new ArrayList<ScriptSignal>();
	private ScriptVersion currentVersion = null;
	
	private DunkEventNode eventNode; 
	
	@Autowired
	private ScriptService scriptService; 
	
	
	public void create(XScriptBundle script, String name) throws Exception { 
		
	}
	
	public void init(ScriptEntity entity) throws Exception { 
		this.entity = entity;
		this.eventNode = scriptService.getEventNode().createChild(this);
		
		for (ScriptVersionEntity versionEnt: entity.getVersions()) {
			ScriptVersionImpl version = new ScriptVersionImpl();
			try {
				version.init(versionEnt);
				versions.add(version);
			} catch (Exception e) {
				logger.error("Exception init script entity " + e.toString());
				throw e;
			}
		}
		ScriptVersion recent = null;
		for (ScriptVersion version : versions) {
			if(recent == null) {
				recent = version;
			} else { 
				if(version.getVersion() > recent.getVersion()) { 
					recent = version; 
				}
			}
		}
		currentVersion = recent; 
	}
	
	
	@Override
	public DunkEventNode getEventNode() {
		return eventNode;
	}

	@Override
	public void archive() throws Exception {
		
	}


	@Override
	public boolean isArchieved() {
		if(entity.isActive() == false) { 
			return true; 
		}
		return false; 
		
	}


	@Override
	public String getName() {
		return entity.getName();
	}

	@Override
	public String getType() {
		return entity.getType();
	}

	@Override
	public XScriptBundle getBundle() {
		return currentVersion.getScriptBundle();
	}

	@Override
	public ScriptVersion getLatestVersion() {
		return currentVersion;
	}

	@Override
	public List<ScriptVersion> getVersions() {
		return versions; 
	}


	@Override
	public ScriptEntity getEntity() {
		return entity;
	}
	
	
	
	
}
