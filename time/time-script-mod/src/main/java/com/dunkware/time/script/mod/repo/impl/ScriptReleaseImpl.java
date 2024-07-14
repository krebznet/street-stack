package com.dunkware.time.script.mod.repo.impl;

import java.time.LocalDateTime;

import com.dunkware.time.script.mod.repo.Script;
import com.dunkware.time.script.mod.repo.ScriptRelease;
import com.dunkware.time.script.mod.repo.entity.ScriptReleaseEntity;
import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.xstream.model.script.model.XScriptRelease;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.bundle.XscriptBundleHelper;
import com.dunkware.xstream.xproject.model.XScriptBundle;

public class ScriptReleaseImpl implements ScriptRelease {

	private ScriptReleaseEntity entity;
	private XScriptRelease releaseModel;
	private XScriptProject scriptProject;
	private Script script; 
	public void init(Script script, ScriptReleaseEntity entity) throws Exception {
		this.entity = entity;
		this.script = script;
		releaseModel = DunkJson.getObjectMapper().readValue(entity.getReleaseModel(), XScriptRelease.class);
		XScriptBundle bundle = XscriptBundleHelper.createBundleFromFileContents(entity.getXscript());
		scriptProject = new XScriptProject(bundle);
	}
	
	@Override
	public XScriptRelease getModel() {
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
	public ScriptReleaseEntity getEntity() {
		return entity;
	}

	@Override
	public LocalDateTime getTimestamp() {
		return entity.getReleaseTimestamp();
	}

	@Override
	public Script getScript() {
		return script;
	}

	@Override
	public String getScriptName() {
		return entity.getScriptName();
	}

	@Override
	public String getXScript() {
		return entity.getXscript();
	}
	
	
	
	

}
