package com.dunkware.xstream.model.script.utils;

import java.util.List;

import com.dunkware.xstream.model.script.model.XScriptModel;
import com.dunkware.xstream.model.script.model.XScriptModelBot;
import com.dunkware.xstream.model.script.model.XScriptModelSignal;
import com.dunkware.xstream.model.script.model.XScriptModelVariable;
import com.dunkware.xstream.model.script.model.XScriptUpdate;
import com.dunkware.xstream.model.script.model.XScriptUpdate.XScriptChange;
import com.dunkware.xstream.model.script.model.XScriptUpdate.XScriptChangeType;
import com.dunkware.xstream.model.script.model.XScriptUpdate.XScriptElementType;

public class XScriptUpdateInitializer {

	public static XScriptUpdate intialize(XScriptModel model) {
		XScriptUpdateInitializer in = new XScriptUpdateInitializer(model);
		return in.getUpdate();
	}
	
	private XScriptUpdate update;
	private XScriptModel model;
	
	private XScriptUpdateInitializer(XScriptModel model) { 
		this.model = model;
		update = new XScriptUpdate();
		insertBots(model.getBots());
		insertSignals(model.getSignals());
		insertVariables(model.getVariables());
		
	}
	
	public void insertVariables(List<XScriptModelVariable> vars) {
		for (XScriptModelVariable var : vars) {
			XScriptChange change = new XScriptChange();
			change.setElementId(var.getId());
			change.setElementLabel(var.getLabel());
			change.setElementName(var.getName());
			change.setChangeType(XScriptChangeType.Insert);
			change.setElementType(XScriptElementType.Variable);
			update.getChanges().add(change);
		}
		
	}
	
	public void insertSignals(List<XScriptModelSignal> vars) {
		for (XScriptModelSignal var : vars) {
			XScriptChange change = new XScriptChange();
			change.setElementId(var.getId());
			change.setElementLabel(var.getLabel());
			change.setElementName(var.getName());
			change.setChangeType(XScriptChangeType.Insert);
			change.setElementType(XScriptElementType.Signal);
			update.getChanges().add(change);
		}
		
	}
	
	public void insertBots(List<XScriptModelBot> bots) {
		for (XScriptModelBot var : bots) {
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
