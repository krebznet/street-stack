package com.dunkware.xstream.model.script.utils;

import java.util.List;

import com.dunkware.xstream.model.script.descriptor.XScriptBotDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptSignalDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptVariableDescriptor;
import com.dunkware.xstream.model.script.release.XScriptUpdate;
import com.dunkware.xstream.model.script.release.XScriptUpdate.XScriptChange;
import com.dunkware.xstream.model.script.release.XScriptUpdate.XScriptChangeType;
import com.dunkware.xstream.model.script.release.XScriptUpdate.XScriptElementType;

public class XScriptUpdateInitializer {

	public static XScriptUpdate intialize(XScriptDescriptor model) {
		XScriptUpdateInitializer in = new XScriptUpdateInitializer(model);
		return in.getUpdate();
	}
	
	private XScriptUpdate update;
	private XScriptDescriptor model;
	
	private XScriptUpdateInitializer(XScriptDescriptor model) { 
		this.model = model;
		update = new XScriptUpdate();
		insertBots(model.getBots());
		insertSignals(model.getSignals());
		insertVariables(model.getVariables());
		
	}
	
	public void insertVariables(List<XScriptVariableDescriptor> vars) {
		for (XScriptVariableDescriptor var : vars) {
			XScriptChange change = new XScriptChange();
			change.setElementId(var.getId());
			change.setElementLabel(var.getLabel());
			change.setElementName(var.getName());
			change.setChangeType(XScriptChangeType.Insert);
			change.setElementType(XScriptElementType.Variable);
			update.getChanges().add(change);
		}
		
	}
	
	public void insertSignals(List<XScriptSignalDescriptor> vars) {
		for (XScriptSignalDescriptor var : vars) {
			XScriptChange change = new XScriptChange();
			change.setElementId(var.getId());
			change.setElementLabel(var.getLabel());
			change.setElementName(var.getName());
			change.setChangeType(XScriptChangeType.Insert);
			change.setElementType(XScriptElementType.Signal);
			update.getChanges().add(change);
		}
		
	}
	
	public void insertBots(List<XScriptBotDescriptor> bots) {
		for (XScriptBotDescriptor var : bots) {
			XScriptChange change = new XScriptChange();
			change.setElementId(var.getId());
			change.setElementLabel(var.getLabel());
			change.setElementName(var.getName());
			change.setChangeType(XScriptChangeType.Insert);
			change.setElementType(XScriptElementType.Signal);
			update.getChanges().add(change);
		}
		
	}
	
	public XScriptUpdate getUpdate() { 
		return update;
	}
}
