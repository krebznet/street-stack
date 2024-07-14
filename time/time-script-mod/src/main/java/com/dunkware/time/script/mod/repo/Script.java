package com.dunkware.time.script.mod.repo;

import java.util.List;

import com.dunkware.time.script.mod.repo.entity.ScriptEntity;
import com.dunkware.utils.core.events.DunkEventNode;
import com.dunkware.xstream.model.script.model.XScriptModel;

public interface Script {

	String getName();

	String getType();

	public ScriptRelease getRelease();

	public XScriptModel getReleaseModel();
	
	List<ScriptRelease> getReleaseHistory();
	
	public DunkEventNode getEventNode();

	public ScriptEntity getEntity();


}
