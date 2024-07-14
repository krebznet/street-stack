package com.dunkware.time.script.model.proto;

import com.dunkware.xstream.model.script.model.XScriptProblems;
import com.dunkware.xstream.model.script.model.XScriptUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class XScriptSyncRep {

	private String version;
	private boolean error = false; 
	private XScriptUpdate update;
	private XScriptProblems problems;
	
		
	
}
