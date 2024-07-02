package com.dunkware.time.script.mod.repo.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.time.script.mod.repo.ScriptVersion;
import com.dunkware.time.script.mod.repo.persist.ScriptVersionEntity;
import com.dunkware.time.script.model.update.ScriptUpdates;
import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.xstream.xproject.bundle.XscriptBundleHelper;
import com.dunkware.xstream.xproject.model.XScriptBundle;

public class ScriptVersionImpl implements ScriptVersion {
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ScriptVersionEntity entity;
	
	private XScriptBundle bundle; 
	
	private ScriptUpdates updates; 
	
	public void init(ScriptVersionEntity entity) throws Exception { 
		this.entity = entity;
		try {
			bundle = XscriptBundleHelper.createBundleFromFileContents(entity.getScript());
		} catch (Exception e) {
			logger.error("Exception loading version script " + e.toString());
			throw e; 
		}
		try {
			updates = DunkJson.getObjectMapper().readValue(entity.getUpdates(), ScriptUpdates.class);
		} catch (Exception e) {
			logger.error("Exception deserializing scrip updates on version " + e.toString());
		}
	}

	@Override
	public double getVersion() {
		return entity.getVersion();
	}

	@Override
	public ScriptVersionEntity getEntity() {
		return entity;
	}

	@Override
	public XScriptBundle getScriptBundle() {
		return bundle; 
	}

	@Override
	public ScriptUpdates getUpdates() {
		return updates;
	}

	@Override
	public LocalDateTime getTimestamp() {
		return entity.getTimestamp();
	}

	
}
