package com.dunkware.time.script.mod.repo;

import java.util.List;

import com.dunkware.xstream.xproject.model.XScriptBundle;

public interface ScriptRepo {

	String getName();

	String getType();
	
	XScriptBundle getBundle();

	ScriptRepoVersion getLatestVersion();
	
	List<ScriptRepoVersion> getVersions();

}
