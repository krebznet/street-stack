package com.dunkware.time.script.lib.client;

import com.dunkware.time.script.model.proto.XScriptDeployRep;
import com.dunkware.time.script.model.proto.XScriptReleaseRep;
import com.dunkware.time.script.model.proto.XScriptSyncRep;
import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;

public interface TimeScriptClient {

	XScriptDescriptor getLatestScriptReleaseModel(String scriptName) throws Exception; 
	
	XScriptReleaseRep releaseScript(String string, String scriptName) throws Exception;
	
	XScriptSyncRep syncScript(String script, String scriptName) throws Exception;
	
	XScriptDeployRep deployScript(String script, String scriptName, String scriptType) throws Exception;
}
