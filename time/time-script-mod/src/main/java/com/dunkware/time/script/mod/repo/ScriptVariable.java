package com.dunkware.time.script.mod.repo;

import lombok.Data;

@Data
public class ScriptVariable {

	private String ident; 
	private int id; 
	private String name; 
	private String group;
	private int releaseVersion; 
	private int deleteVersion; 
	private boolean active; 
	
	
}
