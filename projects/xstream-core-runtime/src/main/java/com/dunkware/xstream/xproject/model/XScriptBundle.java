package com.dunkware.xstream.xproject.model;

import java.util.ArrayList;
import java.util.List;


public class XScriptBundle {

	private String name;
	private List<XScriptFile> files = new ArrayList<XScriptFile>();

	public XScriptBundle() { 
		
	}
	
	public XScriptBundle(List<XScriptFile> files) { 
		this.files = files;
	}
	public List<XScriptFile> getFiles() {
		return files;
	}

	public void setFiles(List<XScriptFile> files) {
		this.files = files;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
