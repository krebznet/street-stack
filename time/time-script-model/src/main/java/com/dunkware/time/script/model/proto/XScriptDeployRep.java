package com.dunkware.time.script.model.proto;

import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;
import com.dunkware.xstream.model.script.release.XScriptProblems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XScriptDeployRep {

	private boolean error = false; 
	private XScriptProblems problems; 
	XScriptDescriptor model;
	private String version;
}
