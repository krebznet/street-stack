package com.dunkware.time.client.lib.script;

import com.dunkware.time.script.model.proto.ScriptDeployRep;
import com.dunkware.time.script.model.proto.ScriptDeployReq;
import com.dunkware.time.script.model.proto.ScriptSyncRep;
import com.dunkware.time.script.model.proto.ScriptUpdateRep;

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
	
	public ScriptDeployRep deployScript(String name, String script) throws Exception { 
		ScriptDeployReq req = new ScriptDeployReq();
		req.setName(name);
		req.setScript(script);
		return null;
	}
	
	public ScriptSyncRep syncScript(String name, String script) throws Exception { 
		return null;
	}
	
	public ScriptUpdateRep updateScript(String name, String script) throws Exception { 
		return null;
	}
	
	
	

}
