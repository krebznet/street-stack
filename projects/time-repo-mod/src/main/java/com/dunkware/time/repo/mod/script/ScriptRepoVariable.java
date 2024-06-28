package com.dunkware.time.repo.mod.script;

public interface ScriptRepoVariable {
	
	public int id();
	
	public String ident();
	
	public String name();
	
	public String group();
	
	public double getReleaseTag();
	
	public double getDeleteTag();
	
	public boolean isActive();
	
	

}
