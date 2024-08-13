package com.dunkware.time.client.lib.script;

import com.dunkware.time.client.lib.TimeClient;
import com.dunkware.time.client.lib.TimeConstants;
import com.dunkware.time.client.lib.exception.TimeException;
import com.dunkware.time.script.model.proto.XScriptDeployRep;
import com.dunkware.time.script.model.proto.XScriptDeployReq;
import com.dunkware.utils.reactive.client.ReactiveClient;

public class TimeScripts {
	
	private TimeClient timeClient; 
	private ReactiveClient webClient; 
	
	
	public TimeScripts(TimeClient timeClient) { 
		this.timeClient = timeClient; 
		this.webClient = timeClient.getWebClient();
	}
	
	
	public XScriptDeployRep createRepo(String name, String type, String source) throws TimeException { 
		XScriptDeployReq req = new XScriptDeployReq();
		req.setName(name);
		req.setScript(source);
		req.setType(type);
		try {
			XScriptDeployRep rep = webClient.postJsonResponse(TimeConstants.API_SCRIPT_DEPLOY, req,XScriptDeployRep.class);
			return rep;
		} catch (Exception e) {
			throw new TimeException("Exception calling API " + e.toString());
		}
		
	}
	


}
