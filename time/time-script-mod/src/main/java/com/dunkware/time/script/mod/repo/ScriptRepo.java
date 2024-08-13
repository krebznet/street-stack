package com.dunkware.time.script.mod.repo;

import java.util.List;

import com.dunkware.time.script.mod.repo.entity.DBScriptRepo;
import com.dunkware.utils.core.events.DunkEventNode;
import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;

public interface ScriptRepo {

	String getName();

	String getType();

	public ScriptRepoRelease getRelease();

	public XScriptDescriptor getReleaseModel();
	
	List<ScriptRepoRelease> getReleaseHistory();
	
	public DunkEventNode getEventNode();

	public DBScriptRepo getEntity();


}
