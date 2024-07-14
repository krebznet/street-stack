package com.dunkware.time.script.lib.client.impl;

import java.util.HashMap;

import com.dunkware.time.script.lib.client.TimeScriptClient;
import com.dunkware.time.script.model.proto.XScriptDeployRep;
import com.dunkware.time.script.model.proto.XScriptDeployReq;
import com.dunkware.time.script.model.proto.XScriptReleaseRep;
import com.dunkware.time.script.model.proto.XScriptSyncRep;
import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.utils.core.web.DunkWebClient;
import com.dunkware.xstream.model.script.model.XScriptModel;

//TODO: AVINASHANV-2 HttpTimeScriptClient
/**
 * This is an example of using the DunkWebClient line 57 uses it, the pattern is 
 * wrapping a set of related rest URL's into a client class, in this case this class
 * can create new stream scripts, compare local and remote scripts with domain models
 * and deploy updated scripts as release, i decided to not go with git. this is integrated
 * into Street Studio with wizards. 
 */
public class HttpTimeScriptClient implements TimeScriptClient {
	
	private static final String URL_SCRIPT_CREATE = "";
	private static final String URL_SCRIPT_SYNC = "";
	private static final String URL_SCRIPT_DEPLOY = "";
	
	
	public static HttpTimeScriptClient instance(String baseURL, String username, String password) throws Exception {
		return new HttpTimeScriptClient(baseURL, username, password);
	}
	
	private DunkWebClient webClient; 

	
	private HttpTimeScriptClient(String baseURL, String username, String password) throws Exception { 
		webClient = DunkWebClient.newInstance(baseURL, username, password); 
		
	}
	
	
	@Override
	public XScriptModel getLatestScriptReleaseModel(String scriptName) throws Exception {
		
		return null;
	}

	@Override
	public XScriptReleaseRep releaseScript(String string, String scriptName) throws Exception {
		
		return null;
	}

	@Override
	public XScriptSyncRep syncScript(String script, String scriptName) throws Exception {
		
		return null;
	}

	@Override
	public XScriptDeployRep deployScript(String script, String scriptName, String scriptType) throws Exception {
		XScriptDeployReq req = new XScriptDeployReq();
		req.setName(scriptName);
		req.setScript(script);
		req.setType(scriptType);
		String serialized = null;
		try {
			serialized = DunkJson.serialize(req);
		} catch (Exception e) {
			throw new Exception("Exception serializing request " + e.toString());
		}
		String response = null;
		try {
			response = webClient.postJson(URL_SCRIPT_DEPLOY, serialized, new HashMap<String,String>());
		} catch (Exception e) {
			throw new Exception("Exception invoking request " + e.toString());
		}
		try {
			XScriptDeployRep rep = DunkJson.getObjectMapper().readValue(response, XScriptDeployRep.class);
			return rep; 
		} catch (Exception X) {
			throw new Exception("Exception Deserializing Response " + X.toString());
		}
	}
	
	

}
