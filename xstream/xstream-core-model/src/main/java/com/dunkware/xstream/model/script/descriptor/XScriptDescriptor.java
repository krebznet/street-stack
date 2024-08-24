package com.dunkware.xstream.model.script.descriptor;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XScriptDescriptor {
	
	
	private String name;
	private String type;
	private String version;
	private List<XScriptVariableDescriptor> variables = new ArrayList<XScriptVariableDescriptor>();
	private List<XScriptSignalDescriptor> signals = new ArrayList<XScriptSignalDescriptor>();
	private List<XScriptBotDescriptor> bots = new ArrayList<XScriptBotDescriptor>();

}
