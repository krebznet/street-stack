package com.dunkware.time.script.model.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class XScriptDeployReq {
	
	private String name; 
	private String script;
	private String type;
	

}
