package com.dunkware.xstream.xproject.bundle;

import java.io.StringWriter;

import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.model.XScriptBundle;
import com.fasterxml.jackson.databind.ObjectMapper;

public class XScriptBundleTest {

	public static final String DEV1 = "/Users/duncankrebs/worksapces/dunkhub/runtime-StreetScript/equity-script";
	
	
	public static void main(String[] args) {
		try {
			XScriptBundle bundle = XscriptBundleHelper.createBundleFromFilePaths(DEV1);
			ObjectMapper mapper = new ObjectMapper();
			StringWriter writer = new StringWriter();
		   mapper.writeValue(writer, bundle);
		
			XScriptProject project = XscriptBundleHelper.loadProject(bundle);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}

// FileSet
	// File
		// - 