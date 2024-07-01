package com.dunkware.time.script.model.proto;

import com.dunkware.xstream.xproject.model.XScriptBundle;

public class ScriptSyncReq {
	
	XScriptBundle bundle; 
	private String ident;
	
	
	public XScriptBundle getBundle() {
		return bundle;
	}
	public void setBundle(XScriptBundle bundle) {
		this.bundle = bundle;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}

}
