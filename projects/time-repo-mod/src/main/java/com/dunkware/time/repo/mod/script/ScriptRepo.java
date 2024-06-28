package com.dunkware.time.repo.mod.script;

import java.util.List;

import com.dunkware.xstream.xproject.model.XScriptBundle;

public interface ScriptRepo {
	
	String getIdent();
	
	ScriptRepoTag releaseTag();
	
	List<ScriptRepoTag> releaseTags();
	
	// SYNC THE SHIT 
	
	public ScriptRepoTag tagRelease(XScriptBundle bundle) throws Exception;
	

	// time-signal-srvc
	// time-signal-mod
	// time-signal-client
	// time-signal-model
	
}
