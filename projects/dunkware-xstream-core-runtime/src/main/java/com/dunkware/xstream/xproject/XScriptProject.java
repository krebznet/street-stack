package com.dunkware.xstream.xproject;

import java.beans.Transient;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.xstream.XScriptStandaloneSetup;
import com.dunkware.xstream.xScript.SignalType;
import com.dunkware.xstream.xScript.VarType;
import com.dunkware.xstream.xScript.XClassType;
import com.dunkware.xstream.xScript.XScript;
import com.dunkware.xstream.xScript.XScriptPackage;
import com.dunkware.xstream.xproject.model.XScriptBundle;
import com.dunkware.xstream.xproject.model.XScriptFile;
import com.google.inject.Injector;

public class XScriptProject {
	
	private boolean isSetup = false;

	private List<VarType> streamVars = new ArrayList<VarType>();
	private List<XScript> scripts = new ArrayList<XScript>();
	private List<XClassType> classes = new ArrayList<XClassType>();
	private List<SignalType> signals = new ArrayList<SignalType>();
	private List<Integer> streamVarIds = new ArrayList<Integer>();
	private List<Integer> streamSignalIds = new ArrayList<Integer>();
	private List<SignalType> streamSignals = new ArrayList<SignalType>();
	
	public XScriptProject(XScriptBundle bundle) throws XScriptException {
		if(!isSetup) { 
			XScriptStandaloneSetup.doSetup();
			isSetup = true;
		}
		Injector injector = new XScriptStandaloneSetup().createInjector();
		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
		resourceSet.getPackageRegistry().put(XScriptPackage.eNS_URI, XScriptPackage.eINSTANCE);
		int count = 0;
		for (XScriptFile res : bundle.getFiles()) {
			try {
				count++;
				Resource resource = resourceSet.createResource(URI.createURI("test " + DRandom.getRandom(3, 100) + count + ".xs"));
				resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL,
						Boolean.TRUE);
				InputStream in = new ByteArrayInputStream(res.getContent().getBytes());
				resource.load(in, resourceSet.getLoadOptions());
				
				XScript script = (XScript) resource.getContents().get(0);
				scripts.add(script);
			} catch (Exception e) {
				throw new XScriptException("Exception loading XScript Project " + e.toString(),e);
			}
		}
		
		// XScript

		for (XScript xScript : scripts) {
			streamVars.addAll(XScriptHelper.getVarTypes(xScript));
			classes.addAll(XScriptHelper.getClasses(xScript));
			signals.addAll(XScriptHelper.getSignals(xScript));
			signals.addAll(XScriptHelper.getSignals(xScript));
		}
		for (VarType varType : streamVars) {
			streamVarIds.add(varType.getId());
		}
		for (SignalType sigType : signals) { 
			streamSignalIds.add(sigType.getId());
		}
		
		
	}
	
	@Transient
	public boolean varExists(String name) { 
		for (VarType varType : streamVars) {
			if(varType.getName().equals(name)) { 
				return true; 
			}
		}
		return false; 
	}
	
	
	public List<SignalType> getStreamSignals() { 
		return signals;
	}

	
	public List<VarType> getStreamVars() {
		return streamVars;

	}

	
	public List<XScript> getScripts() {
		return scripts;
	}

	public List<XClassType> getClasses() { 
		return classes;
	}
	
	public List<Integer> getStreamVarIds() { 
		return streamVarIds;
	}
	
	public List<Integer> getStreamSignalIds() { 
		return streamSignalIds;
	}
}

