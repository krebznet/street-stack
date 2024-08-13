package com.dunkware.time.script.model.proto;

import com.dunkware.xstream.model.script.release.XScriptProblems;
import com.dunkware.xstream.model.script.release.XScriptRelease;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class XScriptReleaseRep {
	
	private boolean error = false; 
	private String version;
	private XScriptProblems problems;
	private XScriptRelease release;

	
	
	
	

}
