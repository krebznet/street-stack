package com.dunkware.time.script.mod.repo;

import java.util.List;

import com.dunkware.time.script.mod.repo.entity.DBScriptRepo;
import com.dunkware.utils.core.event.EventNode;
import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;

public interface ScriptRepo {

	String getName();

	String getType();

	public ScriptRepoRelease getRelease();

	public XScriptDescriptor getReleaseModel();
	
	List<ScriptRepoRelease> getReleaseHistory();
	
	public EventNode getEventNode();

	public DBScriptRepo getEntity();


}
