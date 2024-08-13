package com.dunkware.time.script.mod.repo.impl;

import java.time.LocalDateTime;

import com.dunkware.time.script.mod.repo.ScriptRepo;
import com.dunkware.time.script.mod.repo.ScriptRepoRelease;
import com.dunkware.time.script.mod.repo.entity.DBScriptRepoRelease;
import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.xstream.model.script.release.XScriptRelease;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.bundle.XscriptBundleHelper;
import com.dunkware.xstream.xproject.model.XScriptBundle;

public class ScriptRepoReleaseImpl implements ScriptRepoRelease {

	private DBScriptRepoRelease entity;
	private XScriptRelease releaseModel;
	private XScriptProject scriptProject;
	private ScriptRepo script; 
	public void init(ScriptRepo script, DBScriptRepoRelease entity) throws Exception {
		this.entity = entity;
		this.script = script;
		releaseModel = DunkJson.getObjectMapper().readValue(entity.getMetadata(), XScriptRelease.class);
		XScriptBundle bundle = XscriptBundleHelper.createBundleFromFileContents(entity.getSource());
		scriptProject = new XScriptProject(bundle);
	}
	
	@Override
	public XScriptRelease getRelease() {
		return releaseModel;
	}

	@Override
	public String getVersion() {
		return releaseModel.getVersion();
	}

	@Override
	public XScriptProject getProject() {
		return scriptProject;
	}

	@Override
	public DBScriptRepoRelease getEntity() {
		return entity;
	}

	@Override
	public LocalDateTime getTimestamp() {
		return entity.getTimestamp();
	}

	@Override
	public ScriptRepo getRepo() {
		return script;
	}

	@Override
	public String getScriptName() {
		return entity.getScriptName();
	}

	@Override
	public String getSource() {
		return entity.getSource();
	}
	
	
	
	

}
