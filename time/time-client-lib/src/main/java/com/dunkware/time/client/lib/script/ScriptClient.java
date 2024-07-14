package com.dunkware.time.client.lib.script;

import com.dunkware.time.script.model.proto.XScriptDeployRep;
import com.dunkware.time.script.model.proto.XScriptDeployReq;
import com.dunkware.time.script.model.proto.XScriptSyncRep;
import com.dunkware.time.script.model.proto.XScriptReleaseRep;

public class ScriptClient {
	
	
	public static ScriptClient create(String endpoint, String username, String password) throws Exception {
		return new  ScriptClient(endpoint, username, password);
	}
	
	private String endpoint; 
	private String username; 
	private String password; 
	
	private ScriptClient(String endpoint, String username, String password) throws Exception { 
		this.endpoint = endpoint;
		this.username = username; 
		this.password = password;
	}
	
	public XScriptDeployRep deployScript(String name, String script) throws Exception { 
		XScriptDeployReq req = new XScriptDeployReq();
		req.setName(name);
		req.setScript(script);
		return null;
	}
	
	public XScriptSyncRep syncScript(String name, String script) throws Exception { 
		return null;
	}
	
	public XScriptReleaseRep updateScript(String name, String script) throws Exception { 
		return null;
	}
	
	
	

}
