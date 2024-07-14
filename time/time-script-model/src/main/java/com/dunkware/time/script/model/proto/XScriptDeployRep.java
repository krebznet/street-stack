package com.dunkware.time.script.model.proto;

import com.dunkware.xstream.model.script.model.XScriptModel;
import com.dunkware.xstream.model.script.model.XScriptProblems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XScriptDeployRep {

	private boolean error = false; 
	private XScriptProblems problems; 
	XScriptModel model;
	private String version;
}
