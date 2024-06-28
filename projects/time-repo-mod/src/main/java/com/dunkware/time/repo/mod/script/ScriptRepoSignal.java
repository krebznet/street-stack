package com.dunkware.time.repo.mod.script;

public interface ScriptRepoSignal {

	int getId();
	
	String getIdent();
	
	double getReleaseTag();
	
	double getDeleteTag();
	
	String getName();
	
	String getScriptString(); 
}
