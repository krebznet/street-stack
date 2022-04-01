package com.dunkware.xstream.api;

import java.util.List;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

public class XStreamInput {

	private List<XStreamExtensionType> extensions;
	private DExecutor executor;
	private DDate date;
	private XScriptProject script;
	private XStreamRegistry registry;
	private String identifier;
	private String sessionId;
	private DTimeZone timeZone;
	
	public List<XStreamExtensionType> getExtensions() {
		return extensions;
	}
	public void setExtensions(List<XStreamExtensionType> extensions) {
		this.extensions = extensions;
	}
	public DExecutor getExecutor() {
		return executor;
	}
	public void setExecutor(DExecutor executor) {
		this.executor = executor;
	}
	
	public DDate getDate() {
		if(date == null) { 
			date = DDate.now();
		}
		return date;
	}
	public void setDate(DDate date) {
		this.date = date;
	}
	public XScriptProject getScript() {
		return script;
	}
	public void setScript(XScriptProject script) {
		this.script = script;
	}
	public XStreamRegistry getRegistry() {
		return registry;
	}
	public void setRegistry(XStreamRegistry registry) {
		this.registry = registry;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public DTimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(DTimeZone timeZone) {
		this.timeZone = timeZone;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
	
	
	
	
	
	
	
	
}
